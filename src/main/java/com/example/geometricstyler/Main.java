package com.example.geometricStyler;

import com.example.geometricStyler.model.CircleObject;
import com.example.geometricStyler.model.EllipseObject;
import com.example.geometricStyler.model.GeometricObject;
import com.example.geometricStyler.model.ObjectFactory;
import com.example.geometricStyler.model.PolygonObject;
import com.example.geometricStyler.model.RectangleObject;
import com.example.geometricStyler.ui.ControlPanel;
import com.example.geometricStyler.ui.PreviewPane;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private GeometricObject currentObject;

    private ControlPanel controlPanel;
    private PreviewPane previewPane;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        controlPanel = new ControlPanel();
        previewPane = new PreviewPane();

        ScrollPane controlScrollPane =
                new ScrollPane(controlPanel);

        controlScrollPane.setFitToWidth(true);
        controlScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );
        controlScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );
        controlScrollPane.setPrefWidth(370);

        controlScrollPane
                .getStyleClass()
                .add("control-scroll-pane");

        root.setLeft(controlScrollPane);
        root.setCenter(previewPane);

        Scene scene = new Scene(root, 1100, 700);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        stage.setTitle("Geometric Object Styler");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        createInitialObject();
        setupObjectSelection();
        setupTransformations();

        stage.show();
    }

    private void createInitialObject() {

        currentObject =
                ObjectFactory.create("Circle");

        updatePreview();
    }

    private void setupObjectSelection() {

        controlPanel
                .getObjectTypeComboBox()
                .setOnAction(event -> {

                    String type =
                            controlPanel
                                    .getObjectTypeComboBox()
                                    .getValue();

                    currentObject =
                            ObjectFactory.create(type);

                    updatePreview();
                });
    }

    private void setupTransformations() {

        controlPanel
                .getXPositionField()
                .setOnAction(event -> updatePosition());

        controlPanel
                .getYPositionField()
                .setOnAction(event -> updatePosition());

        controlPanel
                .getWidthField()
                .setOnAction(event -> updateDimensions());

        controlPanel
                .getHeightField()
                .setOnAction(event -> updateDimensions());

        controlPanel
                .getRotationSlider()
                .valueProperty()
                .addListener((obs, oldValue, newValue) -> {

                    currentObject.setRotation(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        controlPanel
                .getScaleXSlider()
                .valueProperty()
                .addListener((obs, oldValue, newValue) -> {

                    currentObject.setScaleX(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        controlPanel
                .getScaleYSlider()
                .valueProperty()
                .addListener((obs, oldValue, newValue) -> {

                    currentObject.setScaleY(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        controlPanel
                .getTranslateXSlider()
                .valueProperty()
                .addListener((obs, oldValue, newValue) -> {

                    currentObject.setTranslateX(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        controlPanel
                .getTranslateYSlider()
                .valueProperty()
                .addListener((obs, oldValue, newValue) -> {

                    currentObject.setTranslateY(
                            newValue.doubleValue()
                    );

                    refreshObject();
                });

        controlPanel
                .getCenterButton()
                .setOnAction(event -> {

                    currentObject.setX(350);
                    currentObject.setY(275);

                    updatePositionFields();
                    refreshObject();
                });

        controlPanel
                .getFlipHButton()
                .setOnAction(event -> {

                    currentObject.setScaleX(
                            -currentObject.getScaleX()
                    );

                    refreshObject();
                });

        controlPanel
                .getFlipVButton()
                .setOnAction(event -> {

                    currentObject.setScaleY(
                            -currentObject.getScaleY()
                    );

                    refreshObject();
                });

        controlPanel
                .getResetButton()
                .setOnAction(event -> {

                    String type =
                            controlPanel
                                    .getObjectTypeComboBox()
                                    .getValue();

                    currentObject =
                            ObjectFactory.create(type);

                    updateControlValues();
                    updatePreview();
                });
    }

    private void updatePosition() {

        try {

            currentObject.setX(
                    Double.parseDouble(
                            controlPanel
                                    .getXPositionField()
                                    .getText()
                    )
            );

            currentObject.setY(
                    Double.parseDouble(
                            controlPanel
                                    .getYPositionField()
                                    .getText()
                    )
            );

            refreshObject();

        } catch (NumberFormatException ignored) {
            // Validation will be handled in a later stage.
        }
    }

    private void updateDimensions() {

        try {

            currentObject.setWidth(
                    Double.parseDouble(
                            controlPanel
                                    .getWidthField()
                                    .getText()
                    )
            );

            currentObject.setHeight(
                    Double.parseDouble(
                            controlPanel
                                    .getHeightField()
                                    .getText()
                    )
            );

            refreshObject();

        } catch (NumberFormatException ignored) {
            // Validation will be handled in a later stage.
        }
    }

    private void refreshObject() {

        if (currentObject instanceof CircleObject object) {
            object.updateNode();

        } else if (currentObject instanceof RectangleObject object) {
            object.updateNode();

        } else if (currentObject instanceof EllipseObject object) {
            object.updateNode();

        } else if (currentObject instanceof PolygonObject object) {
            object.updateNode();
        }

        updatePreview();
    }

    private void updatePreview() {

        Node node =
                currentObject.createNode();

        previewPane.setObject(node);
    }

    private void updatePositionFields() {

        controlPanel
                .getXPositionField()
                .setText(
                        String.valueOf(
                                currentObject.getX()
                        )
                );

        controlPanel
                .getYPositionField()
                .setText(
                        String.valueOf(
                                currentObject.getY()
                        )
                );
    }

    private void updateControlValues() {

        controlPanel
                .getXPositionField()
                .setText(
                        String.valueOf(
                                currentObject.getX()
                        )
                );

        controlPanel
                .getYPositionField()
                .setText(
                        String.valueOf(
                                currentObject.getY()
                        )
                );

        controlPanel
                .getWidthField()
                .setText(
                        String.valueOf(
                                currentObject.getWidth()
                        )
                );

        controlPanel
                .getHeightField()
                .setText(
                        String.valueOf(
                                currentObject.getHeight()
                        )
                );

        controlPanel
                .getRotationSlider()
                .setValue(
                        currentObject.getRotation()
                );

        controlPanel
                .getScaleXSlider()
                .setValue(
                        currentObject.getScaleX()
                );

        controlPanel
                .getScaleYSlider()
                .setValue(
                        currentObject.getScaleY()
                );

        controlPanel
                .getTranslateXSlider()
                .setValue(
                        currentObject.getTranslateX()
                );

        controlPanel
                .getTranslateYSlider()
                .setValue(
                        currentObject.getTranslateY()
                );
    }

    public static void main(String[] args) {
        launch(args);
    }
}