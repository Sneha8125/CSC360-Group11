package com.example.geometricStyler.styling;

import javafx.scene.Node;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class StyleManager {

    private final ObjectStyle style;

    public StyleManager() {
        style = new ObjectStyle();
    }

    public ObjectStyle getStyle() {
        return style;
    }

    // =========================================================
    // APPLY COMPLETE STYLE
    // =========================================================

    public void applyStyle(Node node) {

        if (!(node instanceof Shape shape)) {
            return;
        }

        shape.setFill(createFill());

        shape.setStroke(style.getStrokeColor());

        shape.setStrokeWidth(
                style.getStrokeWidth()
        );

        applyStrokeStyle(shape);

        node.setOpacity(
                style.getOpacity()
        );
    }

    // =========================================================
    // CREATE FILL
    // =========================================================

    private Paint createFill() {

        String fillType =
                style.getFillType();

        if ("Linear Gradient".equals(fillType)) {

            return new LinearGradient(
                    0,
                    0,
                    1,
                    1,
                    true,
                    CycleMethod.NO_CYCLE,
                    new Stop(
                            0,
                            style.getGradientColor1()
                    ),
                    new Stop(
                            1,
                            style.getGradientColor2()
                    )
            );
        }

        if ("Radial Gradient".equals(fillType)) {

            return new RadialGradient(
                    0,
                    0,
                    0.5,
                    0.5,
                    0.5,
                    true,
                    CycleMethod.NO_CYCLE,
                    new Stop(
                            0,
                            style.getGradientColor1()
                    ),
                    new Stop(
                            1,
                            style.getGradientColor2()
                    )
            );
        }

        return style.getFillColor();
    }

    // =========================================================
    // STROKE STYLE
    // =========================================================

    private void applyStrokeStyle(Shape shape) {

        shape.getStrokeDashArray().clear();

        switch (style.getStrokeStyle()) {

            case "Dashed" -> {
                shape.getStrokeDashArray()
                        .addAll(12.0, 8.0);
            }

            case "Dotted" -> {
                shape.getStrokeDashArray()
                        .addAll(2.0, 8.0);
            }

            case "Solid" -> {
                // No dash pattern.
            }

            default -> {
                // Use solid stroke.
            }
        }
    }

    // =========================================================
    // FILL COLOR
    // =========================================================

    public void setFillColor(
            Color color,
            Node node) {

        style.setFillColor(color);

        applyStyle(node);
    }

    // =========================================================
    // FILL TYPE
    // =========================================================

    public void setFillType(
            String fillType,
            Node node) {

        style.setFillType(fillType);

        applyStyle(node);
    }

    // =========================================================
    // GRADIENT COLOR 1
    // =========================================================

    public void setGradientColor1(
            Color color,
            Node node) {

        style.setGradientColor1(color);

        applyStyle(node);
    }

    // =========================================================
    // GRADIENT COLOR 2
    // =========================================================

    public void setGradientColor2(
            Color color,
            Node node) {

        style.setGradientColor2(color);

        applyStyle(node);
    }

    // =========================================================
    // STROKE COLOR
    // =========================================================

    public void setStrokeColor(
            Color color,
            Node node) {

        style.setStrokeColor(color);

        applyStyle(node);
    }

    // =========================================================
    // STROKE WIDTH
    // =========================================================

    public void setStrokeWidth(
            double width,
            Node node) {

        style.setStrokeWidth(width);

        applyStyle(node);
    }

    // =========================================================
    // STROKE STYLE
    // =========================================================

    public void setStrokeStyle(
            String strokeStyle,
            Node node) {

        style.setStrokeStyle(strokeStyle);

        applyStyle(node);
    }

    // =========================================================
    // OPACITY
    // =========================================================

    public void setOpacity(
            double opacity,
            Node node) {

        style.setOpacity(opacity);

        applyStyle(node);
    }

    // =========================================================
    // RESET
    // =========================================================

    public void reset(Node node) {

        style.reset();

        applyStyle(node);
    }
}