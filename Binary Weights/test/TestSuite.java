import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestSuite {

  @Test
  public void canBeConstructedFromString() {
    assertEquals(1, new BinaryWeight("1").asDecimal());
    assertEquals(2, new BinaryWeight("10").asDecimal());
    assertEquals(0, new BinaryWeight("ABCD").asDecimal());
    assertEquals(0, new BinaryWeight(" ").asDecimal());
    assertEquals(0, new BinaryWeight("").asDecimal());
  }

  @Test
  public void canBeConstructedFromInteger() {
    assertEquals(1, new BinaryWeight(1).asDecimal());
    assertEquals(2, new BinaryWeight(2).asDecimal());
    assertEquals(0, new BinaryWeight(-1).asDecimal());
  }

  @Test
  public void canBeRepresentedAsBinaryString() {
    assertEquals("1", new BinaryWeight(1).asString());
    assertEquals("10", new BinaryWeight(2).asString());
    assertEquals("1111", new BinaryWeight(15).asString());
    assertEquals("0", new BinaryWeight("A").asString());
    assertEquals("0", new BinaryWeight(-1).asString());
  }

  @Test
  public void illformedWeightsShouldBeZero() {
    checkCreatingFromInvalidString("a", "Cannot contain letters");
    checkCreatingFromInvalidString("012", "Cannot contain non-binary digits");
    checkCreatingFromInvalidString("-1", "Cannot contain special characters");
  }

  private void checkCreatingFromInvalidString(String x, String reason) {
    assertEquals(
        String.format("Malformed \"%s\" should be rejected: %s", x, reason),
        "0",
        new BinaryWeight(x).asString());
  }

  @Test
  public void negativeDecimalShouldBeZero() {
    checkNegativeValueIsRejected(-1);
    checkNegativeValueIsRejected(-13);
  }

  private void checkNegativeValueIsRejected(Integer x) {
    assertEquals(
        String.format("%s should be rejected: Cannot be negative", x),
        "0",
        new BinaryWeight(x).asString());
  }

  @Test
  public void decrementingCannotGoNegative() {
    BinaryWeight w = new BinaryWeight("0");
    assertEquals(0, w.asDecimal());
    w.decrement();
    assertEquals("Negative weights are not allowed", 0, w.asDecimal());
  }

  @Test
  public void roundTripWorksCorrectly() {
    for (int i = 0; i < 100; ++i) {
      BinaryWeight w = new BinaryWeight(i);
      BinaryWeight u = new BinaryWeight(w.asString());
      BinaryWeight v = new BinaryWeight(u.asDecimal());
      assertEquals(
          "Values converted from decimal and then back again should be equal", i, u.asDecimal());
      assertEquals(
          "Values converted from binary and then back again should be equal",
          w.asString(),
          v.asString());
    }
  }

  @Test
  public void addedWeightsAreUnchangedAndResultCorrect() {
    BinaryWeight w = new BinaryWeight(10);
    BinaryWeight u = new BinaryWeight(42);
    BinaryWeight v = w.plus(u);

    assertEquals("Left operand should be unchanged after addition", 10, w.asDecimal());
    assertEquals("Right operand should be unchanged after addition", 42, u.asDecimal());
    assertEquals("10 + 42 = 52", w.asDecimal() + u.asDecimal(), v.asDecimal());
  }
}
