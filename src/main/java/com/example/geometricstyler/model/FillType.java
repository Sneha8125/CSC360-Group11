package com.example.geometricstyler.model;

/**
 * How the interior of the object is painted.
 * SOLID uses the fill colour, the two gradient types use the start/end colours.
 */
public enum FillType {

    SOLID("Solid"),
    LINEAR_GRADIENT("Linear gradient"),
    RADIAL_GRADIENT("Radial gradient");

    private final String label;

    FillType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /** True when the user should be able to pick start/end gradient colours. */
    public boolean usesGradientColors() {
        return this != SOLID;
    }

    @Override
    public String toString() {
        return label;
    }
}
