package geometry;

public class GeometryStage1Test {

    private static class TestObject extends GeometricObject {

        public TestObject() {
            super(100, 100, 200, 150);
        }

        @Override
        public String getType() {
            return "Test Object";
        }
    }

    public static void main(String[] args) {

        TestObject object = new TestObject();

        System.out.println("=== Geometry Stage 1 Test ===");

        System.out.println("Type: " + object.getType());
        System.out.println("X: " + object.getX());
        System.out.println("Y: " + object.getY());
        System.out.println("Width: " + object.getWidth());
        System.out.println("Height: " + object.getHeight());

        object.translate(50, 25);

        System.out.println();
        System.out.println("After translation:");

        System.out.println("X: " + object.getX());
        System.out.println("Y: " + object.getY());
    }
}
