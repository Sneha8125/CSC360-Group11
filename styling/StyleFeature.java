package styling;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 * A single styling feature (fill, border, opacity, effects, ...).
 * Each implementation owns its own UI controls and knows how to:
 *   - render itself (getView)
 *   - apply its current settings onto a target shape (applyTo)
 *   - reset its controls to their default values (resetToDefault)
 *   - notify the panel when something changes (setOnChange)
 */
public interface StyleFeature {

    /** The UI section for this feature, to be placed in the styling panel. */
    Node getView();

    /** Apply this feature's current settings to the given shape. */
    void applyTo(Shape shape);

    /** Reset this feature's controls back to their default values. */
    void resetToDefault();

    /** Register a callback to run whenever a control in this feature changes. */
    void setOnChange(Runnable onChange);
}