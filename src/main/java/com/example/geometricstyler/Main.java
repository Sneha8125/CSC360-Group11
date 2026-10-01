package com.example.geometricstyler;

import com.example.geometricstyler.app.StylerController;
import com.example.geometricstyler.ui.ControlPanel;
import com.example.geometricstyler.ui.PreviewPane;
import com.example.geometricstyler.ui.PropertyView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Geometric Object Styler - CSC360 Computer Graphics and Digital Image Processing.
 *
 * <p>This class only sets up the JavaFX application lifecycle and the outer
 * layout. All behaviour lives in the controller, the model and the ui classes,
 * which keeps this file small and keeps merge conflicts between the four feature
 * branches to a minimum.</p>
 */
public class Main extends Application {

    private static final String TITLE = "Geometric Object Styler";
    private static final double WINDOW_WIDTH = 1100;
    private static final double WINDOW_HEIGHT = 700;
    private static final double MIN_WINDOW_WIDTH = 900;
    private static final double MIN_WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        ControlPanel controlPanel = new ControlPanel();
        PreviewPane previewPane = new PreviewPane();
        PropertyView propertyView = new PropertyView();

        // The preview takes every extra pixel; the property strip keeps its height.
        VBox workArea = new VBox(12, previewPane, propertyView);
        workArea.getStyleClass().add("work-area");
        VBox.setVgrow(previewPane, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.getStyleClass().add("app-root");
        root.setTop(createHeader());
        root.setLeft(controlPanel);
        root.setCenter(workArea);

        StylerController controller = new StylerController(controlPanel, previewPane, propertyView);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        applyStylesheet(scene);
        controller.start(scene);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMinWidth(MIN_WINDOW_WIDTH);
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.show();
    }

    private HBox createHeader() {
        Label title = new Label(TITLE);
        title.getStyleClass().add("app-title");

        Label hint = new Label("Drag the object, or use the arrow keys, + and -, C to centre, R to reset");
        hint.getStyleClass().add("app-hint");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox(12, title, spacer, hint);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("app-header");
        return header;
    }

    private void applyStylesheet(Scene scene) {
        URL stylesheet = getClass().getResource("/style.css");
        if (stylesheet != null) {
            scene.getStylesheets().add(stylesheet.toExternalForm());
        } else {
            System.err.println("style.css was not found on the classpath, using default JavaFX styling");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
