package com.example.geometricstyler.model;

/**
 * The geometric objects the user can choose from.
 * Only one of these is active in the preview at any time.
 */
public enum ShapeType {

    CIRCLE("Circle"),
    RECTANGLE("Rectangle"),
    SQUARE("Square"),
    ELLIPSE("Ellipse"),
    POLYGON("Polygon");

    private final String label;

    ShapeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /** ComboBox shows this text. */
    @Override
    public String toString() {
        return label;
    }
}
