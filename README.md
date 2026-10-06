# Geometric Object Styler

**Course:** CSC360 – Computer Graphics and Digital Image Processing
**Assignment:** Write a JavaFX program with UI to style a single geometric object.

## 🎥 Project Demo

<video src="https://github.com/Sneha8125/CSC360-Group11/blob/main/docs/screenshots/Demo.mp4" controls width="800"></video>

## Description

Geometric Object Styler is a JavaFX desktop application for working with **one**
geometric object at a time. The user picks a shape, then moves, resizes, rotates,
scales, translates, fills, outlines and adds effects to it, either through the
control panel on the left or directly with the mouse and keyboard on the preview.

It is deliberately not a multi-object drawing program: exactly one object is alive
at any moment, which keeps the focus on the styling and transformation operations
taught in the course.

## Objective

- Practise the JavaFX application lifecycle (Stage, Scene, layouts, CSS).
- Apply 2D geometric transformations: translation, rotation, scaling and reflection.
- Apply raster/vector styling: solid fills, linear and radial gradients, stroke
  styles, opacity and image effects (drop shadow, glow).
- Handle user interaction (mouse picking, dragging, keyboard control) together
  with input validation and boundary clamping.

## Features

**Object**
- Five object types: Circle, Rectangle, Square, Ellipse, Polygon (five point star)
- Only one object is visible at a time; switching type keeps position, size,
  transforms and styling
- Circle and Square keep their width and height equal automatically

**Position and transformation**
- X / Y position with slider and editable number box
- Width and height with sensible minimum and maximum values
- Rotation from 0° to 360° around the object centre
- Scale X and Scale Y, with an optional "keep scale uniform" link
- Translation (translateX / translateY) kept separate from the base position
- Center object button
- Flip horizontal and flip vertical

**Appearance**
- Fill colour picker
- Fill type: solid, linear gradient, radial gradient (with start and end colours)
- Border colour, border width (0–20 px) and border style (solid, dashed, dotted)
- Opacity from 0 % to 100 %
- Drop shadow and glow effects, which can be combined

**Interaction**
- Click to select the object (the preview border turns to the accent colour)
- Drag the object with the mouse
- Keyboard shortcuts for moving, scaling, centring and resetting
- Apply and Reset buttons
- Live property display and a status line for feedback
- Input validation with clear messages; the object can never be lost off screen

## Technologies

| Item | Version |
|------|---------|
| Java | 17 or newer (developed on Java 21) |
| JavaFX | 21.0.4 |
| Build tool | Maven |
| Tests | JUnit 5 |

No FXML, no Spring, no database, no external UI libraries. Plain JavaFX only.

## Project architecture

```
GeometricObjectStyler/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/geometricstyler/
    │   │   ├── Main.java                  Stage, Scene, outer layout only
    │   │   ├── app/
    │   │   │   └── StylerController.java  Wires UI <-> model <-> styling
    │   │   ├── model/
    │   │   │   ├── GeometricObject.java   Geometry + transforms (abstract base)
    │   │   │   ├── ObjectStyle.java       Fill, stroke, opacity, effect settings
    │   │   │   ├── ShapeType.java
    │   │   │   ├── FillType.java
    │   │   │   └── StrokeStyle.java
    │   │   ├── object/
    │   │   │   ├── CircleObject.java
    │   │   │   ├── RectangleObject.java
    │   │   │   ├── SquareObject.java
    │   │   │   ├── EllipseObject.java
    │   │   │   ├── PolygonObject.java
    │   │   │   └── ObjectFactory.java     Creates the one active object
    │   │   ├── ui/
    │   │   │   ├── ControlPanel.java      Left panel, builds every control
    │   │   │   ├── PreviewPane.java       Drawing area, holds one object
    │   │   │   ├── PropertyView.java      Current object + status line
    │   │   │   ├── SliderField.java       Reusable slider + numeric box
    │   │   │   └── UiFactory.java         Section and row helpers
    │   │   ├── styling/
    │   │   │   ├── FillManager.java       Colour and gradient paints
    │   │   │   ├── StrokeManager.java     Border colour, width, dashes
    │   │   │   ├── EffectManager.java     Drop shadow and glow
    │   │   │   └── StyleApplier.java      Applies all three at once
    │   │   ├── interaction/
    │   │   │   ├── MouseController.java   Selection and dragging
    │   │   │   ├── KeyboardController.java Shortcuts
    │   │   │   └── ShortcutActions.java   Actions the shortcuts trigger
    │   │   └── util/
    │   │       ├── Defaults.java          Every default value and limit
    │   │       └── ValidationUtil.java    Clamping and safe parsing
    │   └── resources/
    │       └── style.css
    └── test/java/com/example/geometricstyler/util/
        └── ValidationUtilTest.java
```

### Two design decisions worth knowing

**Position versus translation.** `x` / `y` is the base position of the object
centre and is written into `layoutX` / `layoutY`. `translateX` / `translateY` is
an extra offset on top of it, using the JavaFX translate properties. What the user
sees is `x + translateX`. To stop the two from contradicting each other, dragging
and the arrow keys write into `x` / `y` and fold any translation into them, while
the translation sliders only touch the translate properties. If a translation
would push the object out of the preview, it is trimmed back automatically.

