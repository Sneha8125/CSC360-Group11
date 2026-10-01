package com.example.geometricstyler.ui;

import com.example.geometricstyler.model.FillType;
import com.example.geometricstyler.model.ShapeType;
import com.example.geometricstyler.model.StrokeStyle;
import com.example.geometricstyler.util.Defaults;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

/**
 * The left hand control panel.
 *
 * <p>This class only builds and arranges the controls. It deliberately contains
 * no application logic: the wiring between a control and the object lives in
 * {@code StylerController}, which keeps the UI easy to change without touching
 * the model.</p>
 */
public class ControlPanel extends ScrollPane {

    private static final double PANEL_WIDTH = 330;

    /* object */
    private final ComboBox<ShapeType> shapeCombo = new ComboBox<>();

    /* position */
    private final SliderField xField;
    private final SliderField yField;

    /* size and transformation */
    private final SliderField widthField;
    private final SliderField heightField;
    private final SliderField rotationField;
    private final SliderField scaleXField;
    private final SliderField scaleYField;
    private final CheckBox uniformScaleCheck = new CheckBox("Keep scale uniform");
    private final SliderField translateXField;
    private final SliderField translateYField;

    /* appearance */
    private final ColorPicker fillPicker = new ColorPicker(Defaults.FILL);
    private final ComboBox<FillType> fillTypeCombo = new ComboBox<>();
    private final ColorPicker gradientStartPicker = new ColorPicker(Defaults.GRADIENT_START);
    private final ColorPicker gradientEndPicker = new ColorPicker(Defaults.GRADIENT_END);
    private final ColorPicker strokePicker = new ColorPicker(Defaults.STROKE);
    private final SliderField strokeWidthField;
    private final ComboBox<StrokeStyle> strokeStyleCombo = new ComboBox<>();
    private final SliderField opacityField;

    /* effects */
    private final CheckBox dropShadowCheck = new CheckBox("Drop shadow");
    private final CheckBox glowCheck = new CheckBox("Glow");

    /* actions */
    private final Button centerButton = new Button("Center object");
    private final Button flipHorizontalButton = new Button("Flip H");
    private final Button flipVerticalButton = new Button("Flip V");
    private final Button applyButton = new Button("Apply");
    private final Button resetButton = new Button("Reset");

    private final HBox gradientRow;

    public ControlPanel() {
        xField = new SliderField("X position", 0, 700, 350, 0, " px");
        yField = new SliderField("Y position", 0, 500, 250, 0, " px");

        widthField = new SliderField("Width", Defaults.MIN_SIZE, Defaults.MAX_SIZE,
                Defaults.WIDTH, 0, " px");
        heightField = new SliderField("Height", Defaults.MIN_SIZE, Defaults.MAX_SIZE,
                Defaults.HEIGHT, 0, " px");
        rotationField = new SliderField("Rotation", Defaults.MIN_ROTATION, Defaults.MAX_ROTATION,
                Defaults.ROTATION, 0, " deg");
        scaleXField = new SliderField("Scale X", Defaults.MIN_SCALE, Defaults.MAX_SCALE,
                Defaults.SCALE, 2, "");
        scaleYField = new SliderField("Scale Y", Defaults.MIN_SCALE, Defaults.MAX_SCALE,
                Defaults.SCALE, 2, "");
        translateXField = new SliderField("Translate X", Defaults.MIN_TRANSLATION,
                Defaults.MAX_TRANSLATION, Defaults.TRANSLATION, 0, " px");
        translateYField = new SliderField("Translate Y", Defaults.MIN_TRANSLATION,
                Defaults.MAX_TRANSLATION, Defaults.TRANSLATION, 0, " px");
        strokeWidthField = new SliderField("Border width", Defaults.MIN_STROKE_WIDTH,
                Defaults.MAX_STROKE_WIDTH, Defaults.STROKE_WIDTH, 0, " px");
        opacityField = new SliderField("Opacity", Defaults.MIN_OPACITY_PERCENT,
                Defaults.MAX_OPACITY_PERCENT, Defaults.MAX_OPACITY_PERCENT, 0, " %");

        gradientRow = UiFactory.labelledRow("Gradient colours",
                new HBox(6, gradientStartPicker, gradientEndPicker));

        configureCombos();
        addTooltips();

        VBox content = new VBox(16,
                buildObjectSection(),
                buildPositionSection(),
                buildTransformSection(),
                buildAppearanceSection(),
                buildEffectsSection(),
                buildActionSection());
        content.getStyleClass().add("control-content");

        setContent(content);
        setFitToWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setPrefWidth(PANEL_WIDTH);
        setMinWidth(300);
        getStyleClass().add("control-panel");
    }

    /* ----- construction helpers ----- */

    private void configureCombos() {
        shapeCombo.getItems().addAll(ShapeType.values());
        shapeCombo.setValue(Defaults.SHAPE);
        shapeCombo.setMaxWidth(Double.MAX_VALUE);

        fillTypeCombo.getItems().addAll(FillType.values());
        fillTypeCombo.setValue(Defaults.FILL_TYPE);

        strokeStyleCombo.getItems().addAll(StrokeStyle.values());
        strokeStyleCombo.setValue(Defaults.STROKE_STYLE);

        uniformScaleCheck.setSelected(true);
    }

