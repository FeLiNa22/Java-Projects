public class Turtle {
  private Page page;
  private Pen pen;
  private Boolean penDown;

  public Turtle(Page page, Pen pen) {
    this.page = page;
    this.pen  = pen;
    this.penDown = false;
  }

  public void setPen(Pen pen) {
    this.pen = pen;
  }

  public void penUp() {
    this.penDown = false;
  }

  public void penDown() {
    this.penDown = true;
  }

  public boolean isPenDown() {
    return this.penDown;
  }

  public void move(Direction direction, int distance) {
    assert(distance >= 0);
    switch(direction) {
      case NORTH:
        this.move(0, -1, distance);
        break;
      case EAST:
        this.move(1, 0, distance);
        break;
      case SOUTH:
        this.move(0, 1, distance);
        break;
      case WEST:
        this.move(-1, 0, distance);
        break;
      case NORTHWEST:
        this.move(-1, -1, distance);
        break;
      case SOUTHWEST:
      this.move(-1, 1, distance);
      break;
      case NORTHEAST:
        this.move(1, -1, distance);
        break;
      case SOUTHEAST:
        this.move(1, 1, distance);
        break;
    }
  }

  private void makeMark() {
    if (isPenDown()) {
      this.page.draw(this.page.x, this.page.y, this.pen);
    }
  }

  private void move(int deltaX, int deltaY, int distance) {
    for (int i = 0; i < distance; i++) {
      makeMark();
      int new_x = this.page.x + deltaX;
      int new_y = this.page.y + deltaY;
      this.page.x = (new_x >= this.page.size() || new_x <= 0) ? this.page.x: new_x;
      this.page.y = (new_y >= this.page.size() || new_y <= 0) ? this.page.y: new_y;
      makeMark();
    }

  }
}
