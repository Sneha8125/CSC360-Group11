package styling;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Styling feature that controls the border/stroke of a JavaFX Shape.
 *
 * <p>Supports stroke color, stroke width, and solid/dashed border styles.</p>
 */
public final class BorderStyleControl implements StyleFeature {

    private static final Color DEFAULT_STROKE =
            Color.web("#2c3e50");

    private static final double MIN_WIDTH = 0.0;
    private static final double MAX_WIDTH = 15.0;
    private static final double DEFAULT_WIDTH = 2.0;

    private static final double DASH_LENGTH = 12.0;
    private static final double GAP_LENGTH = 8.0;

    private final ColorPicker strokePicker =
            new ColorPicker(DEFAULT_STROKE);

    private final Slider widthSlider =
            UiUtil.slider(MIN_WIDTH, MAX_WIDTH, DEFAULT_WIDTH);

    private final CheckBox dashedCheck =
            new CheckBox("Dashed border");

    private final VBox view;

    private Runnable onChange = () -> {};

    public BorderStyleControl() {
        registerListeners();

        view = UiUtil.section(
                "Border / Stroke",
                UiUtil.labeledRow("Stroke Color", strokePicker),
                UiUtil.labeledRow("Stroke Width", widthSlider),
                dashedCheck
        );
    }

    /**
     * Registers listeners for all border controls.
     */
    private void registerListeners() {
        strokePicker.valueProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );

        widthSlider.valueProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );

        dashedCheck.selectedProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        applyStrokeColor(shape);
        applyStrokeWidth(shape);
        applyStrokeStyle(shape);
    }

    /**
     * Applies the selected stroke color.
     */
    private void applyStrokeColor(Shape shape) {
        shape.setStroke(strokePicker.getValue());
    }

    /**
     * Applies the selected stroke width.
     */
    private void applyStrokeWidth(Shape shape) {
        shape.setStrokeWidth(widthSlider.getValue());
    }

    /**
     * Applies either a solid or dashed stroke.
     */
    private void applyStrokeStyle(Shape shape) {
        if (dashedCheck.isSelected()) {
            shape.getStrokeDashArray().setAll(
                    DASH_LENGTH,
                    GAP_LENGTH
            );
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
        this.onChange = onChange != null
                ? onChange
                : () -> {};
    }

    /**
     * Notifies the styling panel that a border setting changed.
     */
    private void notifyChange() {
        onChange.run();
    }
}