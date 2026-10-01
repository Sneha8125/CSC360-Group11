package com.example.geometricstyler.interaction;

import com.example.geometricstyler.util.Defaults;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;

/**
 * Keyboard shortcuts for moving, scaling, centring and resetting the object.
 *
 * <p>The handler is installed on the scene so it works wherever the user clicked,
 * but it steps aside while a text box, a slider or a colour picker has the focus.
 * Without that check, typing "5" into the width box would also move the object,
 * and the arrow keys would fight with the slider's own arrow key handling.</p>
 */
public class KeyboardController {

    private final Scene scene;
    private final ShortcutActions actions;

    public KeyboardController(Scene scene, ShortcutActions actions) {
        this.scene = scene;
        this.actions = actions;
    }

    public void install() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (isEditingControlFocused()) {
            return;
        }

        // Holding shift moves the object in bigger steps.
        double step = event.isShiftDown() ? Defaults.KEYBOARD_STEP_FAST : Defaults.KEYBOARD_STEP;

        switch (event.getCode()) {
            case UP:
                actions.moveBy(0, -step);
                break;
            case DOWN:
                actions.moveBy(0, step);
                break;
            case LEFT:
                actions.moveBy(-step, 0);
                break;
            case RIGHT:
                actions.moveBy(step, 0);
                break;
            case PLUS:
            case ADD:
            case EQUALS:
                actions.scaleBy(Defaults.KEYBOARD_SCALE_STEP);
                break;
            case MINUS:
            case SUBTRACT:
                actions.scaleBy(-Defaults.KEYBOARD_SCALE_STEP);
                break;
            case R:
                actions.resetAll();
                break;
            case C:
                actions.centerObject();
                break;
            default:
                return;   // any other key is left to the normal UI
        }
        event.consume();
    }

    /** True while the user is typing or using a control that needs the arrow keys itself. */
    private boolean isEditingControlFocused() {
        Node focused = scene.getFocusOwner();
        return focused instanceof TextInputControl
                || focused instanceof Slider
                || focused instanceof ComboBoxBase;
    }
}
