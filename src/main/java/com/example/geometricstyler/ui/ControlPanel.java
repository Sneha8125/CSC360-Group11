package com.example.geometricStyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControlPanel extends VBox {

    private ComboBox<String> objectTypeComboBox;
    private TextField xPositionField;
    private TextField yPositionField;

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

        // Transformation section
        Label transformationSection = new Label("TRANSFORMATION");
        transformationSection.getStyleClass().add("section-title");

        Label transformationPlaceholder =
                new Label("Transformation controls will be added next.");

        transformationPlaceholder.getStyleClass()
                .add("placeholder-label");

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

                transformationSection,
                transformationPlaceholder,

                appearanceSection,
                appearancePlaceholder
        );
    }
}