    private void addTooltips() {
        shapeCombo.setTooltip(new Tooltip("Choose which geometric object to style"));
        rotationField.withTooltip("Rotate the object from 0 to 360 degrees around its centre");
        scaleXField.withTooltip("Increase or decrease the object size horizontally");
        scaleYField.withTooltip("Increase or decrease the object size vertically");
        translateXField.withTooltip("Extra horizontal offset applied on top of the X position");
        translateYField.withTooltip("Extra vertical offset applied on top of the Y position");
        opacityField.withTooltip("Change object transparency");
        strokeWidthField.withTooltip("Thickness of the border in pixels");
        fillTypeCombo.setTooltip(new Tooltip("Fill the object with a colour or a gradient"));
        strokeStyleCombo.setTooltip(new Tooltip("Draw the border solid, dashed or dotted"));
        dropShadowCheck.setTooltip(new Tooltip("Apply a shadow effect to the object"));
        glowCheck.setTooltip(new Tooltip("Make the colours of the object glow"));
        applyButton.setTooltip(new Tooltip("Commit the values typed in the number boxes"));
        resetButton.setTooltip(new Tooltip("Put every setting back to its default value"));
    }

    private VBox buildObjectSection() {
        return UiFactory.section("Object", UiFactory.labelledRow("Type", shapeCombo));
    }

    private VBox buildPositionSection() {
        return UiFactory.section("Position", xField, yField);
    }

    private VBox buildTransformSection() {
        return UiFactory.section("Transformation",
                widthField, heightField, rotationField,
                uniformScaleCheck, scaleXField, scaleYField,
                translateXField, translateYField);
    }

    private VBox buildAppearanceSection() {
        return UiFactory.section("Appearance",
                UiFactory.labelledRow("Fill colour", fillPicker),
                UiFactory.labelledRow("Fill type", fillTypeCombo),
                gradientRow,
                UiFactory.labelledRow("Border colour", strokePicker),
                strokeWidthField,
                UiFactory.labelledRow("Border style", strokeStyleCombo),
                opacityField);
    }

    private VBox buildEffectsSection() {
        return UiFactory.section("Effects", dropShadowCheck, glowCheck);
    }

    private VBox buildActionSection() {
        centerButton.getStyleClass().add("secondary-button");
        flipHorizontalButton.getStyleClass().add("secondary-button");
        flipVerticalButton.getStyleClass().add("secondary-button");
        applyButton.getStyleClass().add("primary-button");
        resetButton.getStyleClass().add("secondary-button");

        centerButton.setMaxWidth(Double.MAX_VALUE);
        centerButton.setTooltip(new Tooltip("Move the object back to the middle of the preview"));

        return UiFactory.section("Actions",
                centerButton,
                UiFactory.buttonRow(flipHorizontalButton, flipVerticalButton),
                UiFactory.buttonRow(applyButton, resetButton));
    }

    /* ----- state used by the controller ----- */

    /** The gradient colour pickers only make sense for the two gradient fill types. */
    public void setGradientControlsEnabled(boolean enabled) {
        gradientRow.setDisable(!enabled);
    }

    /** Every numeric field, so Apply can commit all of them in one go. */
    public List<SliderField> getNumericFields() {
        return Arrays.asList(xField, yField, widthField, heightField, rotationField,
                scaleXField, scaleYField, translateXField, translateYField,
                strokeWidthField, opacityField);
    }

    /* ----- getters ----- */

    public ComboBox<ShapeType> getShapeCombo() {
        return shapeCombo;
    }

    public SliderField getXField() {
        return xField;
    }

    public SliderField getYField() {
        return yField;
    }

    public SliderField getWidthField() {
        return widthField;
    }

    public SliderField getHeightField() {
        return heightField;
    }

    public SliderField getRotationField() {
        return rotationField;
    }

    public SliderField getScaleXField() {
        return scaleXField;
    }

    public SliderField getScaleYField() {
        return scaleYField;
    }

    public CheckBox getUniformScaleCheck() {
        return uniformScaleCheck;
    }

    public SliderField getTranslateXField() {
        return translateXField;
    }

    public SliderField getTranslateYField() {
        return translateYField;
    }

    public ColorPicker getFillPicker() {
        return fillPicker;
    }

    public ComboBox<FillType> getFillTypeCombo() {
        return fillTypeCombo;
    }

    public ColorPicker getGradientStartPicker() {
        return gradientStartPicker;
    }

    public ColorPicker getGradientEndPicker() {
        return gradientEndPicker;
    }

    public ColorPicker getStrokePicker() {
        return strokePicker;
    }

    public SliderField getStrokeWidthField() {
        return strokeWidthField;
    }

    public ComboBox<StrokeStyle> getStrokeStyleCombo() {
        return strokeStyleCombo;
    }

    public SliderField getOpacityField() {
        return opacityField;
    }

    public CheckBox getDropShadowCheck() {
        return dropShadowCheck;
    }

    public CheckBox getGlowCheck() {
        return glowCheck;
    }

    public Button getCenterButton() {
        return centerButton;
    }

    public Button getFlipHorizontalButton() {
        return flipHorizontalButton;
    }

    public Button getFlipVerticalButton() {
        return flipVerticalButton;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public Button getResetButton() {
        return resetButton;
    }
}
