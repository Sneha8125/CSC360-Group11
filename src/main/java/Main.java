import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Simple JavaFX test application for Member 4 Week 1.
 * Demonstrates mouse dragging of an object.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Pane previewPane = new Pane();

        Rectangle rectangle = new Rectangle(100, 100, 150, 100);
        rectangle.setFill(Color.DODGERBLUE);

        ObjectInteractionController interactionController =
                new ObjectInteractionController();

        interactionController.enableDragging(rectangle);

        previewPane.getChildren().add(rectangle);

        Scene scene = new Scene(previewPane, 800, 600);

        stage.setTitle("CSC360 - Member 4 Week 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
