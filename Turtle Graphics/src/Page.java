import java.util.Arrays;

public class Page {

  private char[][] grid;
  public int x;
  public int y;

  public Page(int size) {
    assert (size >= 0);
    this.grid = new char[size][size];
    this.clear();
    this.x = 0;
    this.y = 0;
}

  private void clear() {
    for (int y = 0; y < this.grid.length; y++){
      for (int x = 0; x < this.grid.length; x++) {
        this.grid[y][x] = ' ';
      }
    }
  }

  public int size() {
    return this.grid.length;
  }

  public void draw(int x, int y, Pen pen) {
    this.grid[y][x] = pen.getStroke();
  }

  public String toString() {
    String output = "";
    for (char row[] : this.grid){
      for (char elem :  row){
          output += elem;
      }
      output += "\n";
    }
    return output;
  }
}
