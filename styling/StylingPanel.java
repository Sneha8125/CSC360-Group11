package styling;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

import java.util.List;
import java.util.Objects;

/**
 * Main styling panel that combines all available StyleFeature controls.
 *
 * Whenever a feature changes, all features are re-applied to the target shape.
 * The panel also provides a single action to reset all features to their
 * default values.
 */
public final class StylingPanel {

    private static final double PANEL_SPACING = 16;
    private static final double PANEL_PADDING = 20;
    private static final double PANEL_WIDTH = 320;

    private final VBox root = new VBox(PANEL_SPACING);
    private final List<StyleFeature> features;
    private final Shape target;

    public StylingPanel(Shape target) {
        this.target = Objects.requireNonNull(
                target,
                "target shape must not be null"
        );

        this.features = List.of(
                new FillStyleControl(),
                new BorderStyleControl(),
                new OpacityControl(),
                new EffectsControl()
        );

        configureRoot();
        buildPanel();

        applyAll();
    }

    /**
     * Configures the main panel layout.
     */
    private void configureRoot() {
        root.setPadding(new Insets(PANEL_PADDING));
        root.setPrefWidth(PANEL_WIDTH);

        root.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dcdcdc;" +
                "-fx-border-width: 0 0 0 1;"
        );
    }

    /**
     * Builds the complete styling panel.
     */
    private void buildPanel() {
        Label title = createTitle();

        root.getChildren().addAll(
                title,
                new Separator()
        );

        for (StyleFeature feature : features) {
            feature.setOnChange(this::applyAll);

            root.getChildren().add(feature.getView());
            root.getChildren().add(new Separator());
        }

        root.getChildren().add(createResetButton());
    }

    /**
     * Creates the panel title.
     */
    private Label createTitle() {
        Label title = new Label("Styling");

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        return title;
    }

    /**
     * Creates the reset button.
     */
    private Button createResetButton() {
        Button resetButton = new Button("Reset to Default");

        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnAction(event -> resetAll());

        return resetButton;
    }

    /**
     * Applies every styling feature to the target shape.
     */
    private void applyAll() {
        for (StyleFeature feature : features) {
            feature.applyTo(target);
        }
    }

    /**
     * Resets every feature and reapplies the default styling.
     */
    private void resetAll() {
        for (StyleFeature feature : features) {
            feature.resetToDefault();
        }

        applyAll();
    }

    /**
     * Returns the JavaFX view representing this styling panel.
     */
    public VBox getView() {
        return root;
    }
}