public class NodeClient {

  //my user id is: ab123
  //this code took me x hours to complete

  public static void main(String[] args) {
    Node slash = new Node("/", false);
    Node etc = new Node("etc", false);

    slash.add(etc);

    System.out.println(slash.lsAll());

    System.out.println("******************");

    System.out.println(slash.asGraphViz());

    System.out.println("******************");

    System.out.println(slash.countAll());
  }
}
