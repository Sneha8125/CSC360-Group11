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

        Label title =
                new Label("GEOMETRIC OBJECT STYLER");

        title.getStyleClass()
                .add("panel-title");

        Label subtitle =
                new Label(
                        "Select and customize a single geometric object"
                );

        subtitle.getStyleClass()
                .add("panel-subtitle");


        // =====================================================
        // OBJECT
        // =====================================================

        Label objectSection =
                new Label("OBJECT");

        objectSection.getStyleClass()
                .add("section-title");

        objectTypeComboBox =
                new ComboBox<>();

        objectTypeComboBox.getItems().addAll(
                "Circle",
                "Rectangle",
                "Square",
                "Ellipse",
                "Polygon"
        );

        objectTypeComboBox.setValue("Circle");

        objectTypeComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        objectTypeComboBox.setTooltip(
                new Tooltip(
                        "Select the geometric object to style"
                )
        );


        // =====================================================
        // POSITION
        // =====================================================

        Label positionSection =
                new Label("POSITION");

        positionSection.getStyleClass()
                .add("section-title");

        xPositionField =
                new TextField("0");

        xPositionField.setPromptText("X");

        yPositionField =
                new TextField("0");

        yPositionField.setPromptText("Y");

        xPositionField.setTooltip(
                new Tooltip(
                        "Horizontal position"
                )
        );

        yPositionField.setTooltip(
                new Tooltip(
                        "Vertical position"
                )
        );

        HBox positionRow =
                new HBox(10);

        positionRow.getChildren().addAll(
                xPositionField,
                yPositionField
        );


        // =====================================================
        // DIMENSIONS
        // =====================================================

        Label dimensionsSection =
                new Label("DIMENSIONS");

        dimensionsSection.getStyleClass()
                .add("section-title");

        widthField =
                new TextField("100");

        widthField.setPromptText("Width");

        heightField =
                new TextField("100");

        heightField.setPromptText("Height");

        widthField.setTooltip(
                new Tooltip("Object width")
        );

        heightField.setTooltip(
                new Tooltip("Object height")
        );

        HBox dimensionsRow =
                new HBox(10);

        dimensionsRow.getChildren().addAll(
                widthField,
                heightField
        );


        // =====================================================
        // TRANSFORMATION
        // =====================================================

        Label transformationSection =
                new Label("TRANSFORMATION");

        transformationSection.getStyleClass()
                .add("section-title");


        // Rotation

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


        // Keep uniform

        keepScaleUniform =
                new CheckBox(
                        "Keep scale uniform"
                );

        keepScaleUniform.setSelected(true);


        // Scale X

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


        // Scale Y

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


        // Translate X

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


        // Translate Y

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


        // =====================================================
        // APPEARANCE
        // =====================================================

        Label appearanceSection =
                new Label("APPEARANCE");

        appearanceSection.getStyleClass()
                .add("section-title");


        // Fill

        Label fillColorLabel =
                new Label("Fill colour");

        fillColorPicker =
                new ColorPicker();

        fillColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );


        // Fill type

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


        // Gradient 1

        Label gradientColor1Label =
                new Label("Gradient colour 1");

        gradientColor1Picker =
                new ColorPicker();

        gradientColor1Picker.setMaxWidth(
                Double.MAX_VALUE
        );


        // Gradient 2

        Label gradientColor2Label =
                new Label("Gradient colour 2");

        gradientColor2Picker =
                new ColorPicker();

        gradientColor2Picker.setMaxWidth(
                Double.MAX_VALUE
        );


        // Border

        Label borderColorLabel =
                new Label("Border colour");

        borderColorPicker =
                new ColorPicker();

        borderColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );


        // Border width

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


        // Border style

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


        // Opacity

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


        // =====================================================
        // EFFECTS
        // =====================================================

        Label effectsSection =
                new Label("EFFECTS");

        effectsSection.getStyleClass()
                .add("section-title");

        dropShadowCheckBox =
                new CheckBox("Drop shadow");

        glowCheckBox =
                new CheckBox("Glow");


        // =====================================================
        // ACTIONS
        // =====================================================

        Label actionsSection =
                new Label("ACTIONS");

        actionsSection.getStyleClass()
                .add("section-title");


        centerButton =
                new Button("Center object");

        centerButton.setMaxWidth(
                Double.MAX_VALUE
        );


        flipHButton =
                new Button("Flip H");

        flipVButton =
                new Button("Flip V");


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


        applyButton =
                new Button("Apply");

        applyButton.getStyleClass()
        .add("apply-button");

        resetButton =
                new Button("Reset");


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
        // CURRENT OBJECT
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
        // LIVE INFORMATION LISTENERS
        // =====================================================

        objectTypeComboBox.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        xPositionField.textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        yPositionField.textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        widthField.textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateCurrentObjectInfo()
                );

        heightField.textProperty()
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
        // ADD CONTROLS
        // =====================================================

        getChildren().addAll(

                title,
                subtitle,

                objectSection,
                objectTypeComboBox,

                positionSection,
                positionRow,

                dimensionsSection,
                dimensionsRow,

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

                effectsSection,

                dropShadowCheckBox,
                glowCheckBox,

                actionsSection,

                centerButton,
                flipButtons,
                finalActionButtons,

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

        updateCurrentObjectInfo();
    }


    // =========================================================
    // INFORMATION ROW
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
    // UPDATE INFORMATION
    // =========================================================

    private void updateCurrentObjectInfo() {

        currentTypeValue.setText(
                objectTypeComboBox.getValue()
        );

        currentPositionValue.setText(
                "X: " + xPositionField.getText()
                        + "    Y: "
                        + yPositionField.getText()
        );

        currentSizeValue.setText(
                widthField.getText()
                        + " × "
                        + heightField.getText()
        );

        currentRotationValue.setText(
                String.format(
                        "%.0f°",
                        rotationSlider.getValue()
                )
        );

        currentScaleValue.setText(
                String.format(
                        "X: %.1f    Y: %.1f",
                        scaleXSlider.getValue(),
                        scaleYSlider.getValue()
                )
        );

        currentTranslationValue.setText(
                String.format(
                        "X: %.0f    Y: %.0f",
                        translateXSlider.getValue(),
                        translateYSlider.getValue()
                )
        );

        currentOpacityValue.setText(
                String.format(
                        "%.0f%%",
                        opacitySlider.getValue() * 100
                )
        );

        currentBorderValue.setText(
                String.format(
                        "%.0f px, %s",
                        borderWidthSlider.getValue(),
                        borderStyleComboBox.getValue()
                )
        );

        currentFillValue.setText(
                fillTypeComboBox.getValue()
        );

        currentStatusValue.setText(
                "Ready"
        );
    }
}