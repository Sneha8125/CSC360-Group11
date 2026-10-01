package com.example.geometricstyler.util;

import java.util.OptionalDouble;

/**
 * Small validation helpers used everywhere the user can type or drag a value.
 *
 * <p>The rule in this project is simple: no user input ever reaches the model
 * without passing through this class, so a bad value can never crash the app.</p>
 */
public final class ValidationUtil {

    private ValidationUtil() {
        // utility class, never instantiated
    }

    /**
     * Forces a value into the allowed range.
     * NaN and infinity are replaced by the minimum, because they can never be drawn.
     */
    public static double clamp(double value, double min, double max) {
        if (!isUsableNumber(value)) {
            return min;
        }
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /** True when the number is neither NaN nor infinite. */
    public static boolean isUsableNumber(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }

    /** True when the value is inside the range (inclusive). */
    public static boolean isInRange(double value, double min, double max) {
        return isUsableNumber(value) && value >= min && value <= max;
    }

    /**
     * Reads a number typed by the user.
     *
     * @return the parsed value, or an empty result when the text is blank,
     *         not a number, or not usable for drawing
     */
    public static OptionalDouble parseNumber(String text) {
        if (text == null) {
            return OptionalDouble.empty();
        }
        String trimmed = text.trim().replace(',', '.');
        if (trimmed.isEmpty()) {
            return OptionalDouble.empty();
        }
        try {
            double value = Double.parseDouble(trimmed);
            return isUsableNumber(value) ? OptionalDouble.of(value) : OptionalDouble.empty();
        } catch (NumberFormatException ex) {
            return OptionalDouble.empty();
        }
    }

    /** Width and height must stay positive and inside the allowed size range. */
    public static double sanitizeSize(double size) {
        return clamp(size, Defaults.MIN_SIZE, Defaults.MAX_SIZE);
    }

    /** Scale must never be zero or negative, otherwise the object disappears or mirrors. */
    public static double sanitizeScale(double scale) {
        return clamp(Math.abs(scale), Defaults.MIN_SCALE, Defaults.MAX_SCALE);
    }

    /** Opacity is stored as 0.0 - 1.0 even though the UI shows 0 - 100 %. */
    public static double sanitizeOpacity(double opacity) {
        return clamp(opacity, 0.0, 1.0);
    }

    /** Rotation is normalised into 0 - 360 degrees. */
    public static double sanitizeRotation(double degrees) {
        if (!isUsableNumber(degrees)) {
            return Defaults.ROTATION;
        }
        double normalised = degrees % 360;
        if (normalised < 0) {
            normalised += 360;
        }
        return normalised;
    }

    /** Formats a number for status messages and the property display. */
    public static String format(double value, int decimals) {
        return String.format("%." + decimals + "f", value);
    }
}
