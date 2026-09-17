package styling;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/** Small reusable UI-building helpers shared by every feature control. */
public final class UiUtil {

    private UiUtil() {
        // no instances
    }

    /** A titled vertical section, e.g. "Fill Color" followed by its controls. */
    public static VBox section(String header, Node... nodes) {
        Label title = new Label(header);
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #34495e;");
        VBox box = new VBox(8, title);
        box.getChildren().addAll(nodes);
        return box;
    }

    /** A label + control pair laid out in a row, e.g. "Stroke Width" [slider]. */
    public static HBox labeledRow(String labelText, Node control) {
        Label label = new Label(labelText);
        label.setPrefWidth(100);
        HBox box = new HBox(10, label, control);
        box.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(control, Priority.ALWAYS);
        return box;
    }

    /** A slider preconfigured with tick marks/labels. */
    public static Slider slider(double min, double max, double value) {
        Slider s = new Slider(min, max, value);
        s.setShowTickLabels(true);
        s.setShowTickMarks(true);
        s.setMajorTickUnit((max - min) / 4.0);
        return s;
    }
}