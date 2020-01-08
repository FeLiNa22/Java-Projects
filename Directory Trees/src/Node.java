import java.util.ArrayList;
import java.util.List;

public class Node {

  private String name;
  private boolean isFile;
  private List<Node> subNodes;
  // my user id is: ab123
  // this code took me x hours to complete

  // 1. name and isDirectory fields, constructor

  public Node(String name, boolean isFile) {
      this.setName(name);
      this.isFile = isFile;
      this.subNodes = new ArrayList<Node>();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if (name == "" || name == null ){
      throw new IllegalArgumentException("Node name must not be empty or null");
    }else if(name.contains("\t") || name.contains("\n")){
      throw new IllegalArgumentException("Node name must not contain whitespace");
    }else {
      this.name = name;
    }
  }

  public boolean isFile() {
    return this.isFile;
  }

  public void setDirectory() {
    this.isFile = false;
  }

  public void setFile() {
    if(this.subNodes.isEmpty()) {
      this.isFile = true;
    }else{
      throw new IllegalArgumentException("Directory with files cannot be changed to a file");
    }
  }

  // 2. Handling Inconsistent State


  // 3. toString

  public String toString() {
    if(this.isFile()){
      return this.getName();
    }else{
      return (this.getName() + "/");
    }
  }

  // 4. Recursive Structure using ArrayList<Node>

  public void add(Node node) {
    if (node == null || this.isFile){
      throw new IllegalArgumentException("File cannot have a subfile");
    }else{
      this.subNodes.add(node.clone());
    }
  }

  // 5. Handling More Inconsistent State





  // 6. Rename all to uppercase: toUpperCaseAll()

  private void toUpperCase() {
    this.setName(this.name.toUpperCase());
  }

  public void toUpperCaseAll() {
    this.toUpperCase();
    for(Node node : this.subNodes){
     node.toUpperCaseAll();
    }
  }

  // 7. countAll()

  public int countAll() {
    int result = 1;
    for(Node node : this.subNodes){
      result += node.countAll();
    }
    return result;
  }

  // 8. lsAll

  public String lsAll() {
    String output = this.toString() + "\n";
    for (Node node : this.subNodes) {
        output += "  " + node.toString() + "\n";
    }
    output += "\n";
    for (Node node : this.subNodes) {
      if (!node.isFile()) {
        output += " " + node.lsAll();
      }
    }
    return  output;

  }

  //9. clone
  public Node clone() {
    Node new_root = new Node(this.name, this.isFile);
    for(Node node : this.subNodes){
      new_root.add(node.clone());
    }
    return new_root;
  }

  // 10. asGraphViz() method
  public String asGraphViz() {
    StringBuffer output =  new StringBuffer("digraph G { \n");
    output.append(this.graphVizHelper());
    output.append("}");
    return output.toString();
  }

  private String  graphVizHelper(){
    String output = "";
    for(Node node : this.subNodes){
      output += "\"" + this.getName() + "\" -> " + node.getName() + " ;\n";
    }
    for(Node node : this.subNodes){
      if (!node.isFile()) {
        output += node.graphVizHelper();
      }
    }
    return output;
  }


}
