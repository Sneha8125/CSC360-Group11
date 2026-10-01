package com.example.geometricstyler.ui;

import com.example.geometricstyler.model.GeometricObject;
import com.example.geometricstyler.model.ObjectStyle;
import com.example.geometricstyler.util.ValidationUtil;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The strip under the preview: what the object currently looks like, plus a
 * short status message such as "Style applied" or a validation warning.
 */
public class PropertyView extends VBox {

    private static final Duration STATUS_VISIBLE_TIME = Duration.seconds(4);

    private final Label typeValue;
    private final Label positionValue;
    private final Label sizeValue;
    private final Label rotationValue;
    private final Label scaleValue;
    private final Label translationValue;
    private final Label opacityValue;
    private final Label strokeValue;
    private final Label fillValue;

    private final Label statusLabel = new Label("Ready");
    private final PauseTransition statusTimer = new PauseTransition(STATUS_VISIBLE_TIME);

    public PropertyView() {
        getStyleClass().add("property-view");
        setSpacing(8);

        Label heading = new Label("Current object");
        heading.getStyleClass().add("section-title");

        FlowPane entries = new FlowPane(14, 6);
        entries.getStyleClass().add("property-entries");

        typeValue = addEntry(entries, "Type");
        positionValue = addEntry(entries, "Position");
        sizeValue = addEntry(entries, "Size");
        rotationValue = addEntry(entries, "Rotation");
        scaleValue = addEntry(entries, "Scale");
        translationValue = addEntry(entries, "Translation");
        opacityValue = addEntry(entries, "Opacity");
        strokeValue = addEntry(entries, "Border");
        fillValue = addEntry(entries, "Fill");

        statusLabel.getStyleClass().add("status-label");
        statusTimer.setOnFinished(event -> showStatus("Ready"));

        getChildren().addAll(heading, entries, statusLabel);
    }

    private Label addEntry(FlowPane parent, String caption) {
        Label captionLabel = new Label(caption);
        captionLabel.getStyleClass().add("property-caption");

        Label valueLabel = new Label("-");
        valueLabel.getStyleClass().add("property-value");

        HBox entry = new HBox(5, captionLabel, valueLabel);
        entry.getStyleClass().add("property-entry");
        parent.getChildren().add(entry);
        return valueLabel;
    }

    /** Refreshes every value from the model. Called whenever the object changes. */
    public void update(GeometricObject object) {
        if (object == null) {
            return;
        }
        ObjectStyle style = object.getStyle();

        typeValue.setText(object.getType().getLabel());
        positionValue.setText(round(object.getX()) + ", " + round(object.getY()));
        sizeValue.setText(round(object.getWidth()) + " x " + round(object.getHeight()));
        rotationValue.setText(round(object.getRotation()) + " deg");
        scaleValue.setText(ValidationUtil.format(object.getScaleX(), 2)
                + " x " + ValidationUtil.format(object.getScaleY(), 2)
                + flipSuffix(object));
        translationValue.setText(round(object.getTranslateX()) + ", " + round(object.getTranslateY()));
        opacityValue.setText(style.getOpacityPercent() + " %");
        strokeValue.setText(ValidationUtil.format(style.getStrokeWidth(), 0)
                + " px " + style.getStrokeStyle().getLabel().toLowerCase());
        fillValue.setText(style.getFillType().getLabel());
    }

    private String flipSuffix(GeometricObject object) {
        if (object.isFlippedHorizontally() && object.isFlippedVertically()) {
            return " (flipped H+V)";
        }
        if (object.isFlippedHorizontally()) {
            return " (flipped H)";
        }
        if (object.isFlippedVertically()) {
            return " (flipped V)";
        }
        return "";
    }

    /** Neutral feedback, for example "Object centred". */
    public void showStatus(String message) {
        statusLabel.getStyleClass().remove("status-error");
        statusLabel.setText(message);
    }

    /** Feedback for a rejected or corrected value; disappears again after a few seconds. */
    public void showError(String message) {
        if (!statusLabel.getStyleClass().contains("status-error")) {
            statusLabel.getStyleClass().add("status-error");
        }
        statusLabel.setText(message);
        statusTimer.playFromStart();
    }

    private String round(double value) {
        return ValidationUtil.format(value, 0);
    }
}
