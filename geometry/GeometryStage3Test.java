package geometry;

public class GeometryStage3Test {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     GEOMETRY STAGE 3 TEST");
        System.out.println("=================================");
        System.out.println();

        // =================================
        // Create the geometry objects
        // =================================

        CircleObject circle = new CircleObject(50, 50, 40);

        RectangleObject rectangle = new RectangleObject(
                100,
                100,
                200,
                120);

        SquareObject square = new SquareObject(
                300,
                200,
                100);

        // =================================
        // Circle position
        // =================================

        circle.setPosition(150, 175);

        System.out.println("Circle Position");
        System.out.println("----------------");
        System.out.println("X: " + circle.getX());
        System.out.println("Y: " + circle.getY());

        // =================================
        // Circle dimensions
        // =================================

        circle.setRadius(60);

        System.out.println();
        System.out.println("Circle Dimensions");
        System.out.println("-----------------");
        System.out.println("Radius: " + circle.getRadius());
        System.out.println("Width: " + circle.getWidth());
        System.out.println("Height: " + circle.getHeight());

        // =================================
        // Rectangle position
        // =================================

        rectangle.setPosition(250, 200);

        System.out.println();
        System.out.println("Rectangle Position");
        System.out.println("------------------");
        System.out.println("X: " + rectangle.getX());
        System.out.println("Y: " + rectangle.getY());

        // =================================
        // Rectangle dimensions
        // =================================

        rectangle.setDimensions(300, 180);

        System.out.println();
        System.out.println("Rectangle Dimensions");
        System.out.println("--------------------");
        System.out.println("Width: " + rectangle.getWidth());
        System.out.println("Height: " + rectangle.getHeight());

        // =================================
        // Square position
        // =================================

        square.setPosition(400, 300);

        System.out.println();
        System.out.println("Square Position");
        System.out.println("---------------");
        System.out.println("X: " + square.getX());
        System.out.println("Y: " + square.getY());

        // =================================
        // Square dimensions
        // =================================

        square.setSide(150);

        System.out.println();
        System.out.println("Square Dimensions");
        System.out.println("-----------------");
        System.out.println("Side: " + square.getSide());
        System.out.println("Width: " + square.getWidth());
        System.out.println("Height: " + square.getHeight());

        // =================================
        // Validation test
        // =================================

        System.out.println();
        System.out.println("Validation Test");
        System.out.println("---------------");

        try {

            rectangle.setDimensions(-100, 200);

            System.out.println(
                    "ERROR: Invalid dimensions were accepted.");

        } catch (IllegalArgumentException exception) {

            System.out.println(
                    "Invalid dimensions correctly rejected.");
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("       STAGE 3 TEST COMPLETE");
        System.out.println("=================================");
    }
}