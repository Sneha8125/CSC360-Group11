package com.example.geometricStyler.styling;

import javafx.scene.paint.Color;

public class ObjectStyle {

    private Color fillColor = Color.LIGHTBLUE;

    private String fillType = "Solid";

    private Color gradientColor1 = Color.LIGHTBLUE;
    private Color gradientColor2 = Color.DODGERBLUE;

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

    public String getFillType() {
        return fillType;
    }

    public void setFillType(String fillType) {
        this.fillType = fillType;
    }

    public Color getGradientColor1() {
        return gradientColor1;
    }

    public void setGradientColor1(Color gradientColor1) {
        this.gradientColor1 = gradientColor1;
    }

    public Color getGradientColor2() {
        return gradientColor2;
    }

    public void setGradientColor2(Color gradientColor2) {
        this.gradientColor2 = gradientColor2;
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

        fillType = "Solid";

        gradientColor1 = Color.LIGHTBLUE;

        gradientColor2 = Color.DODGERBLUE;

        strokeColor = Color.BLACK;

        strokeWidth = 3.0;

        strokeStyle = "Solid";

        opacity = 1.0;
    }
}