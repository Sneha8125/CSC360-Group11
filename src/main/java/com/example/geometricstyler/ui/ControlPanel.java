package com.example.geometricStyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {

    private ComboBox<String> objectTypeComboBox;

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

        Label positionPlaceholder =
                new Label("Position controls will be added next.");

        positionPlaceholder.getStyleClass().add("placeholder-label");

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
                positionPlaceholder,

                transformationSection,
                transformationPlaceholder,

                appearanceSection,
                appearancePlaceholder
        );
    }
}