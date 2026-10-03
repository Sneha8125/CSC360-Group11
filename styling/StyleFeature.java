package styling;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 * Defines a reusable styling feature for a JavaFX Shape.
 *
 * <p>Each implementation is responsible for its own UI controls,
 * styling logic, default values, and change notifications.</p>
 *
 * <p>Examples include fill, border, opacity, and effects.</p>
 */
public interface StyleFeature {

    /**
     * Returns the UI controls used to configure this feature.
     *
     * @return the feature's JavaFX view
     */
    Node getView();

    /**
     * Applies the feature's current settings to the target shape.
     *
     * @param shape the shape to style
     */
    void applyTo(Shape shape);

    /**
     * Resets the feature's controls to their default values.
     */
    void resetToDefault();

    /**
     * Registers a callback that is invoked whenever the feature's
     * styling settings change.
     *
     * @param onChange callback to invoke when the feature changes
     */
    void setOnChange(Runnable onChange);
}