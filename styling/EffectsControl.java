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
 * Styling feature that controls visual effects applied to a JavaFX Shape.
 *
 * <p>Supports Drop Shadow and Glow. When both are enabled, the effects
 * are chained together using JavaFX's effect input mechanism.</p>
 */
public final class EffectsControl implements StyleFeature {

    private static final double GLOW_LEVEL = 0.8;
    private static final double SHADOW_RADIUS = 15.0;
    private static final Color SHADOW_COLOR =
            Color.rgb(0, 0, 0, 0.5);

    private final CheckBox dropShadowCheck =
            new CheckBox("Drop shadow");

    private final CheckBox glowCheck =
            new CheckBox("Glow");

    private final VBox view;

    private Runnable onChange = () -> {};

    public EffectsControl() {
        registerListeners();

        view = UiUtil.section(
                "Visual Effects",
                dropShadowCheck,
                glowCheck
        );
    }

    /**
     * Registers listeners for the effect controls.
     */
    private void registerListeners() {
        dropShadowCheck.selectedProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );

        glowCheck.selectedProperty().addListener(
                (obs, oldValue, newValue) -> notifyChange()
        );
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void applyTo(Shape shape) {
        shape.setEffect(createEffect());
    }

    /**
     * Creates the currently selected effect chain.
     *
     * @return the configured effect, or null when no effects are selected
     */
    private Effect createEffect() {
        Effect effect = null;

        if (glowCheck.isSelected()) {
            effect = createGlow();
        }

        if (dropShadowCheck.isSelected()) {
            effect = createDropShadow(effect);
        }

        return effect;
    }

    /**
     * Creates the glow effect.
     */
    private Glow createGlow() {
        return new Glow(GLOW_LEVEL);
    }

    /**
     * Creates a drop shadow and optionally chains another effect into it.
     */
    private DropShadow createDropShadow(Effect inputEffect) {
        DropShadow shadow = new DropShadow(
                SHADOW_RADIUS,
                SHADOW_COLOR
        );

        shadow.setInput(inputEffect);

        return shadow;
    }

    @Override
    public void resetToDefault() {
        dropShadowCheck.setSelected(false);
        glowCheck.setSelected(false);
    }

    @Override
    public void setOnChange(Runnable onChange) {
        this.onChange = onChange != null
                ? onChange
                : () -> {};
    }

    /**
     * Notifies the styling panel that an effect setting changed.
     */
    private void notifyChange() {
        onChange.run();
    }
}