package com.example.geometricStyler.styling;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

public class EffectManager {

    private boolean dropShadowEnabled = false;
    private boolean glowEnabled = false;

    public void setDropShadowEnabled(boolean enabled) {
        dropShadowEnabled = enabled;
    }

    public boolean isDropShadowEnabled() {
        return dropShadowEnabled;
    }

    public void setGlowEnabled(boolean enabled) {
        glowEnabled = enabled;
    }

    public boolean isGlowEnabled() {
        return glowEnabled;
    }

    public void applyEffects(Node node) {

        if (node == null) {
            return;
        }

        Effect effect = null;

        if (dropShadowEnabled) {

            DropShadow shadow = new DropShadow();

            shadow.setColor(Color.GRAY);

            shadow.setRadius(12);

            shadow.setSpread(0.15);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            effect = shadow;
        }

        if (glowEnabled) {

            Glow glow = new Glow();

            glow.setLevel(0.8);

            if (effect != null) {
                glow.setInput(effect);
            }

            effect = glow;
        }

        node.setEffect(effect);
    }

    public void clearEffects(Node node) {

        if (node != null) {
            node.setEffect(null);
        }
    }

    public void reset(Node node) {

        dropShadowEnabled = false;

        glowEnabled = false;

        clearEffects(node);
    }
}