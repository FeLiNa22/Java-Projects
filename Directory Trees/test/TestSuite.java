import org.junit.Test;

import static org.junit.Assert.*;

public class TestSuite {

// part 1 
  
  // Test that the nodes fields are set by the constructor 
  @Test
  public void nodeInit() {
    var name = "example";
    var node = new Node(name, false);

    assertEquals("name should be set in constructor (directory)",
        name, node.getName());

    assertEquals("isFile should be set in constructor (directory)",
        false, node.isFile());

    name = "example2";
    node = new Node(name, true);

    assertEquals("name should be set in constructor (file)",
        name, node.getName());

    assertEquals("isFile should be set in constructor (file)",
        true, node.isFile());
  }

  // Test that the nodes fields are set by mutators
  @Test
  public void nodeMutatorTest() {
    var name = "example";
    var isFile = false;
    var node = new Node(name, isFile);

    name = "example2";

    node.setName(name);
    node.setFile();

    assertEquals("name should be set in setName()",
        name, node.getName());

    assertTrue("isFile should be set true after setFile()",
        node.isFile());

    node.setDirectory();

    assertFalse("isFile should be false after setDirectory()",
        node.isFile());
  }

//part 2

  //constructor tests
  // these test cases could be combined nicely with JUnit 5 Assertions.assertThrows

  // a file name cannot be empty - Constructor
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameEmptyConstructor() {
    String illegalName = "";
    new Node(illegalName,true);
  }

  // a file name cannot be null - Constructor
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameNullConstructor() {
    String illegalName = null;
    new Node(illegalName, true);
  }

  // a directory name cannot be empty  - Constructor
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameEmptyConstructor() {
    String illegalName = "";
    new Node(illegalName, false);
  }

  // a directory name cannot be null  - Constructor
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameNullConstructor() {
    String illegalName = null;
    new Node(illegalName, false);
  }

  // a file name cannot have new lines
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameNewLineConstructor() {
    String illegalName = "example\n";
    new Node(illegalName, true);
  }

  // a file name cannot have tabs
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameTabConstructor() {
    String illegalName = "example\t";
    new Node(illegalName, true);
  }

  // a directory name cannot have new lines
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameNewLineConstructor() {
    String illegalName = "example\n";
    new Node(illegalName, false);
  }

  // a directory name cannot have tabs
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameTabConstructor() {
    String illegalName = "example\t";
    new Node(illegalName, false);
  }

  //mutator tests

  // a file name cannot have new lines
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameNewLineSet() {
    String illegalName = "example\n";
    new Node("example", true).setName(illegalName);
  }

  // a file name cannot have tabs
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameTabSet() {
    String illegalName = "example\t";
    new Node("example", true).setName(illegalName);
  }

  // a file name cannot be empty
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameEmptySet() {
    String illegalName = "";
    new Node("example", true).setName(illegalName);
  }

  // a file name cannot be null
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileNameNullSet() {
    String illegalName = null;
    new Node("example", true).setName(illegalName);
  }

  // a directory name cannot have new lines
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameNewLineSet() {
    String illegalName = "example\n";
    new Node("example", false).setName(illegalName);
  }

  // a directory name cannot have tabs
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameTabSet() {
    String illegalName = "example\t";
    new Node("example", false).setName(illegalName);
  }

  // a directory name cannot be empty
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameEmptySet() {
    String illegalName = "";
    new Node("example", false).setName(illegalName);
  }

  // a directory name cannot be null
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirectoryNameNullSet() {
    String illegalName = null;
    new Node("example", false).setName(illegalName);
  }

//part 3

