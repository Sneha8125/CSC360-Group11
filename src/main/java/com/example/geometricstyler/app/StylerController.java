package com.example.geometricstyler.app;

import com.example.geometricstyler.interaction.KeyboardController;
import com.example.geometricstyler.interaction.MouseController;
import com.example.geometricstyler.interaction.ShortcutActions;
import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ObjectStyle;
import com.example.geometricstyler.model.ShapeType;
import com.example.geometricstyler.object.ObjectFactory;
import com.example.geometricstyler.styling.StyleApplier;
import com.example.geometricstyler.ui.ControlPanel;
import com.example.geometricstyler.ui.PreviewPane;
import com.example.geometricstyler.ui.PropertyView;
import com.example.geometricstyler.ui.SliderField;
import com.example.geometricstyler.util.Defaults;
import com.example.geometricstyler.util.ValidationUtil;
import javafx.scene.Scene;

/**
 * Connects the control panel, the object model, the styling classes and the
 * mouse/keyboard controllers.
 *
 * <p>Everything flows in one direction: a control changes the model, the model
 * updates the JavaFX node, and the property display is refreshed. When the model
 * corrects a value (a square forcing equal sides, or a clamp at the preview edge)
 * the controller writes the corrected value back into the controls, guarded by
 * the {@code syncing} flag so the listeners do not trigger each other in a loop.</p>
 */
public class StylerController implements ShortcutActions {

    private final ControlPanel panel;
    private final PreviewPane preview;
    private final PropertyView properties;
    private final MouseController mouseController;

    private GeometricObject object;

    /** True while the controller is writing values back into the controls. */
    private boolean syncing;
    private boolean selected;
    private boolean centeredOnce;

    public StylerController(ControlPanel panel, PreviewPane preview, PropertyView properties) {
        this.panel = panel;
        this.preview = preview;
        this.properties = properties;
        this.mouseController = new MouseController(preview, this::handleDrag,
                this::setSelected, this::handleDragFinished);

        this.object = ObjectFactory.create(Defaults.SHAPE,
                panel.getXField().getValue(), panel.getYField().getValue(),
                Defaults.WIDTH, Defaults.HEIGHT);

        showActiveObject();
        mouseController.install();
        wireControls();
        followPreviewSize();
        properties.update(object);
    }

    /** Called from Main once the scene exists, so the shortcuts can be installed. */
    public void start(Scene scene) {
        new KeyboardController(scene, this).install();
        preview.requestFocus();
    }

    /* ==========================================================
       object lifecycle
       ========================================================== */

    private void showActiveObject() {
        preview.showObject(object);
        StyleApplier.applyAll(object);
        mouseController.attachTo(object);
        preview.setSelected(selected);
    }

    /**
     * Swaps the active object for a different shape type, keeping the current
     * position, size, transforms and styling. Only one object is ever on screen.
     */
    private void rebuildObject(ShapeType type) {
        GeometricObject previous = object;

        GeometricObject replacement = ObjectFactory.create(type);
        replacement.setStyle(previous.getStyle());
        replacement.initialise(previous.getX(), previous.getY(),
                previous.getWidth(), previous.getHeight());
        replacement.copyTransformsFrom(previous);

        object = replacement;
        showActiveObject();

        // A circle or a square may have forced the width and height to be equal.
        syncSizeControls();
        keepInsidePreview();
        properties.update(object);
        properties.showStatus(type.getLabel() + " selected");
    }

    /* ==========================================================
       control wiring
       ========================================================== */

    private void wireControls() {
        wireObjectSelection();
        wirePositionControls();
        wireTransformControls();
        wireAppearanceControls();
        wireEffectControls();
        wireActionButtons();
        reportInvalidInput();
    }

    private void wireObjectSelection() {
        panel.getShapeCombo().valueProperty().addListener((observable, oldType, newType) -> {
            if (syncing || newType == null) {
                return;
            }
            rebuildObject(newType);
        });
    }

