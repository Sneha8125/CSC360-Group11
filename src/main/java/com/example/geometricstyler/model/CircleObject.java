package com.example.geometricStyler.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleObject extends GeometricObject {

    private final Circle circle;

    public CircleObject() {

        super(
                250,
                250,
                150,
                150
        );

        circle = new Circle();

        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.DODGERBLUE);
        circle.setStrokeWidth(3);

        updateNode();
    }

    @Override
    public Node createNode() {

        updateNode();

        return circle;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    public Circle getCircle() {
        return circle;
    }

    public void updateNode() {

        double radius =
                Math.min(getWidth(), getHeight()) / 2.0;

        circle.setRadius(radius);

        circle.setTranslateX(
                getX() + getTranslateX()
        );

        circle.setTranslateY(
                getY() + getTranslateY()
        );

        circle.setRotate(
                getRotation()
        );

        circle.setScaleX(
                getScaleX()
        );

        circle.setScaleY(
                getScaleY()
        );
    }
}