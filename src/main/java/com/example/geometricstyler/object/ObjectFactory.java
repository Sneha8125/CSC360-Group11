package com.example.geometricstyler.object;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ShapeType;
import com.example.geometricstyler.util.Defaults;

/**
 * Creates the one active geometric object.
 *
 * <p>The rest of the application never calls a shape constructor directly, so
 * adding a new shape type only means adding a class and one line here.</p>
 */
public final class ObjectFactory {

    private ObjectFactory() {
        // utility class, never instantiated
    }

    public static GeometricObject create(ShapeType type) {
        ShapeType safeType = (type == null) ? Defaults.SHAPE : type;
        switch (safeType) {
            case RECTANGLE:
                return new RectangleObject();
            case SQUARE:
                return new SquareObject();
            case ELLIPSE:
                return new EllipseObject();
            case POLYGON:
                return new PolygonObject();
            case CIRCLE:
            default:
                return new CircleObject();
        }
    }

    /** Creates an object and immediately gives it a position and a size. */
    public static GeometricObject create(ShapeType type, double x, double y,
                                         double width, double height) {
        GeometricObject object = create(type);
        object.initialise(x, y, width, height);
        return object;
    }
}
