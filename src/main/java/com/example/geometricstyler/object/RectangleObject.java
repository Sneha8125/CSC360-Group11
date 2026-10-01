package com.example.geometricstyler.object;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ShapeType;
import javafx.scene.shape.Rectangle;

/**
 * A rectangle with an independent width and height.
 */
public class RectangleObject extends GeometricObject {

    private final Rectangle rectangle;

    public RectangleObject() {
        this(ShapeType.RECTANGLE, new Rectangle());
    }

    /** Used by SquareObject, which is a rectangle with equal sides. */
    protected RectangleObject(ShapeType type, Rectangle rectangle) {
        super(type, rectangle);
        this.rectangle = rectangle;
    }

    @Override
    protected void applyGeometry(double width, double height) {
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }
}
