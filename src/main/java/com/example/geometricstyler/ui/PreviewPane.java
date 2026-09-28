package com.example.geometricStyler.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PreviewPane extends StackPane {

    private final StackPane objectContainer;
    private final Label previewHint;

    public PreviewPane() {

        getStyleClass().add("preview-pane");
        setAlignment(Pos.CENTER);

        Label previewTitle = new Label("PREVIEW");
        previewTitle.getStyleClass().add("preview-title");

        objectContainer = new StackPane();
        objectContainer.setPrefSize(600, 500);
        objectContainer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        previewHint = new Label("Object preview area");
        previewHint.getStyleClass().add("preview-hint");

        VBox previewContent = new VBox(15);
        previewContent.setAlignment(Pos.CENTER);

        previewContent.getChildren().addAll(
                previewTitle,
                objectContainer,
                previewHint
        );

        getChildren().add(previewContent);
    }

    /**
     * Displays the current geometric object in the preview area.
     */
    public void setObject(Node object) {

        objectContainer.getChildren().clear();

        if (object != null) {
            objectContainer.getChildren().add(object);
            previewHint.setText("Object preview");
        } else {
            previewHint.setText("Object preview area");
        }
    }

    /**
     * Removes the current object from the preview.
     */
    public void clearObject() {
        objectContainer.getChildren().clear();
        previewHint.setText("Object preview area");
    }
}