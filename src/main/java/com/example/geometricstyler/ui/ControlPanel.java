package com.example.geometricstyler.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {

    // Constructor
    public ControlPanel() {

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        // Apply CSS class
        getStyleClass().add("control-panel");

        // Main title
        Label title = new Label("Geometric Object Styler");
        title.getStyleClass().add("panel-title");

        // Section headings
        Label objectSection = new Label("OBJECT");
        objectSection.getStyleClass().add("section-title");

        Label positionSection = new Label("POSITION");
        positionSection.getStyleClass().add("section-title");

        Label transformationSection = new Label("TRANSFORMATION");
        transformationSection.getStyleClass().add("section-title");

        Label appearanceSection = new Label("APPEARANCE");
        appearanceSection.getStyleClass().add("section-title");

        // Stage 1 placeholders
        Label objectPlaceholder = new Label("Object controls will be added later");
        objectPlaceholder.getStyleClass().add("placeholder-label");

        Label positionPlaceholder = new Label("Position controls will be added later");
        positionPlaceholder.getStyleClass().add("placeholder-label");

        Label transformationPlaceholder =
                new Label("Transformation controls will be added later");
        transformationPlaceholder.getStyleClass().add("placeholder-label");

        Label appearancePlaceholder =
                new Label("Appearance controls will be added later");
        appearancePlaceholder.getStyleClass().add("placeholder-label");

        // Add everything to the panel
        getChildren().addAll(
                title,

                objectSection,
                objectPlaceholder,

                positionSection,
                positionPlaceholder,

                transformationSection,
                transformationPlaceholder,

                appearanceSection,
                appearancePlaceholder
        );
    }
}