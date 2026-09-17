package styling;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Feature: Border / Stroke.
 * Controls stroke color, stroke width, and dashed vs solid border style.
 */
public class BorderStyleControl implements StyleFeature {

    private static final Color DEFAULT_STROKE = Color.web("#2c3e50");
    private static final double DEFAULT_WIDTH = 2.0;

    private final ColorPicker strokePicker = new ColorPicker(DEFAULT_STROKE);
    private final Slider widthSlider = UiUtil.slider(0, 15, DEFAULT_WIDTH);
    private final CheckBox dashedCheck = new CheckBox("Dashed border");
    private final VBox view;

    private Runnable onChange = () -> {};

    public BorderStyleControl() {
        strokePicker.valueProperty().addListener((obs, o, n) -> fireChange());
        widthSlider.valueProperty().addListener((obs, o, n) -> fireChange());
        dashedCheck.selectedProperty().addListener((obs, o, n) -> fireChange());

        view = UiUtil.section("Border / Stroke",
                UiUtil.labeledRow("Stroke Color", strokePicker),
                UiUtil.labeledRow("Stroke Width", widthSlider),
                dashedCheck);
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        shape.setStroke(strokePicker.getValue());
        shape.setStrokeWidth(widthSlider.getValue());
        if (dashedCheck.isSelected()) {
            shape.getStrokeDashArray().setAll(12.0, 8.0);
        } else {
            shape.getStrokeDashArray().clear();
        }
    }

    @Override
    public void resetToDefault() {
        strokePicker.setValue(DEFAULT_STROKE);
        widthSlider.setValue(DEFAULT_WIDTH);
        dashedCheck.setSelected(false);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }

    private void fireChange() {
        onChange.run();
    }
}