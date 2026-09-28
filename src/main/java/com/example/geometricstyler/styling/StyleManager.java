package com.example.geometricStyler.styling;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class StyleManager {

    private final ObjectStyle style;

    public StyleManager() {
        style = new ObjectStyle();
    }

    public ObjectStyle getStyle() {
        return style;
    }

    public void applyStyle(Node node) {

        if (!(node instanceof Shape shape)) {
            return;
        }

        shape.setFill(style.getFillColor());
        shape.setStroke(style.getStrokeColor());
        shape.setStrokeWidth(style.getStrokeWidth());

        applyStrokeStyle(shape);

        node.setOpacity(style.getOpacity());
    }

    private void applyStrokeStyle(Shape shape) {

        shape.getStrokeDashArray().clear();

        switch (style.getStrokeStyle()) {

            case "Dashed" -> {
                shape.getStrokeDashArray().addAll(12.0, 8.0);
            }

            case "Dotted" -> {
                shape.getStrokeDashArray().addAll(2.0, 8.0);
            }

            case "Solid" -> {
                // No dash pattern.
            }

            default -> {
                // Use solid stroke for unknown values.
            }
        }
    }

    public void setFillColor(Color color, Node node) {
        style.setFillColor(color);
        applyStyle(node);
    }

    public void setStrokeColor(Color color, Node node) {
        style.setStrokeColor(color);
        applyStyle(node);
    }

    public void setStrokeWidth(double width, Node node) {
        style.setStrokeWidth(width);
        applyStyle(node);
    }

    public void setStrokeStyle(String strokeStyle, Node node) {
        style.setStrokeStyle(strokeStyle);
        applyStyle(node);
    }

    public void setOpacity(double opacity, Node node) {
        style.setOpacity(opacity);
        applyStyle(node);
    }

    public void reset(Node node) {
        style.reset();
        applyStyle(node);
    }
}