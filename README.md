# CSC360 - Member 4 - Week 1

## Week 1 Progress
Implemented the initial mouse interaction functionality for the graphics editor.

### Completed
- Created `ObjectInteractionController`
- Added mouse press detection
- Added mouse drag detection
- Updated object X/Y position while dragging
- Tested the interaction with a JavaFX Rectangle
- Kept interaction logic separate from the JavaFX UI

## Run
Requirements:
- JDK 21
- Maven

From the project root:

```powershell
mvn clean javafx:run
```

Then click and drag the blue rectangle.

## Git
Recommended branch:

```powershell
git switch -c feature/member4-interaction
git add .
git commit -m "Implement mouse object dragging"
git push -u origin feature/member4-interaction
```
