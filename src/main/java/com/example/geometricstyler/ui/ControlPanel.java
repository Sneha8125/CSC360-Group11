package com.example.geometricStyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {

    // ---------------------------------------------------------
    // OBJECT
    // ---------------------------------------------------------

    private ComboBox<String> objectTypeComboBox;

    // ---------------------------------------------------------
    // POSITION
    // ---------------------------------------------------------

    private TextField xPositionField;
    private TextField yPositionField;

    // ---------------------------------------------------------
    // DIMENSIONS
    // ---------------------------------------------------------

    private TextField widthField;
    private TextField heightField;

    // ---------------------------------------------------------
    // TRANSFORMATION
    // ---------------------------------------------------------

    private Slider rotationSlider;
    private CheckBox keepScaleUniform;
    private Slider scaleXSlider;
    private Slider scaleYSlider;
    private Slider translateXSlider;
    private Slider translateYSlider;

    // ---------------------------------------------------------
    // APPEARANCE
    // ---------------------------------------------------------

    private ColorPicker fillColorPicker;
    private ComboBox<String> fillTypeComboBox;

    private ColorPicker gradientColor1Picker;
    private ColorPicker gradientColor2Picker;

    private ColorPicker borderColorPicker;
    private Slider borderWidthSlider;
    private ComboBox<String> borderStyleComboBox;
    private Slider opacitySlider;

    // ---------------------------------------------------------
    // EFFECTS
    // ---------------------------------------------------------

    private CheckBox dropShadowCheckBox;
    private CheckBox glowCheckBox;

    // ---------------------------------------------------------
    // ACTIONS
    // ---------------------------------------------------------

    private Button centerButton;
    private Button flipHButton;
    private Button flipVButton;
    private Button applyButton;
    private Button resetButton;

    // ---------------------------------------------------------
    // CURRENT OBJECT INFORMATION
    // ---------------------------------------------------------

    private Label currentTypeValue;
    private Label currentPositionValue;
    private Label currentSizeValue;
    private Label currentRotationValue;
    private Label currentScaleValue;
    private Label currentTranslationValue;
    private Label currentOpacityValue;
    private Label currentBorderValue;
    private Label currentFillValue;
    private Label currentStatusValue;

    public ControlPanel() {

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        getStyleClass().add("control-panel");

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label("GEOMETRIC OBJECT STYLER");
        title.getStyleClass().add("panel-title");


        // =====================================================
        // OBJECT SECTION
        // =====================================================

        Label objectSection = new Label("OBJECT");
        objectSection.getStyleClass().add("section-title");

        objectTypeComboBox = new ComboBox<>();

        objectTypeComboBox.getItems().addAll(
                "Circle",
                "Rectangle",
                "Square",
                "Ellipse",
                "Polygon"
        );

        objectTypeComboBox.setValue("Circle");
        objectTypeComboBox.setMaxWidth(Double.MAX_VALUE);
        objectTypeComboBox.setPromptText("Select Object");

        objectTypeComboBox.setTooltip(
                new Tooltip("Select the geometric object to style")
        );


        // =====================================================
        // POSITION SECTION
        // =====================================================

        Label positionSection = new Label("POSITION");
        positionSection.getStyleClass().add("section-title");

        xPositionField = new TextField("0");
        xPositionField.setPromptText("X");
        xPositionField.setPrefWidth(100);

        yPositionField = new TextField("0");
        yPositionField.setPromptText("Y");
        yPositionField.setPrefWidth(100);

        xPositionField.setTooltip(
                new Tooltip("Horizontal position of the object")
        );

        yPositionField.setTooltip(
                new Tooltip("Vertical position of the object")
        );

        HBox positionRow = new HBox(10);

        positionRow.getChildren().addAll(
                xPositionField,
                yPositionField
        );


        // =====================================================
        // DIMENSIONS SECTION
        // =====================================================

        Label dimensionsSection =
                new Label("DIMENSIONS");

        dimensionsSection.getStyleClass()
                .add("section-title");

        widthField = new TextField("100");
        widthField.setPromptText("Width");
        widthField.setPrefWidth(100);

        heightField = new TextField("100");
        heightField.setPromptText("Height");
        heightField.setPrefWidth(100);

        widthField.setTooltip(
                new Tooltip("Width of the selected object")
        );

        heightField.setTooltip(
                new Tooltip("Height of the selected object")
        );

        HBox dimensionsRow = new HBox(10);

        dimensionsRow.getChildren().addAll(
                widthField,
                heightField
        );


        // =====================================================
        // TRANSFORMATION SECTION
        // =====================================================

        Label transformationSection =
                new Label("TRANSFORMATION");

        transformationSection.getStyleClass()
                .add("section-title");


        // -------------------------
        // Rotation
        // -------------------------

        Label rotationLabel =
                new Label("Rotation");

        rotationSlider =
                new Slider(0, 360, 0);

        rotationSlider.setShowTickLabels(true);
        rotationSlider.setShowTickMarks(true);
        rotationSlider.setMajorTickUnit(90);
        rotationSlider.setBlockIncrement(15);
        rotationSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        rotationSlider.setTooltip(
                new Tooltip(
                        "Rotate the object from 0 to 360 degrees"
                )
        );


        // -------------------------
        // Keep Scale Uniform
        // -------------------------

        keepScaleUniform =
                new CheckBox("Keep scale uniform");

        keepScaleUniform.setSelected(true);

        keepScaleUniform.setTooltip(
                new Tooltip(
                        "Keep Scale X and Scale Y synchronized"
                )
        );


        // -------------------------
        // Scale X
        // -------------------------

        Label scaleXLabel =
                new Label("Scale X");

        scaleXSlider =
                new Slider(0.5, 2.0, 1.0);

        scaleXSlider.setShowTickLabels(true);
        scaleXSlider.setShowTickMarks(true);
        scaleXSlider.setMajorTickUnit(0.5);
        scaleXSlider.setBlockIncrement(0.1);
        scaleXSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        scaleXSlider.setTooltip(
                new Tooltip(
                        "Scale the object horizontally"
                )
        );


        // -------------------------
        // Scale Y
        // -------------------------

        Label scaleYLabel =
                new Label("Scale Y");

        scaleYSlider =
                new Slider(0.5, 2.0, 1.0);

        scaleYSlider.setShowTickLabels(true);
        scaleYSlider.setShowTickMarks(true);
        scaleYSlider.setMajorTickUnit(0.5);
        scaleYSlider.setBlockIncrement(0.1);
        scaleYSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        scaleYSlider.setTooltip(
                new Tooltip(
                        "Scale the object vertically"
                )
        );


        // -------------------------
        // Translate X
        // -------------------------

        Label translateXLabel =
                new Label("Translate X");

        translateXSlider =
                new Slider(-500, 500, 0);

        translateXSlider.setShowTickLabels(true);
        translateXSlider.setShowTickMarks(true);
        translateXSlider.setMajorTickUnit(250);
        translateXSlider.setBlockIncrement(10);
        translateXSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        translateXSlider.setTooltip(
                new Tooltip(
                        "Move the object horizontally"
                )
        );


        // -------------------------
        // Translate Y
        // -------------------------

        Label translateYLabel =
                new Label("Translate Y");

        translateYSlider =
                new Slider(-500, 500, 0);

        translateYSlider.setShowTickLabels(true);
        translateYSlider.setShowTickMarks(true);
        translateYSlider.setMajorTickUnit(250);
        translateYSlider.setBlockIncrement(10);
        translateYSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        translateYSlider.setTooltip(
                new Tooltip(
                        "Move the object vertically"
                )
        );


        // =====================================================
        // APPEARANCE SECTION
        // =====================================================

        Label appearanceSection =
                new Label("APPEARANCE");

        appearanceSection.getStyleClass()
                .add("section-title");


        // -------------------------
        // Fill Colour
        // -------------------------

        Label fillColorLabel =
                new Label("Fill colour");

        fillColorPicker =
                new ColorPicker();

        fillColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );

        fillColorPicker.setTooltip(
                new Tooltip(
                        "Choose the fill colour of the object"
                )
        );


        // -------------------------
        // Fill Type
        // -------------------------

        Label fillTypeLabel =
                new Label("Fill type");

        fillTypeComboBox =
                new ComboBox<>();

        fillTypeComboBox.getItems().addAll(
                "Solid",
                "Linear Gradient",
                "Radial Gradient"
        );

        fillTypeComboBox.setValue("Solid");

        fillTypeComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        fillTypeComboBox.setTooltip(
                new Tooltip(
                        "Choose the type of fill"
                )
        );


        // -------------------------
        // Gradient Colour 1
        // -------------------------

        Label gradientColor1Label =
                new Label("Gradient colour 1");

        gradientColor1Picker =
                new ColorPicker();

        gradientColor1Picker.setMaxWidth(
                Double.MAX_VALUE
        );

        gradientColor1Picker.setTooltip(
                new Tooltip(
                        "Choose the first gradient colour"
                )
        );


        // -------------------------
        // Gradient Colour 2
        // -------------------------

        Label gradientColor2Label =
                new Label("Gradient colour 2");

        gradientColor2Picker =
                new ColorPicker();

        gradientColor2Picker.setMaxWidth(
                Double.MAX_VALUE
        );

        gradientColor2Picker.setTooltip(
                new Tooltip(
                        "Choose the second gradient colour"
                )
        );


        // -------------------------
        // Border Colour
        // -------------------------

        Label borderColorLabel =
                new Label("Border colour");

        borderColorPicker =
                new ColorPicker();

        borderColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );

        borderColorPicker.setTooltip(
                new Tooltip(
                        "Choose the border colour"
                )
        );


        // -------------------------
        // Border Width
        // -------------------------

        Label borderWidthLabel =
                new Label("Border width");

        borderWidthSlider =
                new Slider(0, 20, 3);

        borderWidthSlider.setShowTickLabels(true);
        borderWidthSlider.setShowTickMarks(true);
        borderWidthSlider.setMajorTickUnit(5);
        borderWidthSlider.setBlockIncrement(1);
        borderWidthSlider.setMaxWidth(
                Double.MAX_VALUE
        );

        borderWidthSlider.setTooltip(
                new Tooltip(
                        "Set the border width"
                )
        );


        // -------------------------
        // Border Style
        // -------------------------

        Label borderStyleLabel =
                new Label("Border style");

        borderStyleComboBox =
                new ComboBox<>();

        borderStyleComboBox.getItems().addAll(
                "Solid",
                "Dashed",
                "Dotted"
        );

        borderStyleComboBox.setValue("Solid");

        borderStyleComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        borderStyleComboBox.setTooltip(
                new Tooltip(
                        "Choose the border style"
                )
        );


        // -------------------------
        // Opacity
        // -------------------------

        Label opacityLabel =
                new Label("Opacity");

        opacitySlider =
                new Slider(0, 1, 1);

        opacitySlider.setShowTickLabels(true);
        opacitySlider.setShowTickMarks(true);
        opacitySlider.setMajorTickUnit(0.25);
        opacitySlider.setBlockIncrement(0.1);
        opacitySlider.setMaxWidth(
                Double.MAX_VALUE
        );

        opacitySlider.setTooltip(
                new Tooltip(
                        "Set the object opacity"
                )
        );


        // =====================================================
        // EFFECTS SECTION
        // =====================================================

        Label effectsSection =
                new Label("EFFECTS");

        effectsSection.getStyleClass()
                .add("section-title");


        dropShadowCheckBox =
                new CheckBox("Drop shadow");

        dropShadowCheckBox.setTooltip(
                new Tooltip(
                        "Apply a drop shadow to the object"
                )
        );


        glowCheckBox =
                new CheckBox("Glow");

        glowCheckBox.setTooltip(
                new Tooltip(
                        "Apply a glow effect to the object"
                )
        );


        // =====================================================
        // ACTIONS SECTION
        // =====================================================

        Label actionsSection =
                new Label("ACTIONS");

        actionsSection.getStyleClass()
                .add("section-title");


        // -------------------------
        // Center
        // -------------------------

        centerButton =
                new Button("Center object");

        centerButton.setMaxWidth(
                Double.MAX_VALUE
        );

        centerButton.setTooltip(
                new Tooltip(
                        "Move the object to the centre of the preview"
                )
        );


        // -------------------------
        // Flip H
        // -------------------------

        flipHButton =
                new Button("Flip H");

        flipHButton.setMaxWidth(
                Double.MAX_VALUE
        );

        flipHButton.setTooltip(
                new Tooltip(
                        "Flip the object horizontally"
                )
        );


        // -------------------------
        // Flip V
        // -------------------------

        flipVButton =
                new Button("Flip V");

        flipVButton.setMaxWidth(
                Double.MAX_VALUE
        );

        flipVButton.setTooltip(
                new Tooltip(
                        "Flip the object vertically"
                )
        );


        HBox flipButtons =
                new HBox(10);

        flipButtons.getChildren().addAll(
                flipHButton,
                flipVButton
        );

        HBox.setHgrow(
                flipHButton,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox.setHgrow(
                flipVButton,
                javafx.scene.layout.Priority.ALWAYS
        );


        // -------------------------
        // Apply
        // -------------------------

        applyButton =
                new Button("Apply");

        applyButton.setMaxWidth(
                Double.MAX_VALUE
        );

        applyButton.setTooltip(
                new Tooltip(
                        "Apply the selected styling and transformations"
                )
        );


        // -------------------------
        // Reset
        // -------------------------

        resetButton =
                new Button("Reset");

        resetButton.setMaxWidth(
                Double.MAX_VALUE
        );

        resetButton.setTooltip(
                new Tooltip(
                        "Reset the object controls to their default values"
                )
        );


        HBox finalActionButtons =
                new HBox(10);

        finalActionButtons.getChildren().addAll(
                applyButton,
                resetButton
        );

        HBox.setHgrow(
                applyButton,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox.setHgrow(
                resetButton,
                javafx.scene.layout.Priority.ALWAYS
        );


        // =====================================================
        // CURRENT OBJECT INFORMATION
        // =====================================================

        Label currentObjectSection =
                new Label("CURRENT OBJECT");

        currentObjectSection.getStyleClass()
                .add("section-title");


        currentTypeValue =
                new Label("Circle");

        currentPositionValue =
                new Label("X: 0    Y: 0");

        currentSizeValue =
                new Label("100 × 100");

        currentRotationValue =
                new Label("0°");

        currentScaleValue =
                new Label("X: 1.0    Y: 1.0");

        currentTranslationValue =
                new Label("X: 0    Y: 0");

        currentOpacityValue =
                new Label("100%");

        currentBorderValue =
                new Label("3 px, Solid");

        currentFillValue =
                new Label("Solid");

        currentStatusValue =
                new Label("Ready");

        currentStatusValue.getStyleClass()
                .add("status-label");


        // -------------------------
        // Information Rows
        // -------------------------

        HBox typeRow =
                createInfoRow(
                        "Type",
                        currentTypeValue
                );

        HBox positionInfoRow =
                createInfoRow(
                        "Position",
                        currentPositionValue
                );

        HBox sizeRow =
                createInfoRow(
                        "Size",
                        currentSizeValue
                );

        HBox rotationInfoRow =
                createInfoRow(
                        "Rotation",
                        currentRotationValue
                );

        HBox scaleInfoRow =
                createInfoRow(
                        "Scale",
                        currentScaleValue
                );

        HBox translationInfoRow =
                createInfoRow(
                        "Translation",
                        currentTranslationValue
                );

        HBox opacityInfoRow =
                createInfoRow(
                        "Opacity",
                        currentOpacityValue
                );

        HBox borderInfoRow =
                createInfoRow(
                        "Border",
                        currentBorderValue
                );

        HBox fillInfoRow =
                createInfoRow(
                        "Fill",
                        currentFillValue
                );

        HBox statusRow =
                createInfoRow(
                        "Status",
                        currentStatusValue
                );


        // =====================================================
        // CONTROL LISTENERS
        // =====================================================

        objectTypeComboBox.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        rotationSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        scaleXSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        scaleYSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        translateXSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        translateYSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        opacitySlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        borderWidthSlider.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        borderStyleComboBox.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        fillTypeComboBox.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );


        // =====================================================
        // ADD ALL CONTROLS
        // =====================================================

        getChildren().addAll(

                // Title
                title,

                // Object
                objectSection,
                objectTypeComboBox,

                // Position
                positionSection,
                positionRow,

                // Dimensions
                dimensionsSection,
                dimensionsRow,

                // Transformation
                transformationSection,

                rotationLabel,
                rotationSlider,

                keepScaleUniform,

                scaleXLabel,
                scaleXSlider,

                scaleYLabel,
                scaleYSlider,

                translateXLabel,
                translateXSlider,

                translateYLabel,
                translateYSlider,

                // Appearance
                appearanceSection,

                fillColorLabel,
                fillColorPicker,

                fillTypeLabel,
                fillTypeComboBox,

                gradientColor1Label,
                gradientColor1Picker,

                gradientColor2Label,
                gradientColor2Picker,

                borderColorLabel,
                borderColorPicker,

                borderWidthLabel,
                borderWidthSlider,

                borderStyleLabel,
                borderStyleComboBox,

                opacityLabel,
                opacitySlider,

                // Effects
                effectsSection,

                dropShadowCheckBox,
                glowCheckBox,

                // Actions
                actionsSection,

                centerButton,
                flipButtons,
                finalActionButtons,

                // Current Object
                currentObjectSection,

                typeRow,
                positionInfoRow,
                sizeRow,
                rotationInfoRow,
                scaleInfoRow,
                translationInfoRow,
                opacityInfoRow,
                borderInfoRow,
                fillInfoRow,
                statusRow
        );

        // Initialize information display
        updateCurrentObjectInfo();
    }


    // =========================================================
    // CREATE INFORMATION ROW
    // =========================================================

    private HBox createInfoRow(
            String name,
            Label value
    ) {

        Label nameLabel =
                new Label(name);

        nameLabel.getStyleClass()
                .add("info-label");

        value.getStyleClass()
                .add("info-value");

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.getChildren().addAll(
                nameLabel,
                value
        );

        return row;
    }


    // =========================================================
    // UPDATE CURRENT OBJECT INFORMATION
    // =========================================================

    private void updateCurrentObjectInfo() {

        // Type
        currentTypeValue.setText(
                objectTypeComboBox.getValue()
        );


        // Position
        String xPosition =
                xPositionField.getText();

        String yPosition =
                yPositionField.getText();

        currentPositionValue.setText(
                "X: " + xPosition +
                "    Y: " + yPosition
        );


        // Size
        String width =
                widthField.getText();

        String height =
                heightField.getText();

        currentSizeValue.setText(
                width + " × " + height
        );


        // Rotation
        currentRotationValue.setText(
                String.format(
                        "%.0f°",
                        rotationSlider.getValue()
                )
        );


        // Scale
        currentScaleValue.setText(
                String.format(
                        "X: %.1f    Y: %.1f",
                        scaleXSlider.getValue(),
                        scaleYSlider.getValue()
                )
        );


        // Translation
        currentTranslationValue.setText(
                String.format(
                        "X: %.0f    Y: %.0f",
                        translateXSlider.getValue(),
                        translateYSlider.getValue()
                )
        );


        // Opacity
        currentOpacityValue.setText(
                String.format(
                        "%.0f%%",
                        opacitySlider.getValue() * 100
                )
        );


        // Border
        currentBorderValue.setText(
                String.format(
                        "%.0f px, %s",
                        borderWidthSlider.getValue(),
                        borderStyleComboBox.getValue()
                )
        );


        // Fill
        currentFillValue.setText(
                fillTypeComboBox.getValue()
        );


        // Status
        currentStatusValue.setText(
                "Ready"
        );
    }
}