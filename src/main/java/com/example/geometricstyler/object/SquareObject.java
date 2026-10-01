package com.example.geometricstyler.object;

import com.example.geometricstyler.model.ShapeType;
import javafx.scene.shape.Rectangle;

/**
 * A square: a rectangle whose width and height are forced to stay equal.
 * The base class follows whichever of the two the user changed last.
 */
public class SquareObject extends RectangleObject {

    public SquareObject() {
        super(ShapeType.SQUARE, new Rectangle());
    }

    @Override
    protected boolean keepsEqualSides() {
        return true;
    }
}
