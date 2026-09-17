package styling;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Feature: Visual Effects.
 * Drop shadow and glow, which can be combined by chaining glow as the
 * drop shadow's input (JavaFX's standard way to stack effects).
 */
public class EffectsControl implements StyleFeature {

    private final CheckBox dropShadowCheck = new CheckBox("Drop shadow");
    private final CheckBox glowCheck = new CheckBox("Glow");
    private final VBox view;

    private Runnable onChange = () -> {};

    public EffectsControl() {
        dropShadowCheck.selectedProperty().addListener((obs, o, n) -> fireChange());
        glowCheck.selectedProperty().addListener((obs, o, n) -> fireChange());

        view = UiUtil.section("Visual Effects", dropShadowCheck, glowCheck);
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        Effect effect = null;
        if (glowCheck.isSelected()) {
            effect = new Glow(0.8);
        }
        if (dropShadowCheck.isSelected()) {
            DropShadow shadow = new DropShadow(15, Color.rgb(0, 0, 0, 0.5));
            shadow.setInput(effect); // chains glow underneath, or null if unused
            effect = shadow;
        }
        shape.setEffect(effect);
    }

    @Override
    public void resetToDefault() {
        dropShadowCheck.setSelected(false);
        glowCheck.setSelected(false);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }

    private void fireChange() {
        onChange.run();
    }
}