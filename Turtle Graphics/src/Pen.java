public class Pen {

  private char stroke;

  public Pen(char stroke) {
    assert (stroke != ' ' && stroke != '\n' && stroke != '\t');
    this.stroke = stroke;
  }

  public char getStroke() {
    return this.stroke;
  }

}
