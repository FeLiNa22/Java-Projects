public class BinaryWeightClient {

  public static void main(String[] args) {
    // Add your example BinaryWeight objects below

    
    // Uncomment the following lines as you go
    BinaryWeight aWeight = new BinaryWeight("01");
    System.out.println(aWeight);

    BinaryWeight bWeight = new BinaryWeight(22);
    System.out.println(bWeight);

    BinaryWeight three = new BinaryWeight("11");
    System.out.println(three);

    BinaryWeight seven = new BinaryWeight("111");
    System.out.print(seven.asString() + " incremented becomes ");
    seven.increment();
    System.out.print(seven.asString() + "\n");

    BinaryWeight two  = new BinaryWeight("010");
    BinaryWeight four = new BinaryWeight("001");
    BinaryWeight sum  = two.plus(four);
    System.out.println(sum.asString());
  }
}
