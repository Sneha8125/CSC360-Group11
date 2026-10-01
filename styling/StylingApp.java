package styling;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Entry point for the Object Styling application.
 *
 * Creates the target shape, the styling workspace, and the styling panel,
 * then connects them together in the main application layout.
 */
public final class StylingApp extends Application {

    private static final double SHAPE_WIDTH = 220;
    private static final double SHAPE_HEIGHT = 150;

    private static final double CANVAS_PADDING = 40;
    private static final double CANVAS_WIDTH = 420;

    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 520;

    @Override
    public void start(Stage stage) {

        Rectangle shape = createShape();

        StackPane canvas = createCanvas(shape);

        StylingPanel stylingPanel = new StylingPanel(shape);

        BorderPane root = new BorderPane();

        root.setCenter(canvas);
        root.setRight(stylingPanel.getView());

        Scene scene = new Scene(
                root,
                WINDOW_WIDTH,
                WINDOW_HEIGHT
        );

        configureStage(stage, scene);
    }

    /**
     * Creates the shape that will be styled.
     */
    private Rectangle createShape() {
        Rectangle shape = new Rectangle(
                SHAPE_WIDTH,
                SHAPE_HEIGHT
        );

        shape.setArcWidth(12);
        shape.setArcHeight(12);

        return shape;
    }

    /**
     * Creates the workspace containing the target shape.
     */
    private StackPane createCanvas(Rectangle shape) {
        StackPane canvas = new StackPane(shape);

        canvas.setPadding(new Insets(CANVAS_PADDING));
        canvas.setPrefWidth(CANVAS_WIDTH);

        canvas.setStyle(
                "-fx-background-color: #f4f6f7;"
        );

        return canvas;
    }

    /**
     * Configures and displays the application window.
     */
    private void configureStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.setTitle("Object Styling");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}