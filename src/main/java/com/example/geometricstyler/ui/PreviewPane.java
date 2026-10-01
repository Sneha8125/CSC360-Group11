package com.example.geometricstyler.ui;

import com.example.geometricstyler.model.GeometricObject;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * The drawing area on the right hand side.
 *
 * <p>It is a plain {@link Pane} so the object keeps exactly the layoutX / layoutY
 * the model gives it. The pane holds at most one child, which is how the
 * "only one object at a time" rule is enforced.</p>
 */
public class PreviewPane extends Pane {

    private static final double CORNER_RADIUS = 10;

    private GeometricObject object;

    public PreviewPane() {
        getStyleClass().add("preview-pane");
        setMinSize(320, 240);
        setPrefSize(700, 520);
        setFocusTraversable(true);

        // Anything dragged past the edge is cut off instead of painting over the panel.
        Rectangle clip = new Rectangle();
        clip.setArcWidth(CORNER_RADIUS * 2);
        clip.setArcHeight(CORNER_RADIUS * 2);
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);
    }

    /** Replaces the active object; the previous one is removed from the scene. */
    public void showObject(GeometricObject newObject) {
        getChildren().clear();
        this.object = newObject;
        if (newObject != null) {
            getChildren().add(newObject.getNode());
        }
    }

    public GeometricObject getObject() {
        return object;
    }

    /** Draws an accent border around the preview while the object is selected. */
    public void setSelected(boolean selected) {
        if (selected) {
            if (!getStyleClass().contains("selected")) {
                getStyleClass().add("selected");
            }
        } else {
            getStyleClass().remove("selected");
        }
    }

    public double getCenterX() {
        return getWidth() / 2;
    }

    public double getCenterY() {
        return getHeight() / 2;
    }
}
