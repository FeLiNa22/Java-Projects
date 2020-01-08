public class TurtleClient {

  static Pen penX = new Pen('x');
  static Pen penDash = new Pen('-');
  static Pen penPipe = new Pen('|');
  static Pen penHash = new Pen('#');

  public static void main(String[] args) {
    Page page = new Page(10);
    Turtle turtle1 = new Turtle(page, penX);
    Turtle turtle2 = new Turtle(page, penHash);

    turtle1.move(Direction.EAST, 2);

    turtle1.setPen(penPipe);
    turtle1.move(Direction.SOUTH, 1);

    print(page);

    turtle1.penUp();
    turtle1.move(Direction.SOUTH, 2);
    turtle1.move(Direction.WEST, 4);

    print(page);

    turtle1.setPen(penDash);
    turtle1.move(Direction.SOUTH, 1);
    turtle1.penDown();
    turtle1.move(Direction.EAST, 4);

    turtle2.penUp();
    turtle2.move(Direction.SOUTH, 2);
    turtle2.penDown();
    turtle2.move(Direction.EAST, 5);

    print(page);

    int size = 10;
  // create a turtle pen
    Pen pen1 = new Pen ('X');
// create a turtle graphic
    Page page11 = new Page ( size );
// create a turtle
    Turtle tom1 = new Turtle ( page11 , pen1 );
    tom1.penDown();
    for ( int i =0; i < size ; i +=4) {
      tom1.move(Direction.EAST , 10 - i) ;
      tom1.move(Direction.SOUTH , 10 - i - 1) ;
      tom1.move(Direction.WEST , 10 - i - 2) ;
      tom1.move(Direction.NORTH , 10 - i - 3) ;
    }
    print(page11);

  }

  private static void print(Page page) {
    System.out.println(page);
    System.out.println("************************************************************* \n");
  }
}
