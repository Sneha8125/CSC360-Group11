package com.example.geometricStyler;

import com.example.geometricStyler.model.CircleObject;
import com.example.geometricStyler.model.EllipseObject;
import com.example.geometricStyler.model.GeometricObject;
import com.example.geometricStyler.model.ObjectFactory;
import com.example.geometricStyler.model.PolygonObject;
import com.example.geometricStyler.model.RectangleObject;

import com.example.geometricStyler.styling.EffectManager;
import com.example.geometricStyler.styling.StyleManager;

import com.example.geometricStyler.ui.ControlPanel;
import com.example.geometricStyler.ui.PreviewPane;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private GeometricObject currentObject;

    private ControlPanel controlPanel;

    private PreviewPane previewPane;

    private StyleManager styleManager;

    private EffectManager effectManager;

    // Preview boundaries
    private static final double PREVIEW_WIDTH = 700;
    private static final double PREVIEW_HEIGHT = 550;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        controlPanel = new ControlPanel();

        previewPane = new PreviewPane();

        styleManager = new StyleManager();

        effectManager = new EffectManager();

        ScrollPane controlScrollPane =
                new ScrollPane(controlPanel);

        controlScrollPane.setFitToWidth(true);

        controlScrollPane.setFitToHeight(false);

        controlScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        controlScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        controlScrollPane.setPrefWidth(370);

        controlScrollPane
                .getStyleClass()
                .add("control-scroll-pane");

        root.setLeft(controlScrollPane);

        root.setCenter(previewPane);

        Scene scene =
                new Scene(root, 1100, 700);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        stage.setTitle(
                "Geometric Object Styler"
        );

        stage.setScene(scene);

        stage.setMinWidth(900);

        stage.setMinHeight(600);

        createInitialObject();

        setupObjectSelection();

        setupTransformations();

        setupStyling();

        setupEffects();

        stage.show();
    }

    // =========================================================
    // INITIAL OBJECT
    // =========================================================

    private void createInitialObject() {

        currentObject =
                ObjectFactory.create("Circle");

        updateControlValues();

        updatePreview();
    }

    // =========================================================
    // OBJECT SELECTION
    // =========================================================

    private void setupObjectSelection() {

        controlPanel
                .getObjectTypeComboBox()
                .setOnAction(event -> {

                    String type =
                            controlPanel
                                    .getObjectTypeComboBox()
                                    .getValue();

                    if (type == null) {
                        return;
                    }

                    currentObject =
                            ObjectFactory.create(type);

                    styleManager.reset(
                            currentObject.createNode()
                    );

                    effectManager.reset(
                            currentObject.createNode()
                    );

                    controlPanel
                            .getDropShadowCheckBox()
                            .setSelected(false);

                    controlPanel
                            .getGlowCheckBox()
                            .setSelected(false);

                    updateControlValues();

                    updatePreview();
                });
    }

    // =========================================================
    // TRANSFORMATIONS
    // =========================================================

    private void setupTransformations() {

        controlPanel
                .getXPositionField()
                .setOnAction(event ->
                        updatePosition());

        controlPanel
                .getYPositionField()
                .setOnAction(event ->
                        updatePosition());

        controlPanel
                .getWidthField()
                .setOnAction(event ->
                        updateDimensions());

        controlPanel
                .getHeightField()
                .setOnAction(event ->
                        updateDimensions());

        // Rotation
        controlPanel
                .getRotationSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    currentObject.setRotation(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        // Scale X
        controlPanel
                .getScaleXSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    double value =
                            newValue.doubleValue();

                    if (value == 0) {
                        value = 0.1;
                    }

                    currentObject.setScaleX(value);

                    if (controlPanel
                            .getKeepScaleUniform()
                            .isSelected()) {

                        currentObject.setScaleY(value);

                        controlPanel
                                .getScaleYSlider()
                                .setValue(value);
                    }

                    refreshObject();
                });

        // Scale Y
        controlPanel
                .getScaleYSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    double value =
                            newValue.doubleValue();

                    if (value == 0) {
                        value = 0.1;
                    }

                    currentObject.setScaleY(value);

                    if (controlPanel
                            .getKeepScaleUniform()
                            .isSelected()) {

                        currentObject.setScaleX(value);

                        controlPanel
                                .getScaleXSlider()
                                .setValue(value);
                    }

                    refreshObject();
                });

        // Translation X
        controlPanel
                .getTranslateXSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    currentObject.setTranslateX(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        // Translation Y
        controlPanel
                .getTranslateYSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    currentObject.setTranslateY(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        // Center
        controlPanel
                .getCenterButton()
                .setOnAction(event -> {

                    currentObject.setX(
                            PREVIEW_WIDTH / 2
                    );

                    currentObject.setY(
                            PREVIEW_HEIGHT / 2
                    );

                    updatePositionFields();

                    refreshObject();
                });

        // Flip Horizontal
        controlPanel
                .getFlipHButton()
                .setOnAction(event -> {

                    currentObject.setScaleX(
                            currentObject.getScaleX()
                                    * -1
                    );

                    controlPanel
                            .getScaleXSlider()
                            .setValue(
                                    currentObject.getScaleX()
                            );

                    refreshObject();
                });

        // Flip Vertical
        controlPanel
                .getFlipVButton()
                .setOnAction(event -> {

                    currentObject.setScaleY(
                            currentObject.getScaleY()
                                    * -1
                    );

                    controlPanel
                            .getScaleYSlider()
                            .setValue(
                                    currentObject.getScaleY()
                            );

                    refreshObject();
                });

        // Reset
        controlPanel
                .getResetButton()
                .setOnAction(event -> resetObject());
    }

    // =========================================================
    // STYLING
    // =========================================================

    private void setupStyling() {

        // Solid fill color
        controlPanel
                .getFillColorPicker()
                .setOnAction(event -> {

                    applyFillColor();

                    updatePreview();
                });

        // Fill type
        controlPanel
                .getFillTypeComboBox()
                .setOnAction(event -> {

                    applyFillType();

                    updatePreview();
                });

        // Gradient Color 1
        controlPanel
                .getGradientColor1Picker()
                .setOnAction(event -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setGradientColor1(
                            controlPanel
                                    .getGradientColor1Picker()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // Gradient Color 2
        controlPanel
                .getGradientColor2Picker()
                .setOnAction(event -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setGradientColor2(
                            controlPanel
                                    .getGradientColor2Picker()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // Border color
        controlPanel
                .getBorderColorPicker()
                .setOnAction(event -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setStrokeColor(
                            controlPanel
                                    .getBorderColorPicker()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // Border width
        controlPanel
                .getBorderWidthSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setStrokeWidth(
                            newValue.doubleValue(),
                            node
                    );

                    updatePreview();
                });

        // Border style
        controlPanel
                .getBorderStyleComboBox()
                .setOnAction(event -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setStrokeStyle(
                            controlPanel
                                    .getBorderStyleComboBox()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // Opacity
        controlPanel
                .getOpacitySlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                    Node node =
                            currentObject.createNode();

                    styleManager.setOpacity(
                            newValue.doubleValue(),
                            node
                    );

                    updatePreview();
                });

        // Apply
        controlPanel
                .getApplyButton()
                .setOnAction(event -> {

                    updatePreview();
                });
    }

    // =========================================================
    // EFFECTS
    // =========================================================

    private void setupEffects() {

        controlPanel
                .getDropShadowCheckBox()
                .setOnAction(event -> {

                    effectManager
                            .setDropShadowEnabled(
                                    controlPanel
                                            .getDropShadowCheckBox()
                                            .isSelected()
                            );

                    updatePreview();
                });

        controlPanel
                .getGlowCheckBox()
                .setOnAction(event -> {

                    effectManager
                            .setGlowEnabled(
                                    controlPanel
                                            .getGlowCheckBox()
                                            .isSelected()
                            );

                    updatePreview();
                });
    }

    // =========================================================
    // FILL
    // =========================================================

    private void applyFillColor() {

        Node node =
                currentObject.createNode();

        styleManager.setFillColor(
                controlPanel
                        .getFillColorPicker()
                        .getValue(),
                node
        );
    }

    private void applyFillType() {

        Node node =
                currentObject.createNode();

        styleManager.setFillType(
                controlPanel
                        .getFillTypeComboBox()
                        .getValue(),
                node
        );
    }

    // =========================================================
    // POSITION VALIDATION
    // =========================================================

    private void updatePosition() {

        try {

            double x =
                    Double.parseDouble(
                            controlPanel
                                    .getXPositionField()
                                    .getText()
                                    .trim()
                    );

            double y =
                    Double.parseDouble(
                            controlPanel
                                    .getYPositionField()
                                    .getText()
                                    .trim()
                    );

            if (!Double.isFinite(x)
                    || !Double.isFinite(y)) {

                updatePositionFields();

                return;
            }

            currentObject.setX(
                    clamp(
                            x,
                            0,
                            PREVIEW_WIDTH
                    )
            );

            currentObject.setY(
                    clamp(
                            y,
                            0,
                            PREVIEW_HEIGHT
                    )
            );

            updatePositionFields();

            refreshObject();

        } catch (NumberFormatException exception) {

            updatePositionFields();
        }
    }

    // =========================================================
    // DIMENSION VALIDATION
    // =========================================================

    private void updateDimensions() {

        try {

            double width =
                    Double.parseDouble(
                            controlPanel
                                    .getWidthField()
                                    .getText()
                                    .trim()
                    );

            double height =
                    Double.parseDouble(
                            controlPanel
                                    .getHeightField()
                                    .getText()
                                    .trim()
                    );

            if (!Double.isFinite(width)
                    || !Double.isFinite(height)
                    || width <= 0
                    || height <= 0) {

                updateDimensionFields();

                return;
            }

            width = clamp(
                    width,
                    10,
                    PREVIEW_WIDTH
            );

            height = clamp(
                    height,
                    10,
                    PREVIEW_HEIGHT
            );

            currentObject.setWidth(width);

            currentObject.setHeight(height);

            updateDimensionFields();

            refreshObject();

        } catch (NumberFormatException exception) {

            updateDimensionFields();
        }
    }

    // =========================================================
    // CLAMP VALUE
    // =========================================================

    private double clamp(
            double value,
            double minimum,
            double maximum) {

        return Math.max(
                minimum,
                Math.min(
                        maximum,
                        value
                )
        );
    }

    // =========================================================
    // REFRESH OBJECT
    // =========================================================

    private void refreshObject() {

        if (currentObject instanceof CircleObject object) {

            object.updateNode();

        } else if (currentObject instanceof RectangleObject object) {

            object.updateNode();

        } else if (currentObject instanceof EllipseObject object) {

            object.updateNode();

        } else if (currentObject instanceof PolygonObject object) {

            object.updateNode();
        }

        updatePreview();
    }

    // =========================================================
    // PREVIEW
    // =========================================================

    private void updatePreview() {

        if (currentObject == null) {
            return;
        }

        Node node =
                currentObject.createNode();

        styleManager.applyStyle(node);

        effectManager.applyEffects(node);

        previewPane.setObject(node);
    }

    // =========================================================
    // RESET
    // =========================================================

    private void resetObject() {

        String type =
                controlPanel
                        .getObjectTypeComboBox()
                        .getValue();

        if (type == null) {
            type = "Circle";
        }

        currentObject =
                ObjectFactory.create(type);

        Node node =
                currentObject.createNode();

        styleManager.reset(node);

        effectManager.reset(node);

        // Reset effect checkboxes
        controlPanel
                .getDropShadowCheckBox()
                .setSelected(false);

        controlPanel
                .getGlowCheckBox()
                .setSelected(false);

        // Reset style controls
        controlPanel
                .getFillTypeComboBox()
                .setValue("Solid");

        controlPanel
                .getFillColorPicker()
                .setValue(
                        javafx.scene.paint.Color.LIGHTBLUE
                );

        controlPanel
                .getGradientColor1Picker()
                .setValue(
                        javafx.scene.paint.Color.LIGHTBLUE
                );

        controlPanel
                .getGradientColor2Picker()
                .setValue(
                        javafx.scene.paint.Color.DODGERBLUE
                );

        controlPanel
                .getBorderColorPicker()
                .setValue(
                        javafx.scene.paint.Color.BLACK
                );

        controlPanel
                .getBorderWidthSlider()
                .setValue(3);

        controlPanel
                .getBorderStyleComboBox()
                .setValue("Solid");

        controlPanel
                .getOpacitySlider()
                .setValue(1.0);

        updateControlValues();

        updatePreview();
    }

    // =========================================================
    // UPDATE POSITION FIELDS
    // =========================================================

    private void updatePositionFields() {

        controlPanel
                .getXPositionField()
                .setText(
                        formatValue(
                                currentObject.getX()
                        )
                );

        controlPanel
                .getYPositionField()
                .setText(
                        formatValue(
                                currentObject.getY()
                        )
                );
    }

    // =========================================================
    // UPDATE DIMENSION FIELDS
    // =========================================================

    private void updateDimensionFields() {

        controlPanel
                .getWidthField()
                .setText(
                        formatValue(
                                currentObject.getWidth()
                        )
                );

        controlPanel
                .getHeightField()
                .setText(
                        formatValue(
                                currentObject.getHeight()
                        )
                );
    }

    // =========================================================
    // UPDATE CONTROLS
    // =========================================================

    private void updateControlValues() {

        updatePositionFields();

        updateDimensionFields();

        controlPanel
                .getRotationSlider()
                .setValue(
                        currentObject.getRotation()
                );

        controlPanel
                .getScaleXSlider()
                .setValue(
                        currentObject.getScaleX()
                );

        controlPanel
                .getScaleYSlider()
                .setValue(
                        currentObject.getScaleY()
                );

        controlPanel
                .getTranslateXSlider()
                .setValue(
                        currentObject.getTranslateX()
                );

        controlPanel
                .getTranslateYSlider()
                .setValue(
                        currentObject.getTranslateY()
                );
    }

    // =========================================================
    // FORMAT VALUE
    // =========================================================

    private String formatValue(double value) {

        if (value == Math.rint(value)) {
            return String.valueOf(
                    (int) value
            );
        }

        return String.format(
                "%.2f",
                value
        );
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        launch(args);
    }
}