  // Test that toString() for a directory name is followed by a /
  @Test
  public void nodeDirectoryToString() {
    var name = "example";
    var isFile = false;
    var nameExpectedtoString = "example/";
    var node = new Node(name, isFile);

    assertEquals("directory name should be followed by /",
        nameExpectedtoString, node.toString());
    assertEquals("getNodeName() should just be the node name",
        name, node.getName());

    node = new Node(name, true);
    node.setDirectory();

    assertEquals("directory name should be followed by /",
        nameExpectedtoString, node.toString());
    assertEquals("getNodeName() should just be the node name",
        name, node.getName());
  }

  // Test that toString() for a file name is just file name
  @Test
  public void nodeFileToString() {
    var name = "example";
    var isFile = true;
    var nameExpected = "example";
    var node = new Node(name, isFile);
    assertEquals("file name should just be the specified name (toString)",
        nameExpected, node.toString());
    assertEquals("file name should just be the specified name (getNodeName)",
        nameExpected, node.getName());

    node = new Node(name, false);
    node.setFile();
    assertEquals("file name should just be the specified name (toString)",
        nameExpected, node.toString());
    assertEquals("file name should just be the specified name (getNodeName)",
        nameExpected, node.getName());
  }  
//part 4 Recursive Structure with ArrayList<Node>
//part 5 Handling More Inconsistent States

  // Test that a directory cannot have a sub-file, should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileCannotAddNull() {
    var name = "example";
    var dir = new Node(name,false);

