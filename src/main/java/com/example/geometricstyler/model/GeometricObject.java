package com.example.geometricstyler.model;

import com.example.geometricstyler.util.Defaults;
import com.example.geometricstyler.util.ValidationUtil;
import javafx.scene.shape.Shape;

/**
 * The single geometric object the user is editing.
 *
 * <h2>How an object is placed on the preview</h2>
 * Every subclass builds its geometry inside a local box that starts at (0,0) and
 * is {@code width} x {@code height} big. The object is then placed by moving that
 * box with {@code layoutX} / {@code layoutY}, so that the centre of the box lands
 * exactly on the model position (x, y).
 *
 * <p>Because the geometry is centred inside its own bounds, JavaFX rotates and
 * scales the object around its centre automatically.</p>
 *
 * <h2>Position versus translation (asked about often in the viva)</h2>
 * <ul>
 *   <li><b>x / y</b> is the <i>base position</i> of the centre inside the preview.
 *       It is written into {@code layoutX} / {@code layoutY}.</li>
 *   <li><b>translateX / translateY</b> is an <i>extra offset</i> applied on top of
 *       the base position using the JavaFX translate properties.</li>
 * </ul>
 * The place the user actually sees is therefore
 * {@code effectiveX = x + translateX} (same for y). The two never contradict each
 * other: dragging and the arrow keys write into x / y, the translation sliders
 * write into translateX / translateY.
 */
public abstract class GeometricObject {

    private final ShapeType type;
    private final Shape node;

    /** Base position of the object centre inside the preview area. */
    private double x;
    private double y;

    private double width = Defaults.WIDTH;
    private double height = Defaults.HEIGHT;

    private double rotation = Defaults.ROTATION;
    private double scaleX = Defaults.SCALE;
    private double scaleY = Defaults.SCALE;
    private double translateX = Defaults.TRANSLATION;
    private double translateY = Defaults.TRANSLATION;

    /** Flipping is kept separate from scale so one cannot overwrite the other. */
    private boolean flippedHorizontally;
    private boolean flippedVertically;

    private ObjectStyle style = new ObjectStyle();

    protected GeometricObject(ShapeType type, Shape node) {
        this.type = type;
        this.node = node;
        // The preview is a plain Pane; unmanaged children keep the exact
        // layoutX / layoutY we give them and never resize the pane.
        this.node.setManaged(false);
    }

    /**
     * Builds the shape specific geometry inside the local box (0,0,width,height).
     * Called every time the size changes.
     */
    protected abstract void applyGeometry(double width, double height);

    /** Circle and Square keep width and height equal. */
    protected boolean keepsEqualSides() {
        return false;
    }

    /** Creates the geometry and places the object. Call once after construction. */
    public void initialise(double x, double y, double width, double height) {
        setSize(width, height);
        setPosition(x, y);
        applyTransforms();
    }

    /* ----- size ----- */

    public void setSize(double newWidth, double newHeight) {
        double w = ValidationUtil.sanitizeSize(newWidth);
        double h = ValidationUtil.sanitizeSize(newHeight);

        if (keepsEqualSides()) {
            // Follow whichever value the user actually changed.
            double side = Math.abs(w - width) >= Math.abs(h - height) ? w : h;
            w = side;
            h = side;
        }

        this.width = w;
        this.height = h;
        applyGeometry(w, h);
        updateLayout();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /* ----- position ----- */

    public void setPosition(double newX, double newY) {
        this.x = ValidationUtil.isUsableNumber(newX) ? newX : 0;
        this.y = ValidationUtil.isUsableNumber(newY) ? newY : 0;
        updateLayout();
    }

    private void updateLayout() {
        node.setLayoutX(x - width / 2);
        node.setLayoutY(y - height / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /** Where the object is really drawn: base position plus translation. */
    public double getEffectiveX() {
        return x + translateX;
    }

    public double getEffectiveY() {
        return y + translateY;
    }

    /* ----- rotation ----- */

    public void setRotation(double degrees) {
        this.rotation = ValidationUtil.sanitizeRotation(degrees);
        node.setRotate(this.rotation);
    }

    public double getRotation() {
        return rotation;
    }

    /* ----- scale and flip ----- */

    public void setScale(double newScaleX, double newScaleY) {
        this.scaleX = ValidationUtil.sanitizeScale(newScaleX);
        this.scaleY = ValidationUtil.sanitizeScale(newScaleY);
        applyScale();
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setFlippedHorizontally(boolean flipped) {
        this.flippedHorizontally = flipped;
        applyScale();
    }

    public void setFlippedVertically(boolean flipped) {
        this.flippedVertically = flipped;
        applyScale();
    }

    public boolean isFlippedHorizontally() {
        return flippedHorizontally;
    }

    public boolean isFlippedVertically() {
        return flippedVertically;
    }

    /**
     * Flipping is a mirror, so it is expressed as a negative sign on the scale.
     * Keeping the sign separate means changing the scale slider never undoes a flip.
     */
    private void applyScale() {
        node.setScaleX(scaleX * (flippedHorizontally ? -1 : 1));
        node.setScaleY(scaleY * (flippedVertically ? -1 : 1));
    }

    /* ----- translation ----- */

    public void setTranslation(double newTranslateX, double newTranslateY) {
        this.translateX = ValidationUtil.clamp(newTranslateX,
                Defaults.MIN_TRANSLATION, Defaults.MAX_TRANSLATION);
        this.translateY = ValidationUtil.clamp(newTranslateY,
                Defaults.MIN_TRANSLATION, Defaults.MAX_TRANSLATION);
        node.setTranslateX(this.translateX);
        node.setTranslateY(this.translateY);
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    /** Re-sends every transform to the JavaFX node (used after a rebuild). */
    public void applyTransforms() {
        node.setRotate(rotation);
        node.setTranslateX(translateX);
        node.setTranslateY(translateY);
        applyScale();
    }

    /**
     * Copies the transform values of another object.
     * Used when the user picks a different shape type so the new object appears
     * in the same place, with the same rotation, scale and translation.
     */
    public void copyTransformsFrom(GeometricObject other) {
        this.rotation = other.rotation;
        this.scaleX = other.scaleX;
        this.scaleY = other.scaleY;
        this.translateX = other.translateX;
        this.translateY = other.translateY;
        this.flippedHorizontally = other.flippedHorizontally;
        this.flippedVertically = other.flippedVertically;
        applyTransforms();
    }

    /* ----- style and node ----- */

    public ObjectStyle getStyle() {
        return style;
    }

    public void setStyle(ObjectStyle style) {
        if (style != null) {
            this.style = style;
        }
    }

    public Shape getNode() {
        return node;
    }

    public ShapeType getType() {
        return type;
    }
}
