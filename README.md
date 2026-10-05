# Line Clipping Exercise

Java OpenGL draft exploring clipping a line against a rectangular window.

## How it works

The display routine draws a fixed clipping rectangle and sample line, then performs selected boundary checks and draws an additional segment. A Bresenham-style point routine renders the lines.

## Usage

Requires a Java Development Kit, a desktop display and a legacy JOGL installation compatible with the `javax.media.opengl` API. Configure the JOGL JARs and native libraries in the Java classpath, then compile `src/Cohen_Sutherland/Cohen_Sutherland.java` and run `Cohen_Sutherland.Cohen_Sutherland`.

## Notes

The source does not implement the complete Cohen–Sutherland outcode and clipping loop. Boundary handling is partial, and slope calculation uses integer division. Treat it as an unfinished graphics exercise.
