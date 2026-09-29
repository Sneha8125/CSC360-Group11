package member4;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObjectInteractionControllerTest {

    @Test
    void setPositionUpdatesObjectPosition() {
        Rectangle rectangle = new Rectangle(100, 100);
        ObjectInteractionController controller =
                new ObjectInteractionController();

        controller.setPosition(rectangle, 120, 180);

        assertEquals(120, rectangle.getTranslateX());
        assertEquals(180, rectangle.getTranslateY());
    }

    @Test
    void invalidPositionThrowsException() {
        Rectangle rectangle = new Rectangle(100, 100);
        ObjectInteractionController controller =
                new ObjectInteractionController();

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.setPosition(rectangle, Double.NaN, 50)
        );
    }
}
