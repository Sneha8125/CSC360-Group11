package com.example.geometricstyler.object;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ShapeType;
import javafx.scene.shape.Polygon;

/**
 * A predefined five point star, rebuilt to fit the current width and height.
 *
 * <p>The points are calculated on a circle: every second point uses a smaller
 * radius, which is what produces the star shape.</p>
 */
public class PolygonObject extends GeometricObject {

    private static final int POINT_COUNT = 5;
    private static final double OUTER_RADIUS = 0.5;
    private static final double INNER_RADIUS = 0.2;

    private final Polygon polygon;

    public PolygonObject() {
        this(new Polygon());
    }

    private PolygonObject(Polygon polygon) {
        super(ShapeType.POLYGON, polygon);
        this.polygon = polygon;
    }

    @Override
    protected void applyGeometry(double width, double height) {
        Double[] points = new Double[POINT_COUNT * 4];
        int index = 0;

        for (int step = 0; step < POINT_COUNT * 2; step++) {
            // Start at the top (-90 degrees) and walk around the circle.
            double angle = Math.toRadians(-90 + step * 180.0 / POINT_COUNT);
            double radius = (step % 2 == 0) ? OUTER_RADIUS : INNER_RADIUS;
            points[index++] = width / 2 + radius * width * Math.cos(angle);
            points[index++] = height / 2 + radius * height * Math.sin(angle);
        }

        polygon.getPoints().setAll(points);
    }
}
