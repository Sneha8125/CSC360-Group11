package geometry;

public class GeometryStage5Test {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     GEOMETRY STAGE 5 TEST");
        System.out.println("=================================");
        System.out.println();

        // =================================
        // Create Geometry Manager
        // =================================

        GeometryManager manager = new GeometryManager();

        System.out.println("Manager created.");
        System.out.println(
                "Initial object count: "
                        + manager.getObjectCount());

        // =================================
        // Create geometry objects
        // =================================

        CircleObject circle = new CircleObject(
                50,
                50,
                40);

        RectangleObject rectangle = new RectangleObject(
                150,
                100,
                200,
                120);

        SquareObject square = new SquareObject(
                400,
                200,
                100);

        // =================================
        // Add objects
        // =================================

        manager.addObject(circle);
        manager.addObject(rectangle);
        manager.addObject(square);

        System.out.println();
        System.out.println("After adding objects:");
        System.out.println(
                "Object count: "
                        + manager.getObjectCount());

        // =================================
        // Display objects
        // =================================

        System.out.println();
        System.out.println("Objects in manager:");
        System.out.println("-------------------");

        for (int i = 0; i < manager.getObjectCount(); i++) {

            GeometricObject object = manager.getObject(i);

            System.out.println(
                    i + ": " + object.getType());
        }

        // =================================
        // Select Circle
        // =================================

        manager.selectObject(0);

        System.out.println();
        System.out.println("Selected object:");
        System.out.println(
                "Index: "
                        + manager.getSelectedIndex());

        System.out.println(
                "Type: "
                        + manager.getSelectedObject()
                                .getType());

        // =================================
        // Modify selected Circle
        // =================================

        GeometricObject selected = manager.getSelectedObject();

        selected.setPosition(100, 125);
        selected.setRotation(30);
        selected.setScale(1.25);

        System.out.println();
        System.out.println(
                "Selected object after transformation:");

        printState(selected);

        // =================================
        // Select Rectangle
        // =================================

        manager.selectObject(1);

        System.out.println();
        System.out.println("Selecting Rectangle:");

        GeometricObject selectedRectangle = manager.getSelectedObject();

        System.out.println(
                "Selected index: "
                        + manager.getSelectedIndex());

        System.out.println(
                "Selected type: "
                        + selectedRectangle.getType());

        selectedRectangle.translate(25, 15);
        selectedRectangle.setRotation(45);

        System.out.println();
        System.out.println(
                "Rectangle after transformation:");

        printState(selectedRectangle);

        // =================================
        // Select Square
        // =================================

        manager.selectObject(2);

        System.out.println();
        System.out.println("Selecting Square:");

        GeometricObject selectedSquare = manager.getSelectedObject();

        System.out.println(
                "Selected index: "
                        + manager.getSelectedIndex());

        System.out.println(
                "Selected type: "
                        + selectedSquare.getType());

        selectedSquare.setScale(0.75);
        selectedSquare.setRotation(60);

        System.out.println();
        System.out.println(
                "Square after transformation:");

        printState(selectedSquare);

        // =================================
        // Test invalid selection
        // =================================

        System.out.println();
        System.out.println("Selection Validation Test");
        System.out.println("-------------------------");

        boolean selectionResult = manager.selectObject(10);

        if (!selectionResult) {

            System.out.println(
                    "Invalid selection correctly rejected.");

        } else {

            System.out.println(
                    "ERROR: Invalid selection accepted.");
        }

        // =================================
        // Test object removal
        // =================================

        System.out.println();
        System.out.println("Object Removal Test");
        System.out.println("-------------------");

        boolean removed = manager.removeObject(rectangle);

        System.out.println(
                "Rectangle removed: "
                        + removed);

        System.out.println(
                "Object count after removal: "
                        + manager.getObjectCount());

        // =================================
        // Display remaining objects
        // =================================

        System.out.println();
        System.out.println("Remaining objects:");
        System.out.println("------------------");

        for (int i = 0; i < manager.getObjectCount(); i++) {

            GeometricObject object = manager.getObject(i);

            System.out.println(
                    i + ": " + object.getType());
        }

        // =================================
        // Test clear
        // =================================

        manager.clear();

        System.out.println();
        System.out.println("Clear Test");
        System.out.println("----------");

        System.out.println(
                "Object count after clear: "
                        + manager.getObjectCount());

        System.out.println(
                "Selected object after clear: "
                        + manager.getSelectedObject());

        // =================================
        // Final test
        // =================================

        if (manager.getObjectCount() == 0
                && manager.getSelectedObject() == null) {

            System.out.println();
            System.out.println(
                    "Final manager state is correct.");

        } else {

            System.out.println();
            System.out.println(
                    "ERROR: Final manager state is incorrect.");
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("       STAGE 5 TEST COMPLETE");
        System.out.println("=================================");
    }

    // =================================
    // Print object state
    // =================================

    private static void printState(
            GeometricObject object) {

        System.out.println(
                "Type: "
                        + object.getType());

        System.out.println(
                "X: "
                        + object.getX());

        System.out.println(
                "Y: "
                        + object.getY());

        System.out.println(
                "Width: "
                        + object.getWidth());

        System.out.println(
                "Height: "
                        + object.getHeight());

        System.out.println(
                "Rotation: "
                        + object.getRotation()
                        + " degrees");

        System.out.println(
                "Scale: "
                        + object.getScale());
    }
}