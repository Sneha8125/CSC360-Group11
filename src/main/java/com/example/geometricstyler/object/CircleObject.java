package com.example.geometricstyler.object;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ShapeType;
import javafx.scene.shape.Circle;

/**
 * A circle. Width and height are kept equal because a circle has a single radius.
 */
public class CircleObject extends GeometricObject {

    private final Circle circle;

    public CircleObject() {
        this(new Circle());
    }

    private CircleObject(Circle circle) {
        super(ShapeType.CIRCLE, circle);
        this.circle = circle;
    }

    @Override
    protected boolean keepsEqualSides() {
        return true;
    }

    @Override
    protected void applyGeometry(double width, double height) {
        // The circle sits in the middle of its own (width x height) box.
        circle.setCenterX(width / 2);
        circle.setCenterY(height / 2);
        circle.setRadius(Math.min(width, height) / 2);
    }
}
