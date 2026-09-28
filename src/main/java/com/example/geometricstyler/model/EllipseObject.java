package com.example.geometricStyler.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllipseObject extends GeometricObject {

    private final Ellipse ellipse;

    public EllipseObject() {

        super(250, 250, 200, 120);

        ellipse = new Ellipse();

        ellipse.setFill(Color.LIGHTBLUE);
        ellipse.setStroke(Color.DODGERBLUE);
        ellipse.setStrokeWidth(3);

        updateNode();
    }

    @Override
    public Node createNode() {
        updateNode();
        return ellipse;
    }

    @Override
    public String getType() {
        return "Ellipse";
    }

    public void updateNode() {

        ellipse.setRadiusX(getWidth() / 2);
        ellipse.setRadiusY(getHeight() / 2);

        ellipse.setTranslateX(
                getX() + getTranslateX()
        );

        ellipse.setTranslateY(
                getY() + getTranslateY()
        );

        ellipse.setRotate(getRotation());
        ellipse.setScaleX(getScaleX());
        ellipse.setScaleY(getScaleY());
    }
}