package styling;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

/**
 * Styling feature that controls the opacity of a JavaFX Shape.
 *
 * <p>The UI exposes opacity as a percentage from 0% to 100%,
 * which is converted to JavaFX's 0.0 to 1.0 opacity range.</p>
 */
public final class OpacityControl implements StyleFeature {

    private static final double MIN_OPACITY = 0.0;
    private static final double MAX_OPACITY = 100.0;
    private static final double DEFAULT_OPACITY = 100.0;

    private final Slider opacitySlider;
    private final VBox view;

    private Runnable onChange = () -> {};

    public OpacityControl() {
        opacitySlider = UiUtil.slider(
                MIN_OPACITY,
                MAX_OPACITY,
                DEFAULT_OPACITY
        );

        opacitySlider.valueProperty()
                .addListener((obs, oldValue, newValue) -> notifyChange());

        view = UiUtil.section(
                "Opacity",
                UiUtil.labeledRow("Opacity %", opacitySlider)
        );
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        double opacity = opacitySlider.getValue() / MAX_OPACITY;
        shape.setOpacity(opacity);
    }

    @Override
    public void resetToDefault() {
        opacitySlider.setValue(DEFAULT_OPACITY);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange != null
                ? onChange
                : () -> {};
    }

    /**
     * Notifies the styling panel that this feature has changed.
     */
    private void notifyChange() {
        onChange.run();
    }
}