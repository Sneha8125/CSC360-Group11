package com.example.geometricstyler;

import com.example.geometricstyler.ui.ControlPanel;
import com.example.geometricstyler.ui.PreviewPane;
import javafx.application.Application;
import javafx.scene.Scene;
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

        // Place sections in the BorderPane
        root.setLeft(controlPanel);
        root.setCenter(previewPane);

        // Create scene
        Scene scene = new Scene(root, 1100, 700);

        // Load CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
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