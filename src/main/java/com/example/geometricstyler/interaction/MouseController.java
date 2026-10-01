package com.example.geometricstyler.interaction;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.ui.PreviewPane;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

import java.util.function.Consumer;

/**
 * Mouse selection and dragging for the single active object.
 *
 * <p>The object is grabbed at the exact point the user clicked, so it does not
 * jump to the cursor. The grab offset is measured against the <i>effective</i>
 * position (base position plus translation), which is where the object is really
 * drawn.</p>
 */
public class MouseController {

    /** Called while dragging with the new effective centre of the object. */
    public interface DragHandler {
        void onDragTo(double effectiveX, double effectiveY);
    }

    private final PreviewPane previewPane;
    private final DragHandler dragHandler;
    private final Consumer<Boolean> selectionHandler;
    private final Runnable dragFinishedHandler;

    private double grabOffsetX;
    private double grabOffsetY;

    public MouseController(PreviewPane previewPane,
                           DragHandler dragHandler,
                           Consumer<Boolean> selectionHandler,
                           Runnable dragFinishedHandler) {
        this.previewPane = previewPane;
        this.dragHandler = dragHandler;
        this.selectionHandler = selectionHandler;
        this.dragFinishedHandler = dragFinishedHandler;
    }

    /** Clicking the empty background deselects the object. */
    public void install() {
        previewPane.setOnMousePressed(event -> {
            selectionHandler.accept(false);
            previewPane.requestFocus();
            event.consume();
        });
    }

    /** Adds the handlers to a newly created object. */
    public void attachTo(GeometricObject object) {
        Shape node = object.getNode();
        node.setCursor(Cursor.OPEN_HAND);

        node.setOnMousePressed(event -> {
            Point2D point = toPreviewCoordinates(event.getSceneX(), event.getSceneY());
            grabOffsetX = object.getEffectiveX() - point.getX();
            grabOffsetY = object.getEffectiveY() - point.getY();

            selectionHandler.accept(true);
            node.setCursor(Cursor.CLOSED_HAND);
            previewPane.requestFocus();
            // Stops the background handler above from deselecting straight away.
            event.consume();
        });

        node.setOnMouseDragged(event -> {
            Point2D point = toPreviewCoordinates(event.getSceneX(), event.getSceneY());
            dragHandler.onDragTo(point.getX() + grabOffsetX, point.getY() + grabOffsetY);
            event.consume();
        });

        node.setOnMouseReleased(event -> {
            node.setCursor(Cursor.OPEN_HAND);
            dragFinishedHandler.run();
            event.consume();
        });
    }

    private Point2D toPreviewCoordinates(double sceneX, double sceneY) {
        return previewPane.sceneToLocal(sceneX, sceneY);
    }
}
