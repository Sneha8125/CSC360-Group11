package com.example.geometricStyler.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PolygonObject extends GeometricObject {

    private final Polygon polygon;

    public PolygonObject() {

        super(250, 250, 150, 150);

        polygon = new Polygon(
                0, -75,
                75, 75,
                -75, 75
        );

        polygon.setFill(Color.LIGHTBLUE);
        polygon.setStroke(Color.DODGERBLUE);
        polygon.setStrokeWidth(3);

        updateNode();
    }

    @Override
    public Node createNode() {
        updateNode();
        return polygon;
    }

    @Override
    public String getType() {
        return "Polygon";
    }

    public void updateNode() {

        polygon.setTranslateX(
                getX() + getTranslateX()
        );

        polygon.setTranslateY(
                getY() + getTranslateY()
        );

        polygon.setRotate(getRotation());
        polygon.setScaleX(getScaleX());
        polygon.setScaleY(getScaleY());
    }
}