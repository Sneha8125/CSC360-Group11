package com.example.geometricstyler.object;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ShapeType;
import javafx.scene.shape.Ellipse;

/**
 * An ellipse: width and height control the two radii independently.
 */
public class EllipseObject extends GeometricObject {

    private final Ellipse ellipse;

    public EllipseObject() {
        this(new Ellipse());
    }

    private EllipseObject(Ellipse ellipse) {
        super(ShapeType.ELLIPSE, ellipse);
        this.ellipse = ellipse;
    }

    @Override
    protected void applyGeometry(double width, double height) {
        ellipse.setCenterX(width / 2);
        ellipse.setCenterY(height / 2);
        ellipse.setRadiusX(width / 2);
        ellipse.setRadiusY(height / 2);
    }
}
