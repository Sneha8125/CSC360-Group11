package com.example.geometricStyler.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class PreviewPane extends StackPane {

    // Constructor
    public PreviewPane() {

        // Apply CSS class
        getStyleClass().add("preview-pane");

        // Preview area title
        Label previewTitle = new Label("PREVIEW AREA");
        previewTitle.getStyleClass().add("preview-title");

        // Center the label
        setAlignment(Pos.CENTER);

        // Add label
        getChildren().add(previewTitle);
    }
}