package com.example.geometricStyler.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleObject extends GeometricObject {

    private final Rectangle rectangle;

    public RectangleObject() {

        super(250, 250, 180, 120);

        rectangle = new Rectangle();

        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.DODGERBLUE);
        rectangle.setStrokeWidth(3);

        updateNode();
    }

    @Override
    public Node createNode() {
        updateNode();
        return rectangle;
    }

    @Override
    public String getType() {
        return "Rectangle";
    }

    public void updateNode() {

        rectangle.setWidth(getWidth());
        rectangle.setHeight(getHeight());

        rectangle.setTranslateX(
                getX() + getTranslateX()
        );

        rectangle.setTranslateY(
                getY() + getTranslateY()
        );

        rectangle.setRotate(getRotation());

        rectangle.setScaleX(
                getScaleX()
        );

        rectangle.setScaleY(
                getScaleY()
        );
    }
}