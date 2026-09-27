package geometry;

public class CircleObject extends GeometricObject {

    private double radius;

    public CircleObject(double x, double y, double radius) {

        super(x, y, radius * 2, radius * 2);

        if (radius <= 0) {
            throw new IllegalArgumentException(
                    "Radius must be greater than zero.");
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {

        if (radius <= 0) {
            throw new IllegalArgumentException(
                    "Radius must be greater than zero.");
        }

        this.radius = radius;

        setWidth(radius * 2);
        setHeight(radius * 2);
    }

    @Override
    public String getType() {
        return "Circle";
    }
}