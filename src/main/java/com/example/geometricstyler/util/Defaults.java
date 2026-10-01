package com.example.geometricstyler.util;

import com.example.geometricstyler.model.FillType;
import com.example.geometricstyler.model.ShapeType;
import com.example.geometricstyler.model.StrokeStyle;
import javafx.scene.paint.Color;

/**
 * Every default value and every allowed range used by the application.
 *
 * <p>Keeping them in one place means the sliders, the validation code and the
 * Reset button can never disagree with each other.</p>
 */
public final class Defaults {

    private Defaults() {
        // utility class, never instantiated
    }

    /* ----- object ----- */
    public static final ShapeType SHAPE = ShapeType.CIRCLE;
    public static final double WIDTH = 100;
    public static final double HEIGHT = 100;

    /* ----- transformation ----- */
    public static final double ROTATION = 0;
    public static final double SCALE = 1.0;
    public static final double TRANSLATION = 0;

    /* ----- appearance ----- */
    public static final Color FILL = Color.LIGHTBLUE;
    public static final Color GRADIENT_START = Color.web("#7FD4F2");
    public static final Color GRADIENT_END = Color.web("#12506B");
    public static final Color STROKE = Color.BLACK;
    public static final double STROKE_WIDTH = 3;
    public static final double OPACITY = 1.0;
    public static final FillType FILL_TYPE = FillType.SOLID;
    public static final StrokeStyle STROKE_STYLE = StrokeStyle.SOLID;
    public static final boolean DROP_SHADOW = false;
    public static final boolean GLOW = false;

    /* ----- allowed ranges (used by sliders and by ValidationUtil) ----- */
    public static final double MIN_SIZE = 10;
    public static final double MAX_SIZE = 400;
    public static final double MIN_SCALE = 0.1;
    public static final double MAX_SCALE = 3.0;
    public static final double MIN_TRANSLATION = -250;
    public static final double MAX_TRANSLATION = 250;
    public static final double MIN_ROTATION = 0;
    public static final double MAX_ROTATION = 360;
    public static final double MIN_STROKE_WIDTH = 0;
    public static final double MAX_STROKE_WIDTH = 20;
    public static final double MIN_OPACITY_PERCENT = 0;
    public static final double MAX_OPACITY_PERCENT = 100;

    /* ----- interaction ----- */
    public static final double KEYBOARD_STEP = 5;
    public static final double KEYBOARD_STEP_FAST = 20;
    public static final double KEYBOARD_SCALE_STEP = 0.1;
}
