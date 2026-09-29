package geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeometryManager {

    private final List<GeometricObject> objects;

    private int selectedIndex;

    public GeometryManager() {
        objects = new ArrayList<>();
        selectedIndex = -1;
    }

    // =================================
    // Add object
    // =================================

    public void addObject(GeometricObject object) {

        if (object == null) {
            throw new IllegalArgumentException(
                    "Object cannot be null.");
        }

        objects.add(object);

        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
    }

    // =================================
    // Remove object
    // =================================

    public boolean removeObject(GeometricObject object) {

        int index = objects.indexOf(object);

        if (index == -1) {
            return false;
        }

        objects.remove(index);

        if (objects.isEmpty()) {
            selectedIndex = -1;
        } else if (selectedIndex >= objects.size()) {
            selectedIndex = objects.size() - 1;
        } else if (index < selectedIndex) {
            selectedIndex--;
        } else if (index == selectedIndex) {
            if (selectedIndex >= objects.size()) {
                selectedIndex = objects.size() - 1;
            }
        }

        return true;
    }

    // =================================
    // Remove object by index
    // =================================

    public boolean removeObject(int index) {

        if (!isValidIndex(index)) {
            return false;
        }

        objects.remove(index);

        if (objects.isEmpty()) {
            selectedIndex = -1;
        } else if (selectedIndex >= objects.size()) {
            selectedIndex = objects.size() - 1;
        } else if (index < selectedIndex) {
            selectedIndex--;
        } else if (index == selectedIndex) {
            if (selectedIndex >= objects.size()) {
                selectedIndex = objects.size() - 1;
            }
        }

        return true;
    }

    // =================================
    // Get object
    // =================================

    public GeometricObject getObject(int index) {

        if (!isValidIndex(index)) {
            return null;
        }

        return objects.get(index);
    }

    // =================================
    // Get all objects
    // =================================

    public List<GeometricObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    // =================================
    // Number of objects
    // =================================

    public int getObjectCount() {
        return objects.size();
    }

    // =================================
    // Selection
    // =================================

    public boolean selectObject(int index) {

        if (!isValidIndex(index)) {
            return false;
        }

        selectedIndex = index;

        return true;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public GeometricObject getSelectedObject() {

        if (!isValidIndex(selectedIndex)) {
            return null;
        }

        return objects.get(selectedIndex);
    }

    // =================================
    // Clear objects
    // =================================

    public void clear() {

        objects.clear();
        selectedIndex = -1;
    }

    // =================================
    // Check index
    // =================================

    private boolean isValidIndex(int index) {

        return index >= 0 && index < objects.size();
    }
}