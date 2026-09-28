package com.example.geometricStyler;

import com.example.geometricStyler.ui.ControlPanel;
import com.example.geometricStyler.ui.PreviewPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // ---------------------------------------------------------
        // MAIN LAYOUT
        // ---------------------------------------------------------

        BorderPane root = new BorderPane();

        // ---------------------------------------------------------
        // CREATE UI SECTIONS
        // ---------------------------------------------------------

        ControlPanel controlPanel = new ControlPanel();
        PreviewPane previewPane = new PreviewPane();

        // ---------------------------------------------------------
        // SCROLLABLE CONTROL PANEL
        // ---------------------------------------------------------

        ScrollPane controlScrollPane = new ScrollPane();

        controlScrollPane.setContent(controlPanel);

        controlScrollPane.setFitToWidth(true);
        controlScrollPane.setFitToHeight(false);

        controlScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        controlScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        controlScrollPane.setPrefWidth(370);

        controlScrollPane.getStyleClass()
                .add("control-scroll-pane");

        // ---------------------------------------------------------
        // PLACE UI SECTIONS
        // ---------------------------------------------------------

        root.setLeft(controlScrollPane);
        root.setCenter(previewPane);

        // ---------------------------------------------------------
        // CREATE SCENE
        // ---------------------------------------------------------

        Scene scene = new Scene(
                root,
                1100,
                700
        );

        // ---------------------------------------------------------
        // LOAD CSS
        // ---------------------------------------------------------

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        // ---------------------------------------------------------
        // STAGE CONFIGURATION
        // ---------------------------------------------------------

        stage.setTitle(
                "Geometric Object Styler"
        );

        stage.setScene(scene);

        stage.setMinWidth(900);
        stage.setMinHeight(600);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}