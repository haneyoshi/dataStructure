
public class Driver {
  public static void main (String[]args){
    System.out.println("\n\n\t***** Program Start *****\n");
    //todo: ----------------     todo: build the tree and display     -----------------
    LinkedHeapPriorityQueue<Integer, String> tree = new LinkedHeapPriorityQueue<>();
    //* start feeding data
    tree.insert(1, "cutstomerA");
    tree.insert(3, "cutstomerC");
    tree.insert(5, "cutstomerE");
    tree.insert(7, "cutstomerG");
    tree.insert(9, "cutstomerI");
    tree.insert(11, "cutstomerK");
    tree.insert(13, "cutstomerM");
    tree.insert(15, "cutstomerO");
    tree.insert(17, "cutstomerQ");
    tree.insert(19, "cutstomerS");
    tree.insert(21, "cutstomerU");
    tree.insert(23, "cutstomerW");
    tree.insert(25, "cutstomerY");

    System.out.println(tree+"\n");
    

    //todo: ----------------     todo: test implemented methods     -----------------
    System.out.println("\n\n** test implemented methods:");
    System.out.println("Min: "+ tree.min());
    System.out.println("RemiveMin: "+ tree.removeMin());
    System.out.println(tree+"\n");

    System.out.println("\n\n** insert some data");
    tree.insert(12, "cutstomerI");
    tree.insert(26, "cutstomerZ");
  System.out.println(tree+"\n");


    System.out.println("\n\t***** End *****\n");
  }
}
