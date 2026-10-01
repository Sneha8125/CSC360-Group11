package com.example.geometricstyler.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Small helpers that build the repeating pieces of the control panel.
 * Without them every section would repeat the same six lines of layout code.
 */
public final class UiFactory {

    private UiFactory() {
        // utility class, never instantiated
    }

    /** The heading above a group of controls, for example "Appearance". */
    public static Label sectionTitle(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("section-title");
        return label;
    }

    /** A group of controls under one heading. */
    public static VBox section(String title, Node... controls) {
        VBox box = new VBox(8);
        box.getStyleClass().add("section");
        box.getChildren().add(sectionTitle(title));
        box.getChildren().addAll(controls);
        return box;
    }

    /** A caption on the left and a control pushed to the right. */
    public static HBox labelledRow(String caption, Node control) {
        Label label = new Label(caption);
        label.getStyleClass().add("field-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(6, label, spacer, control);
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("control-row");
        return row;
    }

    /** Buttons side by side, each taking an equal share of the width. */
    public static HBox buttonRow(Node... buttons) {
        HBox row = new HBox(8, buttons);
        row.setAlignment(Pos.CENTER_LEFT);
        for (Node button : buttons) {
            HBox.setHgrow(button, Priority.ALWAYS);
            if (button instanceof Region) {
                ((Region) button).setMaxWidth(Double.MAX_VALUE);
            }
        }
        return row;
    }
}
