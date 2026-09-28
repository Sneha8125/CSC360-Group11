package com.example.geometricStyler.model;

import javafx.scene.Node;

public abstract class GeometricObject {

    private double x;
    private double y;

    private double width;
    private double height;

    private double rotation;

    private double scaleX = 1.0;
    private double scaleY = 1.0;

    private double translateX = 0;
    private double translateY = 0;

    public GeometricObject(
            double x,
            double y,
            double width,
            double height
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract Node createNode();

    public abstract String getType();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }
}