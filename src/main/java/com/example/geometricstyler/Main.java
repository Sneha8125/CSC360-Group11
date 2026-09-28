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

        // Create the main layout
        BorderPane root = new BorderPane();

        // Create UI sections
        ControlPanel controlPanel = new ControlPanel();
        PreviewPane previewPane = new PreviewPane();

        // ---------------------------------------------------------
        // Create scrollable control panel
        // ---------------------------------------------------------

        ScrollPane controlScrollPane = new ScrollPane();

        controlScrollPane.setContent(controlPanel);

        // Allow the control panel to use the available width
        controlScrollPane.setFitToWidth(true);

        // Keep the scrollbar available when the content is taller
        // than the application window
        controlScrollPane.setFitToHeight(false);

        // Remove horizontal scrolling
        controlScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        // Show vertical scrollbar when needed
        controlScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        // Set the width of the left control panel
        controlScrollPane.setPrefWidth(370);

        // ---------------------------------------------------------
        // Place sections in the BorderPane
        // ---------------------------------------------------------

        root.setLeft(controlScrollPane);
        root.setCenter(previewPane);

        // Create scene
        Scene scene = new Scene(root, 1100, 700);

        // Load CSS
        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        // Stage configuration
        stage.setTitle("Geometric Object Styler");

        stage.setScene(scene);

        stage.setMinWidth(900);
        stage.setMinHeight(600);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}