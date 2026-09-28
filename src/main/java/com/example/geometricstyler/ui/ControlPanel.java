package com.example.geometricStyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Slider;

public class ControlPanel extends VBox {

    private ComboBox<String> objectTypeComboBox;
    private TextField xPositionField;
    private TextField yPositionField;
    private TextField widthField;
    private TextField heightField;
    private Slider rotationSlider;
    private Slider scaleSlider;

    public ControlPanel() {

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        getStyleClass().add("control-panel");

        // Main title
        Label title = new Label("GEOMETRIC OBJECT STYLER");
        title.getStyleClass().add("panel-title");

        // Object section
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

        // Position section
        Label positionSection = new Label("POSITION");
        positionSection.getStyleClass().add("section-title");

        xPositionField = new TextField("0");
        xPositionField.setPromptText("X");
        xPositionField.setPrefWidth(100);

        yPositionField = new TextField("0");
        yPositionField.setPromptText("Y");
        yPositionField.setPrefWidth(100);

        HBox positionRow = new HBox(10);
        positionRow.getChildren().addAll(
        xPositionField,
        yPositionField
);

Label dimensionsSection = new Label("DIMENSIONS");
dimensionsSection.getStyleClass().add("section-title");

widthField = new TextField("100");
widthField.setPromptText("Width");
widthField.setPrefWidth(100);

heightField = new TextField("100");
heightField.setPromptText("Height");
heightField.setPrefWidth(100);

HBox dimensionsRow = new HBox(10);
dimensionsRow.getChildren().addAll(
        widthField,
        heightField
);


        // Transformation section
        Label transformationSection = new Label("TRANSFORMATION");
        transformationSection.getStyleClass().add("section-title");

        Label rotationLabel = new Label("Rotation");

        rotationSlider = new Slider(0, 360, 0);
        rotationSlider.setShowTickLabels(true);
        rotationSlider.setShowTickMarks(true);
        rotationSlider.setMajorTickUnit(90);
        rotationSlider.setBlockIncrement(15);
        rotationSlider.setMaxWidth(Double.MAX_VALUE);

        Label scaleLabel = new Label("Scale");

        scaleSlider = new Slider(0.5, 2.0, 1.0);
        scaleSlider.setShowTickLabels(true);
        scaleSlider.setShowTickMarks(true);
        scaleSlider.setMajorTickUnit(0.5);
        scaleSlider.setBlockIncrement(0.1);
        scaleSlider.setMaxWidth(Double.MAX_VALUE);

        // Appearance section
        Label appearanceSection = new Label("APPEARANCE");
        appearanceSection.getStyleClass().add("section-title");

        Label appearancePlaceholder =
                new Label("Appearance controls will be added next.");

        appearancePlaceholder.getStyleClass()
                .add("placeholder-label");

        getChildren().addAll(
                title,

                objectSection,
                objectTypeComboBox,

                positionSection,
                positionRow,

                dimensionsSection,
                dimensionsRow,

                transformationSection,
                
                rotationLabel,
                rotationSlider,

                scaleLabel,
                scaleSlider,

                appearanceSection,
                appearancePlaceholder
        );
    }
}