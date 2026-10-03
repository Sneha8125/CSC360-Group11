package styling;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;

/**
 * Styling feature that controls the fill of a JavaFX Shape.
 *
 * <p>Supports either a solid color or a diagonal linear gradient
 * between two selected colors.</p>
 */
public final class FillStyleControl implements StyleFeature {

    private static final Color DEFAULT_FILL = Color.LIGHTBLUE;
    private static final Color DEFAULT_GRADIENT_END = Color.WHITE;

    private static final double GRADIENT_START_X = 0;
    private static final double GRADIENT_START_Y = 0;
    private static final double GRADIENT_END_X = 1;
    private static final double GRADIENT_END_Y = 1;

    private final ColorPicker fillPicker =
            new ColorPicker(DEFAULT_FILL);

    private final ColorPicker gradientEndPicker =
            new ColorPicker(DEFAULT_GRADIENT_END);

    private final RadioButton solidRadio =
            new RadioButton("Solid");

    private final RadioButton gradientRadio =
            new RadioButton("Gradient");

    private final VBox view;

    private Runnable onChange = () -> {};

    public FillStyleControl() {
        configureFillMode();
        registerListeners();

        view = UiUtil.section(
                "Fill Color",
                new HBox(10, solidRadio, gradientRadio),
                UiUtil.labeledRow("Color", fillPicker),
                UiUtil.labeledRow("Gradient End", gradientEndPicker)
        );
    }

    /**
     * Configures the solid/gradient radio buttons.
     */
    private void configureFillMode() {
        ToggleGroup group = new ToggleGroup();

        solidRadio.setToggleGroup(group);
        gradientRadio.setToggleGroup(group);

        solidRadio.setSelected(true);

        updateGradientPickerState();
    }

    /**
     * Registers listeners for all controls.
     */
    private void registerListeners() {
        solidRadio.selectedProperty().addListener(
                (obs, oldValue, newValue) -> {
                    updateGradientPickerState();
                    notifyChange();
                }
        );

        gradientRadio.selectedProperty().addListener(
                (obs, oldValue, newValue) -> {
                    updateGradientPickerState();
                    notifyChange();
                }
        );

        fillPicker.valueProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );

        gradientEndPicker.valueProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );
    }

    /**
     * Enables the gradient end color picker only when gradient mode
     * is selected.
     */
    private void updateGradientPickerState() {
        gradientEndPicker.setDisable(!gradientRadio.isSelected());
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        if (gradientRadio.isSelected()) {
            shape.setFill(createGradient());
        } else {
            shape.setFill(fillPicker.getValue());
        }
    }

    /**
     * Creates the currently selected linear gradient.
     */
    private LinearGradient createGradient() {
        return new LinearGradient(
                GRADIENT_START_X,
                GRADIENT_START_Y,
                GRADIENT_END_X,
                GRADIENT_END_Y,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, fillPicker.getValue()),
                new Stop(1, gradientEndPicker.getValue())
        );
    }

    @Override
    public void resetToDefault() {
        solidRadio.setSelected(true);
        fillPicker.setValue(DEFAULT_FILL);
        gradientEndPicker.setValue(DEFAULT_GRADIENT_END);

        updateGradientPickerState();
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange != null
                ? onChange
                : () -> {};
    }

    /**
     * Notifies the styling panel that this feature changed.
     */
    private void notifyChange() {
        onChange.run();
    }
}