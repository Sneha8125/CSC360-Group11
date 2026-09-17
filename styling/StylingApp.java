package styling;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Entry point. Wires the single styled object (a Rectangle) together with
 * the StylingPanel, which owns all the individual feature controls.
 */
public class StylingApp extends Application {

    @Override
    public void start(Stage stage) {
        Rectangle shape = new Rectangle(220, 150);
        shape.setArcWidth(12);
        shape.setArcHeight(12);

        StackPane canvas = new StackPane(shape);
        canvas.setPadding(new Insets(40));
        canvas.setStyle("-fx-background-color: #f4f6f7;");
        canvas.setPrefWidth(420);

        StylingPanel panel = new StylingPanel(shape);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setRight(panel.getView());

        stage.setScene(new Scene(root, 800, 520));
        stage.setTitle("Object Styling");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}