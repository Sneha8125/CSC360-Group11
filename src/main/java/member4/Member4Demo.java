package member4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** Standalone demonstration/test screen for Member 4 functionality. */
public class Member4Demo extends Application {

    @Override
    public void start(Stage stage) {
        Pane preview = new Pane();
        preview.setPrefSize(700, 500);

        Rectangle rectangle = new Rectangle(140, 90, Color.DODGERBLUE);
        rectangle.setTranslateX(250);
        rectangle.setTranslateY(180);
        preview.getChildren().add(rectangle);

        TextField xField = new TextField("250");
        TextField yField = new TextField("180");
        Slider rotation = new Slider(0, 360, 0);
        Slider scale = new Slider(0.5, 2.0, 1.0);
        Slider opacity = new Slider(0, 1, 1);
        ColorPicker colorPicker = new ColorPicker(Color.DODGERBLUE);

        Button apply = new Button("Apply");
        Button reset = new Button("Reset");

        VBox controls = new VBox(
                10,
                new Label("X Position"), xField,
                new Label("Y Position"), yField,
                new Label("Rotation"), rotation,
                new Label("Scale"), scale,
                new Label("Opacity"), opacity,
                new Label("Fill Color"), colorPicker,
                new HBox(10, apply, reset)
        );
        controls.setPadding(new Insets(15));
        controls.setPrefWidth(220);

        ObjectInteractionController controller =
                new ObjectInteractionController();

        controller.selectObject(rectangle);
        controller.enableMouseDragging(rectangle);
        controller.enableKeyboardControls(rectangle, 10);
        controller.connectUI(
                rectangle, xField, yField, rotation, scale,
                opacity, colorPicker, apply, reset
        );

        BorderPane root = new BorderPane();
        root.setLeft(controls);
        root.setCenter(preview);

        Scene scene = new Scene(root, 950, 550);
        stage.setTitle("CSC360 - Member 4 Interaction Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
