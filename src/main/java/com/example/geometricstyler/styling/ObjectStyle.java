package com.example.geometricStyler.styling;

import javafx.scene.paint.Color;

public class ObjectStyle {

    private Color fillColor = Color.LIGHTBLUE;
    private Color strokeColor = Color.BLACK;
    private double strokeWidth = 3.0;
    private String strokeStyle = "Solid";
    private double opacity = 1.0;

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeStyle() {
        return strokeStyle;
    }

    public void setStrokeStyle(String strokeStyle) {
        this.strokeStyle = strokeStyle;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public void reset() {
        fillColor = Color.LIGHTBLUE;
        strokeColor = Color.BLACK;
        strokeWidth = 3.0;
        strokeStyle = "Solid";
        opacity = 1.0;
    }
}