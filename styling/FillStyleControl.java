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
 * Feature: Fill Color.
 * Supports a solid fill color, or a linear gradient between two colors.
 */
public class FillStyleControl implements StyleFeature {

    private static final Color DEFAULT_FILL = Color.LIGHTBLUE;
    private static final Color DEFAULT_GRADIENT_END = Color.WHITE;

    private final ColorPicker fillPicker = new ColorPicker(DEFAULT_FILL);
    private final ColorPicker gradientEndPicker = new ColorPicker(DEFAULT_GRADIENT_END);
    private final RadioButton solidRadio = new RadioButton("Solid");
    private final RadioButton gradientRadio = new RadioButton("Gradient");
    private final VBox view;

    private Runnable onChange = () -> {};

    public FillStyleControl() {
        gradientEndPicker.setDisable(true);

        ToggleGroup group = new ToggleGroup();
        solidRadio.setToggleGroup(group);
        gradientRadio.setToggleGroup(group);
        solidRadio.setSelected(true);

        group.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            gradientEndPicker.setDisable(newT != gradientRadio);
            fireChange();
        });
        fillPicker.valueProperty().addListener((obs, o, n) -> fireChange());
        gradientEndPicker.valueProperty().addListener((obs, o, n) -> fireChange());

        view = UiUtil.section("Fill Color",
                new HBox(10, solidRadio, gradientRadio),
                UiUtil.labeledRow("Color", fillPicker),
                UiUtil.labeledRow("Gradient End", gradientEndPicker));
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        if (gradientRadio.isSelected()) {
            LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, fillPicker.getValue()),
                    new Stop(1, gradientEndPicker.getValue()));
            shape.setFill(gradient);
        } else {
            shape.setFill(fillPicker.getValue());
        }
    }

    @Override
    public void resetToDefault() {
        solidRadio.setSelected(true);
        fillPicker.setValue(DEFAULT_FILL);
        gradientEndPicker.setValue(DEFAULT_GRADIENT_END);
        gradientEndPicker.setDisable(true);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }

    private void fireChange() {
        onChange.run();
    }
}