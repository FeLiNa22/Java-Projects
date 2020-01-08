public class BinaryWeight {

  // My username is rp519 and I spent 10 hours completing this lab

  private String weight;

  public BinaryWeight(String s) {
    if (this.isBinaryString(s)){
      this.weight = s;
    }else{
      this.weight = "0";
    }
  }

  public BinaryWeight(int n) {
    if (n >= 0){
      this.weight = this.toBinary(n);
    }else{
      this.weight = "0";
    }
  }

  private boolean isBinaryString(String s) {
    for (int i = 0;i<s.length();i++){
      char charVal = s.charAt(i);
      if (charVal != '0' && charVal != '1'){
        return false;
      }
    }
    return true;
  }

  public String asString() {
    return this.weight;
  }

  public String toBinary(int n) {
    String bin = "";
    while (n != 0){
      bin = (n % 2) + bin;
      n   = n / 2; 
    }
    return bin;
  }

  public int asDecimal() {
    return toInt(this.weight);
  }

  public String toString() {
    String output = "The weights that you need to represent (" + this.asDecimal() + ") are : ";
    for (int i = 0; i < this.weight.length(); i++){
      int power = this.weight.length() - 1 - i;
      if (this.weight.charAt(i) == '1'){
        int units = (int) Math.pow(2, power);
        output += "\n" + ("1 x " + units + " Unit");
      }
    }
    return output;
  }

  private int toInt(String s) {
    int dec = 0;
    for (int i = 0; i < s.length(); i++){
      int power =  s.length() - 1 - i;
      if (s.charAt(i) == '1'){
        dec += Math.pow(2, power);
      }
    }
    return dec;
  }

  public void increment() {
    this.weight = binaryAdd(this.weight, "1");
  }

  public void decrement() {
    if (Integer.parseInt(this.weight) > 0) {
      this.weight = binarySub(this.weight, "1");
    }else{
      this.weight = "0";
    }
  }

  public BinaryWeight plus(BinaryWeight weight) {
    return new BinaryWeight( binaryAdd(this.weight, weight.asString()) );
  }

  // EXTENSION WORK
  private String binaryAdd(String first, String second){
    int b1 = Integer.parseInt(first, 2);
    int b2 = Integer.parseInt(second, 2);
    int sum = b1 + b2;
    return Integer.toBinaryString(sum);
  }

  private String binarySub(String first, String second){
    int b1 = Integer.parseInt(first, 2);
    int b2 = Integer.parseInt(second, 2);
    int sum = b1 - b2;
    return Integer.toBinaryString(sum);
  }

  public int compareTo(BinaryWeight otherValue) {
    if (this.asDecimal() > otherValue.asDecimal()){
      return 1;
    }else if (this.asDecimal() < otherValue.asDecimal()){
      return -1;
    } else {
      return 0;
    }
  }
}