    private void wirePositionControls() {
        panel.getXField().onChange(value -> {
            if (!syncing) {
                updatePosition(value, object.getY());
            }
        });
        panel.getYField().onChange(value -> {
            if (!syncing) {
                updatePosition(object.getX(), value);
            }
        });
    }

    private void wireTransformControls() {
        panel.getWidthField().onChange(value -> {
            if (syncing) {
                return;
            }
            object.setSize(value, object.getHeight());
            syncSizeControls();
            afterGeometryChange("Width set to " + ValidationUtil.format(object.getWidth(), 0) + " px");
        });

        panel.getHeightField().onChange(value -> {
            if (syncing) {
                return;
            }
            object.setSize(object.getWidth(), value);
            syncSizeControls();
            afterGeometryChange("Height set to " + ValidationUtil.format(object.getHeight(), 0) + " px");
        });

        panel.getRotationField().onChange(value -> {
            if (syncing) {
                return;
            }
            object.setRotation(value);
            afterGeometryChange("Rotated to " + ValidationUtil.format(object.getRotation(), 0) + " degrees");
        });

        panel.getScaleXField().onChange(value -> {
            if (syncing) {
                return;
            }
            double scaleY = panel.getUniformScaleCheck().isSelected() ? value : object.getScaleY();
            object.setScale(value, scaleY);
            syncScaleControls();
            afterGeometryChange("Scale " + scaleText());
        });

        panel.getScaleYField().onChange(value -> {
            if (syncing) {
                return;
            }
            double scaleX = panel.getUniformScaleCheck().isSelected() ? value : object.getScaleX();
            object.setScale(scaleX, value);
            syncScaleControls();
            afterGeometryChange("Scale " + scaleText());
        });

        panel.getUniformScaleCheck().selectedProperty().addListener((observable, wasOn, isOn) -> {
            if (syncing || !isOn) {
                return;
            }
            object.setScale(object.getScaleX(), object.getScaleX());
            syncScaleControls();
            afterGeometryChange("Scale linked: " + scaleText());
        });

        panel.getTranslateXField().onChange(value -> {
            if (!syncing) {
                updateTranslation(value, object.getTranslateY());
            }
        });
        panel.getTranslateYField().onChange(value -> {
            if (!syncing) {
                updateTranslation(object.getTranslateX(), value);
            }
        });
    }

    private void wireAppearanceControls() {
        ObjectStyle style = object.getStyle();

        panel.getFillPicker().valueProperty().addListener((observable, oldColor, newColor) -> {
            if (!syncing) {
                object.getStyle().setFillColor(newColor);
                restyle("Fill colour updated");
            }
        });

        panel.getFillTypeCombo().valueProperty().addListener((observable, oldType, newType) -> {
            if (syncing || newType == null) {
                return;
            }
            object.getStyle().setFillType(newType);
            panel.setGradientControlsEnabled(newType.usesGradientColors());
            restyle(newType.getLabel() + " fill applied");
        });

        panel.getGradientStartPicker().valueProperty().addListener((observable, oldColor, newColor) -> {
            if (!syncing) {
                object.getStyle().setGradientStart(newColor);
                restyle("Gradient updated");
            }
        });

        panel.getGradientEndPicker().valueProperty().addListener((observable, oldColor, newColor) -> {
            if (!syncing) {
                object.getStyle().setGradientEnd(newColor);
                restyle("Gradient updated");
            }
        });

        panel.getStrokePicker().valueProperty().addListener((observable, oldColor, newColor) -> {
            if (!syncing) {
                object.getStyle().setStrokeColor(newColor);
                restyle("Border colour updated");
            }
        });

        panel.getStrokeWidthField().onChange(value -> {
            if (!syncing) {
                object.getStyle().setStrokeWidth(value);
                restyle("Border width " + ValidationUtil.format(value, 0) + " px");
            }
        });

        panel.getStrokeStyleCombo().valueProperty().addListener((observable, oldStyle, newStyle) -> {
            if (syncing || newStyle == null) {
                return;
            }
            object.getStyle().setStrokeStyle(newStyle);
            restyle(newStyle.getLabel() + " border applied");
        });

        panel.getOpacityField().onChange(value -> {
            if (!syncing) {
                object.getStyle().setOpacity(value / 100.0);
                restyle("Opacity " + Math.round(value) + " %");
            }
        });

        panel.setGradientControlsEnabled(style.getFillType().usesGradientColors());
    }

