package member4;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Reusable interaction layer for Member 4.
 * Provides mouse dragging, keyboard movement, Apply/Reset,
 * UI-to-object synchronization, and basic error handling.
 */
public class ObjectInteractionController {

    private double mouseOffsetX;
    private double mouseOffsetY;

    private double initialX;
    private double initialY;
    private double initialRotation;
    private double initialScaleX = 1.0;
    private double initialScaleY = 1.0;

    private Node selectedObject;

    /** Select an object and remember its initial transform. */
    public void selectObject(Node object) {
        if (object == null) return;

        selectedObject = object;
        rememberInitialState(object);
        object.requestFocus();
    }

    /** Enable mouse drag movement. */
    public void enableMouseDragging(Node object) {
        if (object == null) return;

        object.setOnMousePressed(event -> {
            selectIfNeeded(object);
            mouseOffsetX = event.getSceneX() - object.getTranslateX();
            mouseOffsetY = event.getSceneY() - object.getTranslateY();
        });

        object.setOnMouseDragged(event -> {
            double x = event.getSceneX() - mouseOffsetX;
            double y = event.getSceneY() - mouseOffsetY;
            setPosition(object, x, y);
        });
    }

    /** Enable arrow-key movement. */
    public void enableKeyboardControls(Node object, double step) {
        if (object == null) return;

        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step)) {
            step = 10;
        }

        final double movement = step;
        object.setFocusTraversable(true);

        object.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            double x = object.getTranslateX();
            double y = object.getTranslateY();

            if (event.getCode() == KeyCode.LEFT) x -= movement;
            else if (event.getCode() == KeyCode.RIGHT) x += movement;
            else if (event.getCode() == KeyCode.UP) y -= movement;
            else if (event.getCode() == KeyCode.DOWN) y += movement;
            else return;

            setPosition(object, x, y);
            event.consume();
        });
    }

    /**
     * Connect the control panel to the selected JavaFX object.
     * X/Y are read from text fields; rotation, scale and opacity
     * come from sliders; color comes from ColorPicker.
     */
    public void connectUI(
            Node object,
            TextField xField,
            TextField yField,
            Slider rotationSlider,
            Slider scaleSlider,
            Slider opacitySlider,
            ColorPicker colorPicker,
            Button applyButton,
            Button resetButton) {

        if (object == null || applyButton == null || resetButton == null) {
            return;
        }

        selectObject(object);

        applyButton.setOnAction(event -> {
            try {
                double x = parseNumber(xField, object.getTranslateX());
                double y = parseNumber(yField, object.getTranslateY());

                setPosition(object, x, y);

                if (rotationSlider != null) {
                    object.setRotate(rotationSlider.getValue());
                }

                if (scaleSlider != null) {
                    double scale = validateScale(scaleSlider.getValue());
                    object.setScaleX(scale);
                    object.setScaleY(scale);
                }

                if (opacitySlider != null) {
                    object.setOpacity(clamp(opacitySlider.getValue(), 0, 1));
                }

                if (colorPicker != null && object instanceof Shape shape) {
                    Color color = colorPicker.getValue();
                    if (color != null) shape.setFill(color);
                }

            } catch (NumberFormatException ex) {
                showError("Invalid input",
                        "Please enter valid numeric X and Y values.");
            } catch (RuntimeException ex) {
                showError("Could not apply changes", ex.getMessage());
            }
        });

        resetButton.setOnAction(event -> reset(object));
    }

    /** Restore the selected object's initial transform. */
    public void reset(Node object) {
        if (object == null) return;

        object.setTranslateX(initialX);
        object.setTranslateY(initialY);
        object.setRotate(initialRotation);
        object.setScaleX(initialScaleX);
        object.setScaleY(initialScaleY);
        object.setOpacity(1.0);
    }

    /** Safely set object position. */
    public void setPosition(Node object, double x, double y) {
        if (object == null) return;

        if (Double.isNaN(x) || Double.isInfinite(x)
                || Double.isNaN(y) || Double.isInfinite(y)) {
            throw new IllegalArgumentException(
                    "Position must contain valid numbers.");
        }

        object.setTranslateX(x);
        object.setTranslateY(y);
    }

    private void selectIfNeeded(Node object) {
        if (selectedObject != object) selectObject(object);
    }

    private void rememberInitialState(Node object) {
        initialX = object.getTranslateX();
        initialY = object.getTranslateY();
        initialRotation = object.getRotate();
        initialScaleX = object.getScaleX();
        initialScaleY = object.getScaleY();
    }

    private double parseNumber(TextField field, double defaultValue) {
        if (field == null || field.getText() == null
                || field.getText().trim().isEmpty()) {
            return defaultValue;
        }
        return Double.parseDouble(field.getText().trim());
    }

    private double validateScale(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value <= 0) {
            return 1.0;
        }
        return value;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(
                message == null ? "An unexpected error occurred." : message);
        alert.showAndWait();
    }
}
