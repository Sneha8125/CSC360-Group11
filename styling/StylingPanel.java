package styling;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

import java.util.List;

/**
 * Assembles every StyleFeature into a single panel and coordinates them:
 *   - re-applies ALL features to the target shape whenever ANY one changes
 *   - resets every feature (and repaints) when "Reset to Default" is pressed
 *
 * To add feature 4 (dashed already lives in BorderStyleControl) or any new
 * feature later, write a class implementing StyleFeature and add it to the
 * `features` list below - nothing else in this file needs to change.
 */
public class StylingPanel {

    private final VBox root = new VBox(16);
    private final List<StyleFeature> features;
    private final Shape target;

    public StylingPanel(Shape target) {
        this.target = target;
        this.features = List.of(
                new FillStyleControl(),
                new BorderStyleControl(),
                new OpacityControl(),
                new EffectsControl()
        );

        root.setPadding(new Insets(20));
        root.setPrefWidth(320);
        root.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc; -fx-border-width: 0 0 0 1;");

        Label title = new Label("Styling");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        root.getChildren().add(title);
        root.getChildren().add(new Separator());

        for (StyleFeature feature : features) {
            feature.setOnChange(this::applyAll);
            root.getChildren().add(feature.getView());
            root.getChildren().add(new Separator());
        }

        Button resetButton = new Button("Reset to Default");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnAction(e -> resetAll());
        root.getChildren().add(resetButton);

        applyAll(); // paint the shape with defaults immediately
    }

    public VBox getView() {
        return root;
    }

    private void applyAll() {
        for (StyleFeature feature : features) {
            feature.applyTo(target);
        }
    }

    private void resetAll() {
        for (StyleFeature feature : features) {
            feature.resetToDefault();
        }
        applyAll();
    }
}