    private void wireEffectControls() {
        panel.getDropShadowCheck().selectedProperty().addListener((observable, wasOn, isOn) -> {
            if (!syncing) {
                object.getStyle().setDropShadowEnabled(isOn);
                restyle(isOn ? "Drop shadow on" : "Drop shadow off");
            }
        });

        panel.getGlowCheck().selectedProperty().addListener((observable, wasOn, isOn) -> {
            if (!syncing) {
                object.getStyle().setGlowEnabled(isOn);
                restyle(isOn ? "Glow on" : "Glow off");
            }
        });
    }

    private void wireActionButtons() {
        panel.getCenterButton().setOnAction(event -> centerObject());
        panel.getFlipHorizontalButton().setOnAction(event -> flipHorizontally());
        panel.getFlipVerticalButton().setOnAction(event -> flipVertically());
        panel.getApplyButton().setOnAction(event -> applyChanges());
        panel.getResetButton().setOnAction(event -> resetAll());
    }

    /** Every numeric field reports a bad value through the same status line. */
    private void reportInvalidInput() {
        for (SliderField field : panel.getNumericFields()) {
            field.onInvalidInput(properties::showError);
        }
    }

    /* ==========================================================
       preview size
       ========================================================== */

    private void followPreviewSize() {
        preview.widthProperty().addListener((observable, oldWidth, newWidth) -> handleResize());
        preview.heightProperty().addListener((observable, oldHeight, newHeight) -> handleResize());
    }

    /**
     * The X and Y sliders always cover exactly the preview area, so the range
     * grows with the window instead of being a hard coded number.
     */
    private void handleResize() {
        double width = preview.getWidth();
        double height = preview.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }

        syncing = true;
        panel.getXField().setRange(0, Math.round(width));
        panel.getYField().setRange(0, Math.round(height));
        syncing = false;

