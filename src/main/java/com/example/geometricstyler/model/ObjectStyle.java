package com.example.geometricstyler.model;

import com.example.geometricstyler.util.Defaults;
import com.example.geometricstyler.util.ValidationUtil;
import javafx.scene.paint.Color;

/**
 * All appearance settings of the active object.
 *
 * <p>This class stores <em>what</em> the object should look like. Turning these
 * values into real JavaFX paints, strokes and effects is the job of the classes
 * in the {@code styling} package.</p>
 */
public class ObjectStyle {

    private FillType fillType = Defaults.FILL_TYPE;
    private Color fillColor = Defaults.FILL;
    private Color gradientStart = Defaults.GRADIENT_START;
    private Color gradientEnd = Defaults.GRADIENT_END;

    private Color strokeColor = Defaults.STROKE;
    private double strokeWidth = Defaults.STROKE_WIDTH;
    private StrokeStyle strokeStyle = Defaults.STROKE_STYLE;

    /** Stored as 0.0 - 1.0; the UI shows it as a percentage. */
    private double opacity = Defaults.OPACITY;

    private boolean dropShadowEnabled = Defaults.DROP_SHADOW;
    private boolean glowEnabled = Defaults.GLOW;

    /** Puts every appearance value back to the defaults listed in the assignment. */
    public void resetToDefaults() {
        fillType = Defaults.FILL_TYPE;
        fillColor = Defaults.FILL;
        gradientStart = Defaults.GRADIENT_START;
        gradientEnd = Defaults.GRADIENT_END;
        strokeColor = Defaults.STROKE;
        strokeWidth = Defaults.STROKE_WIDTH;
        strokeStyle = Defaults.STROKE_STYLE;
        opacity = Defaults.OPACITY;
        dropShadowEnabled = Defaults.DROP_SHADOW;
        glowEnabled = Defaults.GLOW;
    }

    /** Independent copy, used when the Apply button commits the current settings. */
    public ObjectStyle copy() {
        ObjectStyle copy = new ObjectStyle();
        copy.fillType = fillType;
        copy.fillColor = fillColor;
        copy.gradientStart = gradientStart;
        copy.gradientEnd = gradientEnd;
        copy.strokeColor = strokeColor;
        copy.strokeWidth = strokeWidth;
        copy.strokeStyle = strokeStyle;
        copy.opacity = opacity;
        copy.dropShadowEnabled = dropShadowEnabled;
        copy.glowEnabled = glowEnabled;
        return copy;
    }

    /* ----- getters and validated setters ----- */

    public FillType getFillType() {
        return fillType;
    }

    public void setFillType(FillType fillType) {
        if (fillType != null) {
            this.fillType = fillType;
        }
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        if (fillColor != null) {
            this.fillColor = fillColor;
        }
    }

    public Color getGradientStart() {
        return gradientStart;
    }

    public void setGradientStart(Color gradientStart) {
        if (gradientStart != null) {
            this.gradientStart = gradientStart;
        }
    }

    public Color getGradientEnd() {
        return gradientEnd;
    }

    public void setGradientEnd(Color gradientEnd) {
        if (gradientEnd != null) {
            this.gradientEnd = gradientEnd;
        }
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        if (strokeColor != null) {
            this.strokeColor = strokeColor;
        }
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = ValidationUtil.clamp(strokeWidth,
                Defaults.MIN_STROKE_WIDTH, Defaults.MAX_STROKE_WIDTH);
    }

    public StrokeStyle getStrokeStyle() {
        return strokeStyle;
    }

    public void setStrokeStyle(StrokeStyle strokeStyle) {
        if (strokeStyle != null) {
            this.strokeStyle = strokeStyle;
        }
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = ValidationUtil.sanitizeOpacity(opacity);
    }

    public int getOpacityPercent() {
        return (int) Math.round(opacity * 100);
    }

    public boolean isDropShadowEnabled() {
        return dropShadowEnabled;
    }

    public void setDropShadowEnabled(boolean dropShadowEnabled) {
        this.dropShadowEnabled = dropShadowEnabled;
    }

    public boolean isGlowEnabled() {
        return glowEnabled;
    }

    public void setGlowEnabled(boolean glowEnabled) {
        this.glowEnabled = glowEnabled;
    }
}