    //should trigger IAE - no files with null
    dir.add(null);
  }

  // Test that a file cannot have a sub-file, should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void nodeFileCannotAddSubFile() {
    var name = "example";
    var file = new Node(name,true);
    var sFile = new Node(name, true);

    //should trigger IAE - no files with sub files
    file.add(sFile);
  }


  // Test that a file cannot become a directory if it has a sub-files, should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void nodeDirWithSubFilesCannotMutate() {
    var name = "example";
    var nodeSubOne = new Node(name, true);
    var nodeSubTwo = new Node(name, true);

    Node node = new Node(name, false);
    node.add(nodeSubOne);
    node.add(nodeSubTwo);

    //should trigger IAE - directory with sub files cannot change to a file
    node.setFile();
  }

  //6 Rename all to uppercase: uppercaseAll()

  // Test that UppercaseAll returns the expected value
  @Test
  public void nodeUppercaseAllTest() {

    String nameSeven = "lectures.txt";
    String nameSix = "shah";
    String nameNine = "passwd";
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    Node three = new Node("bin", false);
    Node four = new Node("lib", false);

    Node five = new Node("home", false);
    Node six = new Node(nameSix, false);
    Node seven = new Node(nameSeven, true);
    Node eight = new Node("hosts", true);
    Node nine = new Node(nameNine, true);

    six.add(seven);
    five.add(six);
    two.add(eight);
    two.add(nine);
    one.add(two);
    one.add(five);
    one.add(three);
    one.add(four);

    seven.toUpperCaseAll();

    assertTrue("calling toUpperCase on a file makes that file name uppercase",
        seven.getName().equals(nameSeven.toUpperCase()));

    six.toUpperCaseAll();

    assertTrue("calling toUpperCase on a directory makes that directory name uppercase",
        six.getName().equals(nameSix.toUpperCase()));

    //n.b. does not test recursion

  }

  // Test that countAll returns the expected value
  @Test
  public void nodeCountAllTest() {
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    Node three = new Node("bin", false);
    Node four = new Node("lib", false);

    Node five = new Node("home", false);
    Node six = new Node("shah", false);
    Node seven = new Node("lectures.txt", true);
    Node eight = new Node("hosts", true);
    Node nine = new Node("passwd", true);

    six.add(seven);
    five.add(six);
    two.add(eight);
    two.add(nine);
    one.add(two);
    one.add(five);
    one.add(three);
    one.add(four);

    assertEquals("the number of nodes given a tree with nine entries should be 9",
        9, one.countAll());

    assertEquals("the number of nodes given a tree with one entry should be 1",
        1, seven.countAll());
  }



  // Test that lsAll returns the expected values
  @Test
  public void nodeLsAllTest() {
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    Node three = new Node("bin", false);
    Node four = new Node("lib", false);

    Node five = new Node("home", false);
    Node six = new Node("shah", false);
    Node seven = new Node("lectures.txt", true);
    Node eight = new Node("hosts", true);
    Node nine = new Node("passwd", true);

    six.add(seven);
    five.add(six);
    two.add(eight);
    two.add(nine);
    one.add(two);
    one.add(five);
    one.add(three);
    one.add(four);
    assertTrue("lsAll() on a node should contain at least the name of that node",
        one.lsAll().contains(one.getName()));

    assertTrue("lsAll() on a file should contain at least the name of that file",
        seven.lsAll().contains(seven.getName()));

    assertTrue("lsAll() on a node should contain at least the name of a sub file",
        one.lsAll().contains(seven.getName()));
  }

  // Test that UppercaseAll is recursive, using lsAll
  @Test
  public void nodeUppercaseAllRecursiveTest() {

    String nameSeven = "lectures.txt";
    String nameSix = "shah";
    String nameNine = "passwd";
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    Node three = new Node("bin", false);
    Node four = new Node("lib", false);

    Node five = new Node("home", false);
    Node six = new Node(nameSix, false);
    Node seven = new Node(nameSeven, true);
    Node eight = new Node("hosts", true);
    Node nine = new Node(nameNine, true);

    six.add(seven);
    five.add(six);
    two.add(eight);
    two.add(nine);
    one.add(two);
    one.add(five);
    one.add(three);
    one.add(four);

    one.toUpperCaseAll();
    var oneUpper = one.lsAll();

    assertTrue("calling toUpperCase on a root makes a sub file name uppercase (recursive)",
        oneUpper.contains(nameSeven.toUpperCase()));

    six.toUpperCaseAll();

    assertTrue("calling toUpperCase on a root makes a sub directory name uppercase (recursive)",
        oneUpper.contains(nameSix.toUpperCase()));

    //n.b. does test recursion, requires lsAll()
  }

  // Test that the node structure remains a tree
  @Test
  public void nodeIsTreeTest() {
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    one.add(two);
    two.add(two);

    try{
      one.lsAll();
    }
    catch(StackOverflowError e){
      fail("lsAll() recursive call on a node should terminate");
    }
  }


  // Test that graphViz returns the expected values
  @Test
  public void nodeGVTest() {
    Node one = new Node("/", false);
    Node two = new Node("etc", false);

    Node three = new Node("bin", false);
    Node four = new Node("lib", false);

    Node five = new Node("home", false);
    Node six = new Node("shah", false);
    Node seven = new Node("lectures.txt", true);
    Node eight = new Node("hosts", true);
    Node nine = new Node("passwd", true);

    six.add(seven);
    five.add(six);
    two.add(eight);
    two.add(nine);
    one.add(two);
    one.add(five);
    one.add(three);
    one.add(four);
    assertTrue("asGraphViz() on a node should contain at least digraph",
        one.asGraphViz().contains("digraph"));

    assertTrue("asGraphViz() on a node should contain at least new lines",
        one.asGraphViz().contains("\n"));

    assertTrue("asGraphViz() on a node should contain at least \"",
        one.asGraphViz().contains("\""));

    assertTrue("asGraphViz() on a node should contain at least ;",
        one.asGraphViz().contains(";"));

    assertTrue("asGraphViz() on a node should contain at least ->",
        one.asGraphViz().contains("->"));

    assertTrue("asGraphViz() on a node should contain at least a {",
        one.asGraphViz().contains("{"));

    assertTrue("asGraphViz() on a node should contain at least a }",
        one.asGraphViz().contains("}"));

    assertTrue("asGraphViz() on a node should contain at least the name of the node",
        one.asGraphViz().contains(one.getName()));

    assertTrue("asGraphViz() on a node should contain at least the name of a sub file",
        one.asGraphViz().contains(seven.getName()));

  }

}