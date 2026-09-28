package com.example.geometricStyler.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PreviewPane extends StackPane {

    public PreviewPane() {

        getStyleClass().add("preview-pane");

        setAlignment(Pos.CENTER);

        // ---------------------------------------------------------
        // Preview heading
        // ---------------------------------------------------------

        Label previewTitle =
                new Label("PREVIEW");

        previewTitle.getStyleClass()
                .add("preview-title");


        // ---------------------------------------------------------
        // Placeholder object
        // ---------------------------------------------------------

        StackPane objectPreview =
                new StackPane();

        objectPreview.setPrefSize(
                180,
                180
        );

        objectPreview.setMaxSize(
                180,
                180
        );

        objectPreview.getStyleClass()
                .add("object-preview");


        // ---------------------------------------------------------
        // Preview information
        // ---------------------------------------------------------

        Label previewHint =
                new Label(
                        "Object preview area"
                );

        previewHint.getStyleClass()
                .add("preview-hint");


        VBox previewContent =
                new VBox(15);

        previewContent.setAlignment(
                Pos.CENTER
        );

        previewContent.getChildren().addAll(
                previewTitle,
                objectPreview,
                previewHint
        );


        getChildren().add(
                previewContent
        );
    }
}