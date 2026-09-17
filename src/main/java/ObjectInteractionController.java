import javafx.scene.Node;

/**
 * Handles mouse interaction for movable JavaFX objects.
 * Week 1 contribution: mouse press and drag functionality.
 */
public class ObjectInteractionController {

    private double mouseX;
    private double mouseY;

    /**
     * Enables click-and-drag movement for a JavaFX node.
     *
     * @param object the JavaFX object that should be draggable
     */
    public void enableDragging(Node object) {

        object.setOnMousePressed(event -> {
            mouseX = event.getSceneX() - object.getTranslateX();
            mouseY = event.getSceneY() - object.getTranslateY();
        });

        object.setOnMouseDragged(event -> {
            object.setTranslateX(event.getSceneX() - mouseX);
            object.setTranslateY(event.getSceneY() - mouseY);
        });
    }
}