        if (!centeredOnce) {
            centeredOnce = true;
            centerObject();
            properties.showStatus("Ready");
        } else {
            keepInsidePreview();
            syncPositionControls();
            properties.update(object);
        }
    }

    /* ==========================================================
       position, translation and boundaries
       ========================================================== */

    private void updatePosition(double x, double y) {
        object.setPosition(x, y);
        keepInsidePreview();
        syncPositionControls();
        properties.update(object);
    }

    private void updateTranslation(double translateX, double translateY) {
        object.setTranslation(translateX, translateY);
        keepInsidePreview();
        syncTranslationControls();
        properties.update(object);
        properties.showStatus("Translation " + ValidationUtil.format(object.getTranslateX(), 0)
                + ", " + ValidationUtil.format(object.getTranslateY(), 0) + " px");
    }

    /**
     * Stops the object from being lost outside the preview.
     * First the base position is pulled back inside, then any translation that
     * still pushes the object out is trimmed away.
     */
    private void keepInsidePreview() {
        double width = preview.getWidth();
        double height = preview.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }

        double baseX = ValidationUtil.clamp(object.getX(), 0, width);
        double baseY = ValidationUtil.clamp(object.getY(), 0, height);
        if (baseX != object.getX() || baseY != object.getY()) {
            object.setPosition(baseX, baseY);
        }

        double effectiveX = object.getEffectiveX();
        double effectiveY = object.getEffectiveY();
        double allowedX = ValidationUtil.clamp(effectiveX, 0, width);
        double allowedY = ValidationUtil.clamp(effectiveY, 0, height);

        if (allowedX != effectiveX || allowedY != effectiveY) {
            object.setTranslation(object.getTranslateX() + (allowedX - effectiveX),
                    object.getTranslateY() + (allowedY - effectiveY));
            syncTranslationControls();
        }
    }

    /**
     * Dragging and the arrow keys move the object to a point on screen.
     * The result is written into X / Y and the translation is folded into it, so
     * the two position mechanisms can never disagree about where the object is.
     */
    private void handleDrag(double effectiveX, double effectiveY) {
        double x = ValidationUtil.clamp(effectiveX, 0, preview.getWidth());
        double y = ValidationUtil.clamp(effectiveY, 0, preview.getHeight());

        object.setTranslation(0, 0);
        object.setPosition(x, y);

        syncPositionControls();
        syncTranslationControls();
        properties.update(object);
    }

    private void handleDragFinished() {
        properties.showStatus("Moved to " + ValidationUtil.format(object.getX(), 0)
                + ", " + ValidationUtil.format(object.getY(), 0));
    }

    private void setSelected(boolean nowSelected) {
        this.selected = nowSelected;
        preview.setSelected(nowSelected);
        properties.showStatus(nowSelected
                ? object.getType().getLabel() + " selected - drag it or use the arrow keys"
                : "Selection cleared");
    }

    /* ==========================================================
       buttons and keyboard shortcuts
       ========================================================== */

    @Override
    public void moveBy(double deltaX, double deltaY) {
        handleDrag(object.getEffectiveX() + deltaX, object.getEffectiveY() + deltaY);
        properties.showStatus("Moved to " + ValidationUtil.format(object.getX(), 0)
                + ", " + ValidationUtil.format(object.getY(), 0));
    }

    @Override
    public void scaleBy(double delta) {
        double scaleX = ValidationUtil.sanitizeScale(object.getScaleX() + delta);
        double scaleY = panel.getUniformScaleCheck().isSelected()
                ? scaleX
                : ValidationUtil.sanitizeScale(object.getScaleY() + delta);

        object.setScale(scaleX, scaleY);
        syncScaleControls();
        properties.update(object);
        properties.showStatus("Scale " + scaleText());
    }

    @Override
    public void centerObject() {
        if (preview.getWidth() <= 0) {
            return;
        }
        // Centring also clears the translation, otherwise the object would sit
        // next to the middle instead of on it.
        object.setTranslation(0, 0);
        object.setPosition(preview.getCenterX(), preview.getCenterY());

        syncPositionControls();
        syncTranslationControls();
        properties.update(object);
        properties.showStatus("Object centred");
    }

    private void flipHorizontally() {
        object.setFlippedHorizontally(!object.isFlippedHorizontally());
        properties.update(object);
        properties.showStatus(object.isFlippedHorizontally()
                ? "Flipped horizontally" : "Horizontal flip removed");
    }

    private void flipVertically() {
        object.setFlippedVertically(!object.isFlippedVertically());
        properties.update(object);
        properties.showStatus(object.isFlippedVertically()
                ? "Flipped vertically" : "Vertical flip removed");
    }

    /**
     * Sliders and colour pickers update the object live. Numbers typed into the
     * text boxes are only committed when the user presses Enter, leaves the box
     * or presses Apply, so Apply always has real work to do.
     */
    private void applyChanges() {
        boolean everythingValid = true;
        int committed = 0;

        for (SliderField field : panel.getNumericFields()) {
            if (field.hasUncommittedText()) {
                committed++;
                if (!field.commit()) {
                    everythingValid = false;
                }
            }
        }

        StyleApplier.applyAll(object);
        keepInsidePreview();
        syncAllControls();
        properties.update(object);

        if (everythingValid) {
            properties.showStatus(committed == 0
                    ? "Style applied"
                    : "Style applied (" + committed + " typed value(s) committed)");
        }
    }

    @Override
    public void resetAll() {
        syncing = true;
        panel.getShapeCombo().setValue(Defaults.SHAPE);
        panel.getWidthField().setValue(Defaults.WIDTH);
        panel.getHeightField().setValue(Defaults.HEIGHT);
        panel.getRotationField().setValue(Defaults.ROTATION);
        panel.getScaleXField().setValue(Defaults.SCALE);
        panel.getScaleYField().setValue(Defaults.SCALE);
        panel.getUniformScaleCheck().setSelected(true);
        panel.getTranslateXField().setValue(Defaults.TRANSLATION);
        panel.getTranslateYField().setValue(Defaults.TRANSLATION);
        panel.getFillPicker().setValue(Defaults.FILL);
        panel.getFillTypeCombo().setValue(Defaults.FILL_TYPE);
        panel.getGradientStartPicker().setValue(Defaults.GRADIENT_START);
        panel.getGradientEndPicker().setValue(Defaults.GRADIENT_END);
        panel.getStrokePicker().setValue(Defaults.STROKE);
        panel.getStrokeWidthField().setValue(Defaults.STROKE_WIDTH);
        panel.getStrokeStyleCombo().setValue(Defaults.STROKE_STYLE);
        panel.getOpacityField().setValue(Defaults.MAX_OPACITY_PERCENT);
        panel.getDropShadowCheck().setSelected(Defaults.DROP_SHADOW);
        panel.getGlowCheck().setSelected(Defaults.GLOW);
        syncing = false;

        double centerX = preview.getWidth() > 0 ? preview.getCenterX() : panel.getXField().getValue();
        double centerY = preview.getHeight() > 0 ? preview.getCenterY() : panel.getYField().getValue();

        object = ObjectFactory.create(Defaults.SHAPE, centerX, centerY,
                Defaults.WIDTH, Defaults.HEIGHT);
        object.setStyle(new ObjectStyle());

        panel.setGradientControlsEnabled(Defaults.FILL_TYPE.usesGradientColors());
        showActiveObject();
        syncAllControls();
        properties.update(object);
        properties.showStatus("Reset to default");
    }

    /* ==========================================================
       writing model values back into the controls
       ========================================================== */

    private void syncAllControls() {
        syncPositionControls();
        syncSizeControls();
        syncScaleControls();
        syncTranslationControls();
        syncing = true;
        panel.getRotationField().setValue(object.getRotation());
        panel.getStrokeWidthField().setValue(object.getStyle().getStrokeWidth());
        panel.getOpacityField().setValue(object.getStyle().getOpacity() * 100);
        syncing = false;
    }

    private void syncPositionControls() {
        syncing = true;
        panel.getXField().setValue(object.getX());
        panel.getYField().setValue(object.getY());
        syncing = false;
    }

    private void syncSizeControls() {
        syncing = true;
        panel.getWidthField().setValue(object.getWidth());
        panel.getHeightField().setValue(object.getHeight());
        syncing = false;
    }

    private void syncScaleControls() {
        syncing = true;
        panel.getScaleXField().setValue(object.getScaleX());
        panel.getScaleYField().setValue(object.getScaleY());
        syncing = false;
    }

    private void syncTranslationControls() {
        syncing = true;
        panel.getTranslateXField().setValue(object.getTranslateX());
        panel.getTranslateYField().setValue(object.getTranslateY());
        syncing = false;
    }

    /* ----- small helpers ----- */

    private void afterGeometryChange(String message) {
        keepInsidePreview();
        properties.update(object);
        properties.showStatus(message);
    }

    private void restyle(String message) {
        StyleApplier.applyAll(object);
        properties.update(object);
        properties.showStatus(message);
    }

    private String scaleText() {
        return ValidationUtil.format(object.getScaleX(), 2)
                + " x " + ValidationUtil.format(object.getScaleY(), 2);
    }
}
