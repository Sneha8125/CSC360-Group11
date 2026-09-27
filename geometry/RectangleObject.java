package geometry;

public class RectangleObject extends GeometricObject {

    public RectangleObject(double x, double y,
            double width, double height) {

        super(x, y, width, height);

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be greater than zero.");
        }
    }

    @Override
    public String getType() {
        return "Rectangle";
    }
}