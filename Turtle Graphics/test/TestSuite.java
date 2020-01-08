import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSuite {

  // Test that the pen is initially down
  @Test
  public void penInit() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle turtle = new Turtle(page, pen);

    turtle.move(Direction.SOUTH, 10);
    int numStrokes = countChars(page.toString(), '.');

    assertEquals("Pen should be up on creation", 0, numStrokes);
  }

  // Test that the pen state can be changed
  @Test
  public void penToggle() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle turtle = new Turtle(page, pen);

    turtle.penUp();
    turtle.move(Direction.SOUTH, 10);
    int numStrokes = countChars(page.toString(), '.');
    assertEquals("No pen stroke should be left after penUp called and move called", 0, numStrokes);

    turtle.penDown();
    turtle.move(Direction.EAST, 10);
    numStrokes = countChars(page.toString(), '.');
    assertTrue("Pen strokes should be left after penUp and move", numStrokes > 0);
  }

  // Test that putting the penUp when it is up is a no-op
  @Test
  public void penUpNoops() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle turtle = new Turtle(page, pen);

    turtle.penUp();
    turtle.penUp();
    turtle.move(Direction.SOUTH, 10);
    int numStrokes = countChars(page.toString(), '.');
    assertEquals(
        "No pen stroke should be left after penUp is called twice and move called", 0, numStrokes);
  }
  // Test that putting the penUp when it is up is a no-op
  @Test
  public void penDownNoops() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle turtle = new Turtle(page, pen);

    turtle.penDown();
    turtle.penDown();
    turtle.move(Direction.SOUTH, 10);
    int numStrokes = countChars(page.toString(), '.');
    assertTrue(
        "Pen stroke should be left after penDown called twice and move called", numStrokes > 0);
  }

  // Test that the correct Point was stored
  @Test
  public void penPointCorrect() {
    Pen pen = new Pen('.');
    assertEquals("Pen stroke character for new Pen('.') should be '.'", '.', pen.getStroke());
  }

  // Test that whitespace Points are invalid - enable assertions using -ea flag
  @Test(expected = AssertionError.class)
  public void penNoSpaceForPoint() {
    new Pen(' ');
  }

  // enable assertions using -ea flag
  @Test(expected = AssertionError.class)
  public void penNoNewlineForPoint() {
    new Pen('\n');
  }

  // enable assertions using -ea flag
  @Test(expected = AssertionError.class)
  public void penNoTabForPoint() {
    new Pen('\t');
  }

  // Test that a new graphic only has spaces and is right size
  @Test
  public void graphicInitialisedCorrectly() {
    for (int n = 0; n < 10; ++n) {
      Page page = new Page(n);

      String gridText = page.toString();
      assertEquals(
          "Grid string size should be length squared + length (for newlines)",
          n * (n + 1),
          gridText.length());
      assertTrue(
          "Grid should only consist of whitespace characters", matchChars(gridText, ' ', '\n'));
    }
  }

  // Test that the image doesn't change when the pen is up
  @Test
  public void onlyDrawWhenPenDown() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle tom = new Turtle(page, pen);

    tom.move(Direction.SOUTH, 3);
    assertTrue(
        "No drawing should happen when the pen is up", matchChars(page.toString(), ' ', '\n'));
    tom.penDown();
    tom.move(Direction.EAST, 3);
    int numDots = countChars(page.toString(), '.');
    assertTrue(
        "Moving three spaces should draw at most four dots and at least three",
        numDots <= 4 && numDots >= 3);
    tom.penUp();
    tom.move(Direction.NORTH, 3);
    int numDots2 = countChars(page.toString(), '.');
    assertEquals(
        String.format(
            "After lifting the pen again, no more drawing should happen (%s dots drawn before)",
            numDots),
        numDots,
        numDots2);
  }

  @Test
  public void penCanChangePoint() {
    Page page = new Page(10);
    Pen pen = new Pen('.');

    Turtle tom = new Turtle(page, pen);
    tom.penDown();

    tom.move(Direction.SOUTH, 3);
    tom.setPen(new Pen('x'));
    tom.penUp();
    tom.move(Direction.EAST, 2);
    tom.penDown();
    tom.move(Direction.NORTH, 3);
    int numDots = countChars(page.toString(), '.');
    long numCrosses = countChars(page.toString(), 'x');
    assertTrue(
        "Drawing two lines with different pens should result in more pen stoke types",
        numDots > 0 && numCrosses > 0);
  }

  // return a count of how many chr in str
  private int countChars(String str, char chr) {
    int result = 0;
    for (int i = 0; i < str.length() - 1; i++) {
      char c = str.charAt(i);
      if (c == chr) {
        result++;
      }
    }
    return result;
  }

  // returns true if string contains only c1 or c2
  private boolean matchChars(String str, char c1, char c2) {
    boolean result = true;
    for (int i = 0; i < str.length() - 1; i++) {
      char c = str.charAt(i);
      result = result && (c == c1 || c == c2);
    }
    return result;
  }
}
