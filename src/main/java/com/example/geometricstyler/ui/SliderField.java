package com.example.geometricstyler.ui;

import com.example.geometricstyler.util.ValidationUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/**
 * One row of the control panel: a caption, an editable number and a slider.
 *
 * <p>The slider updates the object live. The text field is only committed when
 * the user presses Enter, leaves the field, or presses the Apply button, which
 * is what makes the Apply button meaningful.</p>
 */
public class SliderField extends VBox {

    private final String caption;
    private final String unit;
    private final int decimals;

    private final Slider slider = new Slider();
    private final TextField valueField = new TextField();

    /** Guards against feedback loops while the controller pushes values back in. */
    private boolean syncing;

    private DoubleConsumer onChange = value -> { };
    private Consumer<String> onInvalidInput = message -> { };

    public SliderField(String caption, double min, double max, double initial,
                       int decimals, String unit) {
        this.caption = caption;
        this.decimals = decimals;
        this.unit = unit;

        getStyleClass().add("slider-field");
        setSpacing(3);

        Label captionLabel = new Label(caption);
        captionLabel.getStyleClass().add("field-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        valueField.getStyleClass().add("value-field");
        valueField.setPrefColumnCount(5);

        HBox header = new HBox(6, captionLabel, spacer, valueField);
        header.setAlignment(Pos.CENTER_LEFT);

        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(ValidationUtil.clamp(initial, min, max));
        slider.setBlockIncrement((max - min) / 50);

        getChildren().addAll(header, slider);
        writeFieldText(slider.getValue());

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            writeFieldText(newValue.doubleValue());
            if (!syncing) {
                onChange.accept(newValue.doubleValue());
            }
        });

        valueField.setOnAction(event -> commit());
        valueField.focusedProperty().addListener((observable, hadFocus, hasFocus) -> {
            if (!hasFocus) {
                commit();
            }
        });
    }

    /* ----- callbacks ----- */

    public SliderField onChange(DoubleConsumer handler) {
        this.onChange = (handler == null) ? value -> { } : handler;
        return this;
    }

    public SliderField onInvalidInput(Consumer<String> handler) {
        this.onInvalidInput = (handler == null) ? message -> { } : handler;
        return this;
    }

    public SliderField withTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        slider.setTooltip(tooltip);
        valueField.setTooltip(tooltip);
        return this;
    }

    /* ----- values ----- */

    public double getValue() {
        return slider.getValue();
    }

    /** Sets the value without firing the change callback (controller to UI sync). */
    public void setValue(double value) {
        syncing = true;
        slider.setValue(ValidationUtil.clamp(value, slider.getMin(), slider.getMax()));
        writeFieldText(slider.getValue());
        syncing = false;
    }

    /** Used for the X and Y sliders, whose range follows the preview size. */
    public void setRange(double min, double max) {
        syncing = true;
        double current = slider.getValue();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(ValidationUtil.clamp(current, min, max));
        writeFieldText(slider.getValue());
        syncing = false;
    }

    /** True when the user typed something that has not been committed yet. */
    public boolean hasUncommittedText() {
        return !valueField.getText().equals(format(slider.getValue()));
    }

    /**
     * Validates and applies whatever is currently typed in the text field.
     *
     * @return true when the text was a usable number
     */
    public boolean commit() {
        OptionalDouble parsed = ValidationUtil.parseNumber(valueField.getText());

        if (parsed.isEmpty()) {
            onInvalidInput.accept(caption + ": enter a number between "
                    + format(slider.getMin()) + " and " + format(slider.getMax()) + unit);
            writeFieldText(slider.getValue());
            return false;
        }

        double typed = parsed.getAsDouble();
        double clamped = ValidationUtil.clamp(typed, slider.getMin(), slider.getMax());
        if (Math.abs(clamped - typed) > 0.000001) {
            onInvalidInput.accept(caption + ": " + format(typed) + unit + " is outside "
                    + format(slider.getMin()) + " - " + format(slider.getMax()) + unit
                    + ", using " + format(clamped) + unit);
        }

        applyValue(clamped);
        return true;
    }

    /** Setting the same value does not fire the slider listener, so notify manually. */
    private void applyValue(double value) {
        if (Double.compare(slider.getValue(), value) == 0) {
            writeFieldText(value);
            onChange.accept(value);
        } else {
            slider.setValue(value);
        }
    }

    private void writeFieldText(double value) {
        valueField.setText(format(value));
    }

    private String format(double value) {
        return ValidationUtil.format(value, decimals);
    }
}
