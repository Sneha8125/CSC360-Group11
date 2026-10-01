package com.example.geometricstyler.styling;

import com.example.geometricstyler.model.ObjectStyle;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;

/**
 * Turns the fill settings of an {@link ObjectStyle} into a JavaFX {@link Paint}.
 *
 * <p>All gradients are built with proportional coordinates (0.0 - 1.0), which
 * means the same gradient works for any object size without recalculating it.</p>
 */
public final class FillManager {

    private FillManager() {
        // utility class, never instantiated
    }

    /** Applies the fill and the opacity to the object. */
    public static void apply(Shape shape, ObjectStyle style) {
        shape.setFill(createPaint(style));
        shape.setOpacity(style.getOpacity());
    }

    /** Builds the paint described by the style: a colour or one of the gradients. */
    public static Paint createPaint(ObjectStyle style) {
        Stop[] stops = {
                new Stop(0, style.getGradientStart()),
                new Stop(1, style.getGradientEnd())
        };

        switch (style.getFillType()) {
            case LINEAR_GRADIENT:
                // Diagonal gradient: top-left (0,0) to bottom-right (1,1).
                return new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);

            case RADIAL_GRADIENT:
                // Centre of the object, radius 0.6 so the end colour reaches the edges.
                return new RadialGradient(0, 0, 0.5, 0.5, 0.6, true, CycleMethod.NO_CYCLE, stops);

            case SOLID:
            default:
                return style.getFillColor();
        }
    }
}
