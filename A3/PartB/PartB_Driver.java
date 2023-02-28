/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: PartB_Driver
 ********************************************************/

public class PartB_Driver {
  public static void main (String[]args){
    System.out.println("\n\n\t***** A3 Part B *****\n");
    //todo: ----------------     todo: build the tree and display     -----------------
    LinkedHeapPriorityQueue<Integer, String> tree = new LinkedHeapPriorityQueue<>();
    //* start feeding data
    tree.insert(1, "A");
    tree.insert(3, "C");
    tree.insert(5, "E");
    tree.insert(7, "G");
    tree.insert(9, "I");
    tree.insert(11, "K");
    tree.insert(13, "M");
    tree.insert(15, "O");
    tree.insert(17, "Q");
    tree.insert(19, "S");
    tree.insert(21, "U");
    tree.insert(23, "W");
    tree.insert(25, "Y");

    System.out.println(tree+"\n");
    

    //todo: ----------------     todo: test implemented methods     -----------------
    System.out.println("\n\n** test implemented methods:");
    System.out.println("Min: "+ tree.min());
    System.out.println("RemiveMin: "+ tree.removeMin());
    System.out.println(tree+"\n");

    System.out.println("\n\n** insert some data");
    tree.insert(12, "I");
    tree.insert(26, "Z");
  System.out.println(tree+"\n");


    System.out.println("\n\t***** End of Part B *****\n");
  }
}
