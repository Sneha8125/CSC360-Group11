package com.example.geometricstyler.interaction;

/**
 * The actions the keyboard shortcuts can trigger.
 * Implemented by the controller, which keeps the key handling free of any
 * knowledge about the model.
 */
public interface ShortcutActions {

    /** Moves the object by the given amount of pixels. */
    void moveBy(double deltaX, double deltaY);

    /** Adds the given amount to both scale values. */
    void scaleBy(double delta);

    /** Puts every setting back to its default. */
    void resetAll();

    /** Moves the object to the middle of the preview. */
    void centerObject();
}
