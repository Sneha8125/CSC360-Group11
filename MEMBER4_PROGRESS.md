# Member 4 Progress

## Scope
- Mouse drag interaction
- Keyboard arrow-key controls
- Apply / Reset behavior
- UI-to-object synchronization
- Basic error handling
- Integration demo and unit tests

## Files added
- `src/main/java/member4/ObjectInteractionController.java`
- `src/main/java/member4/Member4Demo.java`
- `src/test/java/member4/ObjectInteractionControllerTest.java`
- `pom.xml`

## How to run
From the repository root:

```powershell
mvn clean test
mvn javafx:run
```

## Manual test checklist
1. Drag the rectangle with the mouse.
2. Click the rectangle and use the arrow keys.
3. Change X/Y and press Apply.
4. Change rotation, scale, opacity, or fill color and press Apply.
5. Press Reset and confirm the transform returns to the selected starting state.
6. Enter invalid X/Y values and press Apply; an error dialog should appear instead of crashing.
