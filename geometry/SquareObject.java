package geometry;

public class SquareObject extends GeometricObject {

    private double side;

    public SquareObject(double x, double y, double side) {

        super(x, y, side, side);

        if (side <= 0) {
            throw new IllegalArgumentException(
                    "Side must be greater than zero.");
        }

        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {

        if (side <= 0) {
            throw new IllegalArgumentException(
                    "Side must be greater than zero.");
        }

        this.side = side;

        setWidth(side);
        setHeight(side);
    }

    @Override
    public String getType() {
        return "Square";
    }
}