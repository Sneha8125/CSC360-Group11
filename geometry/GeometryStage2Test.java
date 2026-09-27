package geometry;

public class GeometryStage2Test {

    public static void main(String[] args) {

        System.out.println("=== Geometry Stage 2 Test ===");
        System.out.println();

        CircleObject circle = new CircleObject(100, 100, 50);

        RectangleObject rectangle = new RectangleObject(200, 150, 200, 100);

        SquareObject square = new SquareObject(450, 200, 120);

        circle.setRadius(75);

        square.setSide(150);

        circle.setX(150);
        circle.setY(175);

        rectangle.setX(250);
        rectangle.setY(200);

        square.setX(500);
        square.setY(250);

        System.out.println("Circle:");
        System.out.println("Type: " + circle.getType());
        System.out.println("X: " + circle.getX());
        System.out.println("Y: " + circle.getY());
        System.out.println("Radius: " + circle.getRadius());
        System.out.println("Width: " + circle.getWidth());
        System.out.println("Height: " + circle.getHeight());

        System.out.println();

        System.out.println("Rectangle:");
        System.out.println("Type: " + rectangle.getType());
        System.out.println("X: " + rectangle.getX());
        System.out.println("Y: " + rectangle.getY());
        System.out.println("Width: " + rectangle.getWidth());
        System.out.println("Height: " + rectangle.getHeight());

        System.out.println();

        System.out.println("Square:");
        System.out.println("Type: " + square.getType());
        System.out.println("X: " + square.getX());
        System.out.println("Y: " + square.getY());
        System.out.println("Side: " + square.getSide());
        System.out.println("Width: " + square.getWidth());
        System.out.println("Height: " + square.getHeight());
    }
}