import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Square extends Application {

    @Override
    public void start(Stage stage) {

        // Canvas size
        double canvasWidth = 600;
        double canvasHeight = 400;

        // Shape dimensions
        double width = 430;
        double height = 260;

        // Position of the shape
        double x = (canvasWidth - width) / 2;
        double y = (canvasHeight - height) / 2;

        // Create canvas
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        // Get graphics context
        GraphicsContext g = canvas.getGraphicsContext2D();

        // White background
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        // Black continuous lines
        g.setStroke(Color.BLACK);
        g.setLineWidth(5);

        // Top
        g.strokeLine(x, y, x + width, y);

        // Right
        g.strokeLine(x + width, y,
                     x + width, y + height);

        // Bottom
        g.strokeLine(x + width, y + height,
                     x, y + height);

        // Left
        g.strokeLine(x, y + height,
                     x, y);

        // Display
        Scene scene = new Scene(canvas);

        stage.setTitle("Geometric Object");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}