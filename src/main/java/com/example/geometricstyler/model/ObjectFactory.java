package com.example.geometricStyler.model;

public class ObjectFactory {

    private ObjectFactory() {
    }

    public static GeometricObject create(String type) {

        return switch (type) {

            case "Circle" ->
                    new CircleObject();

            case "Rectangle" ->
                    new RectangleObject();

            case "Square" ->
                    new SquareObject();

            case "Ellipse" ->
                    new EllipseObject();

            case "Polygon" ->
                    new PolygonObject();

            default ->
                    new CircleObject();
        };
    }
}