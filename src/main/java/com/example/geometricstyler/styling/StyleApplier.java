package com.example.geometricstyler.styling;

import com.example.geometricstyler.model.GeometricObject;

/**
 * Convenience entry point that applies fill, stroke and effects in one call.
 * Used every time a styling control changes and after the object type is swapped.
 */
public final class StyleApplier {

    private StyleApplier() {
        // utility class, never instantiated
    }

    public static void applyAll(GeometricObject object) {
        FillManager.apply(object.getNode(), object.getStyle());
        StrokeManager.apply(object.getNode(), object.getStyle());
        EffectManager.apply(object.getNode(), object.getStyle());
    }
}
