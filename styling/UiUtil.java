package styling;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Small reusable UI-building helpers shared by styling controls.
 */
public final class UiUtil {

    private static final double LABEL_WIDTH = 110;
    private static final double ROW_SPACING = 10;
    private static final double SECTION_SPACING = 8;

    private UiUtil() {
        // Utility class - prevent instantiation
    }

    /**
     * Creates a titled vertical section containing the supplied controls.
     */
    public static VBox section(String header, Node... nodes) {
        VBox box = new VBox(SECTION_SPACING);

        box.setPadding(new Insets(5, 0, 10, 0));

        box.getChildren().add(heading(header));
        box.getChildren().addAll(nodes);

        return box;
    }

    /**
     * Creates a styled section heading.
     */
    public static Label heading(String text) {
        Label title = new Label(text);

        title.setStyle(
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #34495e;"
        );

        return title;
    }

    /**
     * Creates a row containing a label and a control.
     */
    public static HBox labeledRow(String labelText, Node control) {
        Label label = new Label(labelText);
        label.setPrefWidth(LABEL_WIDTH);

        HBox box = new HBox(ROW_SPACING, label, control);

        box.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(control, Priority.ALWAYS);

        return box;
    }

    /**
     * Creates a slider with tick marks and labels.
     */
    public static Slider slider(double min, double max, double value) {

        if (min >= max) {
            throw new IllegalArgumentException(
                "Minimum must be less than maximum"
            );
        }

        if (value < min || value > max) {
            throw new IllegalArgumentException(
                "Value must be between minimum and maximum"
            );
        }

        Slider slider = new Slider(min, max, value);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.setMajorTickUnit((max - min) / 4.0);
        slider.setBlockIncrement((max - min) / 10.0);

        slider.setMaxWidth(Double.MAX_VALUE);

        return slider;
    }
}