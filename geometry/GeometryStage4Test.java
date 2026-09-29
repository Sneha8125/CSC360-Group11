package geometry;

public class GeometryStage4Test {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     GEOMETRY STAGE 4 TEST");
        System.out.println("=================================");
        System.out.println();

        // =================================
        // Create Rectangle
        // =================================

        RectangleObject rectangle = new RectangleObject(
                100,
                100,
                200,
                120);

        System.out.println("Initial Rectangle State");
        System.out.println("-----------------------");
        printState(rectangle);

        // =================================
        // Test Translation
        // =================================

        rectangle.translate(50, 25);

        System.out.println();
        System.out.println("After Translation");
        System.out.println("-----------------");
        printState(rectangle);

        // =================================
        // Test Rotation
        // =================================

        rectangle.setRotation(45);

        System.out.println();
        System.out.println("After Rotation");
        System.out.println("--------------");
        System.out.println(
                "Rotation: "
                        + rectangle.getRotation()
                        + " degrees");

        // =================================
        // Test Additional Rotation
        // =================================

        rectangle.rotate(30);

        System.out.println();
        System.out.println("After Additional Rotation");
        System.out.println("-------------------------");
        System.out.println(
                "Rotation: "
                        + rectangle.getRotation()
                        + " degrees");

        // =================================
        // Test Scale
        // =================================

        rectangle.setScale(1.5);

        System.out.println();
        System.out.println("After Scale");
        System.out.println("-----------");
        System.out.println(
                "Scale: "
                        + rectangle.getScale());

        // =================================
        // Test Additional Scaling
        // =================================

        rectangle.scaleBy(2.0);

        System.out.println();
        System.out.println("After Additional Scaling");
        System.out.println("------------------------");
        System.out.println(
                "Scale: "
                        + rectangle.getScale());

        // =================================
        // Test Rotation Normalization
        // =================================

        rectangle.setRotation(450);

        System.out.println();
        System.out.println("Rotation Normalization Test");
        System.out.println("---------------------------");
        System.out.println(
                "450 degrees becomes: "
                        + rectangle.getRotation()
                        + " degrees");

        // =================================
        // Test Invalid Scale
        // =================================

        System.out.println();
        System.out.println("Scale Validation Test");
        System.out.println("---------------------");

        try {

            rectangle.setScale(0);

            System.out.println(
                    "ERROR: Invalid scale was accepted.");

        } catch (IllegalArgumentException exception) {

            System.out.println(
                    "Invalid scale correctly rejected.");
        }

        // =================================
        // Final Rectangle State
        // =================================

        System.out.println();
        System.out.println("Final Rectangle State");
        System.out.println("---------------------");
        printState(rectangle);

        // =================================
        // Test Circle
        // =================================

        System.out.println();
        System.out.println("Shape Transformation Test");
        System.out.println("--------------------------");

        CircleObject circle = new CircleObject(
                50,
                50,
                40);

        circle.setRotation(30);
        circle.setScale(1.25);
        circle.translate(20, 10);

        System.out.println();
        System.out.println("Circle:");
        printState(circle);

        // =================================
        // Test Square
        // =================================

        SquareObject square = new SquareObject(
                300,
                200,
                100);

        square.setRotation(60);
        square.setScale(0.75);
        square.translate(-30, 20);

        System.out.println();
        System.out.println("Square:");
        printState(square);

        // =================================
        // Test Complete
        // =================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       STAGE 4 TEST COMPLETE");
        System.out.println("=================================");
    }

    // =================================
    // Print Object State
    // =================================

    private static void printState(
            GeometricObject object) {

        System.out.println(
                "Type: " + object.getType());

        System.out.println(
                "X: " + object.getX());

        System.out.println(
                "Y: " + object.getY());

        System.out.println(
                "Width: " + object.getWidth());

        System.out.println(
                "Height: " + object.getHeight());

        System.out.println(
                "Rotation: "
                        + object.getRotation()
                        + " degrees");

        System.out.println(
                "Scale: "
                        + object.getScale());
    }
}