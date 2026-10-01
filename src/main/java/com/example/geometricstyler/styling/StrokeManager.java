package com.example.geometricstyler.styling;

import com.example.geometricstyler.model.ObjectStyle;
import com.example.geometricstyler.model.StrokeStyle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

/**
 * Applies the border settings: colour, width and line style.
 * The stroke is completely independent from the fill.
 */
public final class StrokeManager {

    private StrokeManager() {
        // utility class, never instantiated
    }

    public static void apply(Shape shape, ObjectStyle style) {
        shape.setStroke(style.getStrokeColor());
        shape.setStrokeWidth(style.getStrokeWidth());
        applyDashes(shape, style);
    }

    /** Solid borders use an empty dash array; dashed and dotted use a pattern. */
    private static void applyDashes(Shape shape, ObjectStyle style) {
        StrokeStyle strokeStyle = style.getStrokeStyle();
        shape.setStrokeLineCap(strokeStyle.needsRoundCaps() ? StrokeLineCap.ROUND : StrokeLineCap.BUTT);
        shape.getStrokeDashArray().clear();

        if (!strokeStyle.isDashed()) {
            return;
        }
        for (double dash : strokeStyle.dashesFor(style.getStrokeWidth())) {
            shape.getStrokeDashArray().add(dash);
        }
    }
}
