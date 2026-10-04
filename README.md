# Assignment 3: Bridge Pattern — Drawing Shapes

- **Name:** Akmarzhan
- **Group:** SE-2538
- **Topic:** A — Drawing Shapes

- **Base Commit:** db40973
- **Submitted Commit:** c966a65

## Role Map 

| Role | Class | Source Path |
|------|-------|-------------|
| Abstraction | Shape | src/Shape.java |
| A1 | Circle | src/Circle.java |
| A2 | Square | src/Square.java |
| Implementor | Renderer | src/Renderer.java |
| I1 | VectorRenderer | src/VectorRenderer.java |
| I2 | RasterRenderer | src/RasterRenderer.java |
| I3 | AsciiRenderer | src/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Key elements

- **Bridge field:** `Shape.renderer` — interface-typed reference (line 3 of Shape.java)
- **Abstract operation:** `Shape.execute()` — implemented in Circle.java and Square.java
- **Runtime setter:** `Shape.setImplementation(Renderer)` — allows swapping at runtime
- **T5 check:** in `Main.runDemo()` — proves same object identity via `==`

## Build & Run

```bash
javac --release 17 -encoding UTF-8 -d out @sources.txt
java -cp out Main --demo