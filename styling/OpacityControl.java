package styling;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

/**
 * Feature: Opacity.
 * A 0-100% slider mapped onto the shape's opacity (0.0 - 1.0).
 */
public class OpacityControl implements StyleFeature {

    private static final double DEFAULT_OPACITY = 100.0;

    private final Slider opacitySlider = UiUtil.slider(0, 100, DEFAULT_OPACITY);
    private final VBox view;

    private Runnable onChange = () -> {};

    public OpacityControl() {
        opacitySlider.valueProperty().addListener((obs, o, n) -> fireChange());
        view = UiUtil.section("Opacity", UiUtil.labeledRow("Opacity %", opacitySlider));
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        shape.setOpacity(opacitySlider.getValue() / 100.0);
    }

    @Override
    public void resetToDefault() {
        opacitySlider.setValue(DEFAULT_OPACITY);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }

    private void fireChange() {
        onChange.run();
    }
}