package com.example.geometricstyler.model;

/**
 * Border line style.
 *
 * <p>The dash pattern is stored as multiples of the stroke width so that a dashed
 * border keeps the same look when the user makes the border thicker.</p>
 */
public enum StrokeStyle {

    SOLID("Solid", new double[0]),
    DASHED("Dashed", new double[]{3.0, 2.0}),
    DOTTED("Dotted", new double[]{0.1, 2.0});

    private final String label;
    private final double[] dashPattern;

    StrokeStyle(String label, double[] dashPattern) {
        this.label = label;
        this.dashPattern = dashPattern;
    }

    public String getLabel() {
        return label;
    }

    public boolean isDashed() {
        return dashPattern.length > 0;
    }

    /** Dotted borders need round line caps, otherwise the dots look like tiny squares. */
    public boolean needsRoundCaps() {
        return this == DOTTED;
    }

    /**
     * Dash lengths in pixels for the given border thickness.
     *
     * @param strokeWidth current border width in pixels
     * @return a copy of the pattern scaled to the border width
     */
    public double[] dashesFor(double strokeWidth) {
        double scale = Math.max(1.0, strokeWidth);
        double[] dashes = new double[dashPattern.length];
        for (int i = 0; i < dashPattern.length; i++) {
            dashes[i] = dashPattern[i] * scale;
        }
        return dashes;
    }

    @Override
    public String toString() {
        return label;
    }
}
