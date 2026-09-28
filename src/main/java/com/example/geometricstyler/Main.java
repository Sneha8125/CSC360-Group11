package com.example.geometricStyler;

import com.example.geometricStyler.model.CircleObject;
import com.example.geometricStyler.model.EllipseObject;
import com.example.geometricStyler.model.GeometricObject;
import com.example.geometricStyler.model.ObjectFactory;
import com.example.geometricStyler.model.PolygonObject;
import com.example.geometricStyler.model.RectangleObject;
import com.example.geometricStyler.styling.StyleManager;
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

    // Stage 4 styling manager
    private StyleManager styleManager;

    @Override
    public void start(Stage stage) {

        // ---------------------------------------------------------
        // MAIN LAYOUT
        // ---------------------------------------------------------

        BorderPane root = new BorderPane();

        // ---------------------------------------------------------
        // CREATE UI SECTIONS
        // ---------------------------------------------------------

        controlPanel = new ControlPanel();
        previewPane = new PreviewPane();

        // Stage 4 styling manager
        styleManager = new StyleManager();

        // ---------------------------------------------------------
        // SCROLLABLE CONTROL PANEL
        // ---------------------------------------------------------

        ScrollPane controlScrollPane = new ScrollPane(
                controlPanel
        );

        controlScrollPane.setFitToWidth(true);
        controlScrollPane.setFitToHeight(false);

        controlScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        controlScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        controlScrollPane.setPrefWidth(370);

        controlScrollPane.getStyleClass().add(
                "control-scroll-pane"
        );

        // ---------------------------------------------------------
        // PLACE UI SECTIONS
        // ---------------------------------------------------------

        root.setLeft(controlScrollPane);
        root.setCenter(previewPane);

        // ---------------------------------------------------------
        // CREATE SCENE
        // ---------------------------------------------------------

        Scene scene = new Scene(
                root,
                1100,
                700
        );

        // ---------------------------------------------------------
        // LOAD CSS
        // ---------------------------------------------------------

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        // ---------------------------------------------------------
        // STAGE CONFIGURATION
        // ---------------------------------------------------------

        stage.setTitle(
                "Geometric Object Styler"
        );

        stage.setScene(scene);

        stage.setMinWidth(900);
        stage.setMinHeight(600);

        // ---------------------------------------------------------
        // STAGE 3
        // INITIAL OBJECT + CONTROLS
        // ---------------------------------------------------------

        createInitialObject();

        setupObjectSelection();

        setupTransformations();

        // ---------------------------------------------------------
        // STAGE 4.1
        // STYLING CONTROLS
        // ---------------------------------------------------------

        setupStyling();

        stage.show();
    }

    // =============================================================
    // CREATE INITIAL OBJECT
    // =============================================================

    private void createInitialObject() {

        currentObject = ObjectFactory.create("Circle");

        updateControlValues();

        updatePreview();
    }

    // =============================================================
    // OBJECT SELECTION
    // =============================================================

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

                    // Reset styling for the newly selected object
                    styleManager.reset(
                            currentObject.createNode()
                    );

                    updateControlValues();

                    updatePreview();
                });
    }

    // =============================================================
    // TRANSFORMATIONS
    // =============================================================

    private void setupTransformations() {

        // ---------------------------------------------------------
        // X POSITION
        // ---------------------------------------------------------

        controlPanel
                .getXPositionField()
                .setOnAction(event -> updatePosition());

        // ---------------------------------------------------------
        // Y POSITION
        // ---------------------------------------------------------

        controlPanel
                .getYPositionField()
                .setOnAction(event -> updatePosition());

        // ---------------------------------------------------------
        // WIDTH
        // ---------------------------------------------------------

        controlPanel
                .getWidthField()
                .setOnAction(event -> updateDimensions());

        // ---------------------------------------------------------
        // HEIGHT
        // ---------------------------------------------------------

        controlPanel
                .getHeightField()
                .setOnAction(event -> updateDimensions());

        // ---------------------------------------------------------
        // ROTATION
        // ---------------------------------------------------------

        controlPanel
                .getRotationSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            currentObject.setRotation(
                                    newValue.doubleValue()
                            );

                            refreshObject();
                        }
                );

        // ---------------------------------------------------------
        // SCALE X
        // ---------------------------------------------------------

        controlPanel
                .getScaleXSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            currentObject.setScaleX(
                                    newValue.doubleValue()
                            );

                            refreshObject();
                        }
                );

        // ---------------------------------------------------------
        // SCALE Y
        // ---------------------------------------------------------

        controlPanel
                .getScaleYSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            currentObject.setScaleY(
                                    newValue.doubleValue()
                            );

                            refreshObject();
                        }
                );

        // ---------------------------------------------------------
        // TRANSLATE X
        // ---------------------------------------------------------

        controlPanel
                .getTranslateXSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            currentObject.setTranslateX(
                                    newValue.doubleValue()
                            );

                            refreshObject();
                        }
                );

        // ---------------------------------------------------------
        // TRANSLATE Y
        // ---------------------------------------------------------

        controlPanel
                .getTranslateYSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            currentObject.setTranslateY(
                                    newValue.doubleValue()
                            );

                            refreshObject();
                        }
                );

        // ---------------------------------------------------------
        // CENTER
        // ---------------------------------------------------------

        controlPanel
                .getCenterButton()
                .setOnAction(event -> {

                    currentObject.setX(350);
                    currentObject.setY(275);

                    updatePositionFields();

                    refreshObject();
                });

        // ---------------------------------------------------------
        // FLIP HORIZONTAL
        // ---------------------------------------------------------

        controlPanel
                .getFlipHButton()
                .setOnAction(event -> {

                    currentObject.setScaleX(
                            -currentObject.getScaleX()
                    );

                    refreshObject();
                });

        // ---------------------------------------------------------
        // FLIP VERTICAL
        // ---------------------------------------------------------

        controlPanel
                .getFlipVButton()
                .setOnAction(event -> {

                    currentObject.setScaleY(
                            -currentObject.getScaleY()
                    );

                    refreshObject();
                });

        // ---------------------------------------------------------
        // RESET
        // ---------------------------------------------------------

        controlPanel
                .getResetButton()
                .setOnAction(event -> {

                    String type =
                            controlPanel
                                    .getObjectTypeComboBox()
                                    .getValue();

                    currentObject =
                            ObjectFactory.create(type);

                    styleManager.reset(
                            currentObject.createNode()
                    );

                    updateControlValues();

                    updatePreview();
                });
    }

    // =============================================================
    // STAGE 4.1 - STYLING
    // =============================================================

    private void setupStyling() {

        // ---------------------------------------------------------
        // FILL COLOR
        // ---------------------------------------------------------

        controlPanel
                .getFillColorPicker()
                .setOnAction(event -> {

                    Node node = currentObject.createNode();

                    styleManager.setFillColor(
                            controlPanel
                                    .getFillColorPicker()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // ---------------------------------------------------------
        // BORDER COLOR
        // ---------------------------------------------------------

        controlPanel
                .getBorderColorPicker()
                .setOnAction(event -> {

                    Node node = currentObject.createNode();

                    styleManager.setStrokeColor(
                            controlPanel
                                    .getBorderColorPicker()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // ---------------------------------------------------------
        // BORDER WIDTH
        // ---------------------------------------------------------

        controlPanel
                .getBorderWidthSlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            Node node =
                                    currentObject.createNode();

                            styleManager.setStrokeWidth(
                                    newValue.doubleValue(),
                                    node
                            );

                            updatePreview();
                        }
                );

        // ---------------------------------------------------------
        // BORDER STYLE
        // ---------------------------------------------------------

        controlPanel
                .getBorderStyleComboBox()
                .setOnAction(event -> {

                    Node node = currentObject.createNode();

                    styleManager.setStrokeStyle(
                            controlPanel
                                    .getBorderStyleComboBox()
                                    .getValue(),
                            node
                    );

                    updatePreview();
                });

        // ---------------------------------------------------------
        // OPACITY
        // ---------------------------------------------------------

        controlPanel
                .getOpacitySlider()
                .valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            Node node =
                                    currentObject.createNode();

                            styleManager.setOpacity(
                                    newValue.doubleValue(),
                                    node
                            );

                            updatePreview();
                        }
                );

        // ---------------------------------------------------------
        // APPLY
        // ---------------------------------------------------------

        controlPanel
                .getApplyButton()
                .setOnAction(event -> {

                    applyCurrentStyle();

                    updatePreview();
                });
    }

    // =============================================================
    // APPLY CURRENT STYLE
    // =============================================================

    private void applyCurrentStyle() {

        Node node = currentObject.createNode();

        styleManager.applyStyle(node);

        updatePreview();
    }

    // =============================================================
    // UPDATE POSITION
    // =============================================================

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

            // Validation will be added later.
        }
    }

    // =============================================================
    // UPDATE DIMENSIONS
    // =============================================================

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

            // Validation will be added later.
        }
    }

    // =============================================================
    // REFRESH OBJECT
    // =============================================================

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

    // =============================================================
    // UPDATE PREVIEW
    // =============================================================

    private void updatePreview() {

        Node node =
                currentObject.createNode();

        // Apply current Stage 4 styling
        styleManager.applyStyle(node);

        previewPane.setObject(node);
    }

    // =============================================================
    // UPDATE POSITION FIELDS
    // =============================================================

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

    // =============================================================
    // UPDATE CONTROL VALUES
    // =============================================================

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

    // =============================================================
    // MAIN
    // =============================================================

    public static void main(String[] args) {

        launch(args);
    }
}