**Live preview versus Apply.** Sliders, colour pickers, combo boxes and check
boxes update the object immediately, which makes the tool pleasant to use. Numbers
typed into the small text boxes are only committed when the user presses Enter,
leaves the box, or presses **Apply**. Apply therefore always has real work to do:
it validates and commits every typed value and re-applies the full style.

## How to run

Requirements: JDK 17 or newer and Maven 3.6 or newer on the PATH.

```bash
cd GeometricObjectStyler
mvn clean compile javafx:run
```

To run it again without rebuilding:

```bash
mvn javafx:run
```

To run the unit tests:

```bash
mvn test
```

The first run downloads the JavaFX artifacts from Maven Central, so an internet
connection is needed once.

## Controls

**Mouse**

| Action | Result |
|--------|--------|
| Click the object | Selects it (the preview border turns teal) |
| Drag the object | Moves it; the X and Y values follow |
| Click the empty preview | Clears the selection |

**Keyboard**

| Key | Result |
|-----|--------|
| Arrow Up / Down / Left / Right | Move the object by 5 px |
| Shift + Arrow | Move the object by 20 px |
| `+` | Increase the scale by 0.1 |
| `-` | Decrease the scale by 0.1 |
| `C` | Centre the object |
| `R` | Reset everything to defaults |

Shortcuts are ignored while a text box, slider or colour picker has the focus, so
typing a number never moves the object by accident.

## Defaults used by Reset

| Property | Value |
|----------|-------|
| Object | Circle |
| Position | Centre of the preview |
| Size | 100 × 100 |
| Rotation | 0° |
| Scale | 1.0 |
| Fill | Light blue, solid |
| Border | Black, 3 px, solid |
| Opacity | 100 % |
| Drop shadow / Glow | Off |

## Team members and branch responsibilities

Team member names are placeholders; replace them with the real names.

| Member | Branch | Responsibilities |
|--------|--------|------------------|
| Member 1 – *name* | `feature/ui-layout` | Stage, Scene, main layout, control panel, preview area, labels, buttons, colour pickers, sliders, combo boxes, property display, tooltips, CSS, responsiveness |
| Member 2 – *name* | `feature/object-transform` | Object model, the five shapes, object selection architecture, X/Y, width/height, rotation, scale, translation, center, flip |
| Member 3 – Kunjal Agarwal | `feature/styling-effects` | Fill, solid fill, linear gradient, radial gradient, stroke color, stroke width, stroke style, opacity, drop shadow, glow |
| Member 4 – *name* | `feature/interaction-integration` | Mouse selection, dragging, keyboard controls, Apply, Reset, validation, error handling, integration, testing, debugging |

The file layout follows this split, so each member works mainly inside their own
package and only `StylerController` connects the pieces together.

## Testing

Automated tests (`mvn test`) cover the validation rules: clamping, rejecting text
that is not a number, refusing negative sizes, keeping scale positive, keeping
opacity between 0 and 1, and normalising rotation into 0–360.

Manual test checklist:

| # | Test | Status |
|---|------|--------|
| 1 | Application starts successfully | Pass |
| 2 | Circle appears centred at startup | Pass |
| 3 | Object selection works | Pass |
| 4 | X / Y controls move the object | Pass |
| 5 | Width and height controls work | Pass |
| 6 | Rotation works | Pass |
| 7 | Scale works | Pass |
| 8 | Translation works | Pass |
| 9 | Center object works | Pass |
| 10 | Flip horizontal and vertical work | Pass |
| 11 | Fill colour works | Pass |
| 12 | Linear gradient works | Pass |
| 13 | Radial gradient works | Pass |
| 14 | Stroke colour works | Pass |
| 15 | Stroke width works | Pass |
| 16 | Stroke style (solid/dashed/dotted) works | Pass |
| 17 | Opacity works | Pass |
| 18 | Drop shadow works | Pass |
| 19 | Glow works | Pass |
| 20 | Mouse dragging works | Pass |
| 21 | Keyboard controls work | Pass |
| 22 | Apply works | Pass |
| 23 | Reset works | Pass |
| 24 | Invalid values do not crash the application | Pass |
| 25 | Object stays inside the preview | Pass |
| 26 | Window resizing works | Pass |
| 27 | All four members' features work together | Pass |
| 28 | No console exceptions | Pass |

## Screenshots

Add the screenshots here before submitting.

```
docs/screenshots/
├── 01-startup.png
├── 02-gradients.png
├── 03-effects.png
└── 04-polygon-rotated.png
```

| Screenshot | Description |
|------------|-------------|
| `01-startup.png` | Default circle, control panel and property display |
| `02-gradients.png` | Linear and radial gradient fills |
| `03-effects.png` | Drop shadow, glow and reduced opacity |
| `04-polygon-rotated.png` | Polygon with rotation and scaling applied |

## Possible extensions

These were left out on purpose so the required feature set stays clean and easy
to explain: undo/redo, saved style presets, a light/dark theme switch, and saving
or loading a style to a file.
