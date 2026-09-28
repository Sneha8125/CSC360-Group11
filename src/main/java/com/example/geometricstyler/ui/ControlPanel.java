package com.example.geometricStyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    // Object
    private ComboBox<String> objectTypeComboBox;

    // Position
    private TextField xPositionField;
    private TextField yPositionField;

    // Dimensions
    private TextField widthField;
    private TextField heightField;

    // Transformation
    private Slider rotationSlider;
    private CheckBox keepScaleUniform;
    private Slider scaleXSlider;
    private Slider scaleYSlider;
    private Slider translateXSlider;
    private Slider translateYSlider;

    // Appearance
    private ColorPicker fillColorPicker;
    private ComboBox<String> fillTypeComboBox;
    private ColorPicker borderColorPicker;
    private Slider borderWidthSlider;
    private ComboBox<String> borderStyleComboBox;

    public ControlPanel() {

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        getStyleClass().add("control-panel");

        // ---------------------------------------------------------
        // TITLE
        // ---------------------------------------------------------

        Label title = new Label("GEOMETRIC OBJECT STYLER");
        title.getStyleClass().add("panel-title");


        // ---------------------------------------------------------
        // OBJECT SECTION
        // ---------------------------------------------------------

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


        // ---------------------------------------------------------
        // POSITION SECTION
        // ---------------------------------------------------------

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


        // ---------------------------------------------------------
        // DIMENSIONS SECTION
        // ---------------------------------------------------------

        Label dimensionsSection = new Label("DIMENSIONS");
        dimensionsSection.getStyleClass().add("section-title");

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


        // ---------------------------------------------------------
        // TRANSFORMATION SECTION
        // ---------------------------------------------------------

        Label transformationSection =
                new Label("TRANSFORMATION");

        transformationSection.getStyleClass()
                .add("section-title");


        // Rotation

        Label rotationLabel = new Label("Rotation");

        rotationSlider = new Slider(0, 360, 0);

        rotationSlider.setShowTickLabels(true);
        rotationSlider.setShowTickMarks(true);
        rotationSlider.setMajorTickUnit(90);
        rotationSlider.setBlockIncrement(15);
        rotationSlider.setMaxWidth(Double.MAX_VALUE);

        rotationSlider.setTooltip(
                new Tooltip("Rotate the object from 0 to 360 degrees")
        );


        // Keep Scale Uniform

        keepScaleUniform =
                new CheckBox("Keep scale uniform");

        keepScaleUniform.setSelected(true);

        keepScaleUniform.setTooltip(
                new Tooltip(
                        "Keep Scale X and Scale Y synchronized"
                )
        );


        // Scale X

        Label scaleXLabel = new Label("Scale X");

        scaleXSlider = new Slider(0.5, 2.0, 1.0);

        scaleXSlider.setShowTickLabels(true);
        scaleXSlider.setShowTickMarks(true);
        scaleXSlider.setMajorTickUnit(0.5);
        scaleXSlider.setBlockIncrement(0.1);
        scaleXSlider.setMaxWidth(Double.MAX_VALUE);

        scaleXSlider.setTooltip(
                new Tooltip("Scale the object horizontally")
        );


        // Scale Y

        Label scaleYLabel = new Label("Scale Y");

        scaleYSlider = new Slider(0.5, 2.0, 1.0);

        scaleYSlider.setShowTickLabels(true);
        scaleYSlider.setShowTickMarks(true);
        scaleYSlider.setMajorTickUnit(0.5);
        scaleYSlider.setBlockIncrement(0.1);
        scaleYSlider.setMaxWidth(Double.MAX_VALUE);

        scaleYSlider.setTooltip(
                new Tooltip("Scale the object vertically")
        );


        // Translate X

        Label translateXLabel = new Label("Translate X");

        translateXSlider = new Slider(-500, 500, 0);

        translateXSlider.setShowTickLabels(true);
        translateXSlider.setShowTickMarks(true);
        translateXSlider.setMajorTickUnit(250);
        translateXSlider.setBlockIncrement(10);
        translateXSlider.setMaxWidth(Double.MAX_VALUE);

        translateXSlider.setTooltip(
                new Tooltip("Move the object horizontally")
        );


        // Translate Y

        Label translateYLabel = new Label("Translate Y");

        translateYSlider = new Slider(-500, 500, 0);

        translateYSlider.setShowTickLabels(true);
        translateYSlider.setShowTickMarks(true);
        translateYSlider.setMajorTickUnit(250);
        translateYSlider.setBlockIncrement(10);
        translateYSlider.setMaxWidth(Double.MAX_VALUE);

        translateYSlider.setTooltip(
                new Tooltip("Move the object vertically")
        );


        // ---------------------------------------------------------
        // APPEARANCE SECTION
        // ---------------------------------------------------------

        Label appearanceSection =
                new Label("APPEARANCE");

        appearanceSection.getStyleClass()
                .add("section-title");


        // Fill Colour

        Label fillColorLabel =
                new Label("Fill colour");

        fillColorPicker = new ColorPicker();

        fillColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );

        fillColorPicker.setTooltip(
                new Tooltip(
                        "Choose the fill colour of the object"
                )
        );


        // Fill Type

        Label fillTypeLabel =
                new Label("Fill type");

        fillTypeComboBox = new ComboBox<>();

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


        // Border Colour

        Label borderColorLabel =
                new Label("Border colour");

        borderColorPicker = new ColorPicker();

        borderColorPicker.setMaxWidth(
                Double.MAX_VALUE
        );

        borderColorPicker.setTooltip(
                new Tooltip(
                        "Choose the border colour"
                )
        );


        // Border Width

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


        // Border Style

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


        // ---------------------------------------------------------
        // ADD ALL CONTROLS
        // ---------------------------------------------------------

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

                borderColorLabel,
                borderColorPicker,

                borderWidthLabel,
                borderWidthSlider,

                borderStyleLabel,
                borderStyleComboBox
        );
    }
}