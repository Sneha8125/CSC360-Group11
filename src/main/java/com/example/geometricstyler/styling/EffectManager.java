package com.example.geometricstyler.styling;

import com.example.geometricstyler.model.ObjectStyle;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Applies the optional visual effects.
 *
 * <p>JavaFX allows only one effect per node, so when both effects are switched on
 * they are chained: the glow takes the shadow as its input.</p>
 */
public final class EffectManager {

    private static final double SHADOW_RADIUS = 14;
    private static final double SHADOW_OFFSET = 5;
    private static final double GLOW_LEVEL = 0.85;

    private EffectManager() {
        // utility class, never instantiated
    }

    public static void apply(Shape shape, ObjectStyle style) {
        Effect effect = null;

        if (style.isDropShadowEnabled()) {
            effect = createDropShadow();
        }
        if (style.isGlowEnabled()) {
            Glow glow = new Glow(GLOW_LEVEL);
            glow.setInput(effect);   // null input simply means "no chained effect"
            effect = glow;
        }

        // Setting null removes the effect again.
        shape.setEffect(effect);
    }

    private static DropShadow createDropShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(SHADOW_RADIUS);
        shadow.setOffsetX(SHADOW_OFFSET);
        shadow.setOffsetY(SHADOW_OFFSET);
        shadow.setColor(Color.rgb(15, 23, 42, 0.45));
        return shadow;
    }
}
