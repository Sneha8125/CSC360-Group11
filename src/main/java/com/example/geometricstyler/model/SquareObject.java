package com.example.geometricStyler.model;

public class SquareObject extends RectangleObject {

    public SquareObject() {

        super();

        setWidth(150);
        setHeight(150);
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public void setWidth(double width) {

        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(double height) {

        super.setWidth(height);
        super.setHeight(height);
    }
}