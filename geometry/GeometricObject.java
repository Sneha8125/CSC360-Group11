package geometry;

public abstract class GeometricObject {

    private double x;
    private double y;

    private double width;
    private double height;

    protected GeometricObject(double x, double y,
            double width, double height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be greater than zero.");
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // =========================
    // Position
    // =========================

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

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // =========================
    // Dimensions
    // =========================

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {

        if (width <= 0) {
            throw new IllegalArgumentException(
                    "Width must be greater than zero.");
        }

        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {

        if (height <= 0) {
            throw new IllegalArgumentException(
                    "Height must be greater than zero.");
        }

        this.height = height;
    }

    public void setDimensions(double width, double height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be greater than zero.");
        }

        this.width = width;
        this.height = height;
    }

    // =========================
    // Translation
    // =========================

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    // =========================
    // Object type
    // =========================

    public abstract String getType();
}