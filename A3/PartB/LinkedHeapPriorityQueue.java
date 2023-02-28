/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: LinkedHeapPriorityQueue
 ********************************************************/

import java.util.Comparator;
import java.util.Stack;

public class LinkedHeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

  LinkedBinaryTree<Entry<K,V>> heap = new LinkedBinaryTree<>();

  //* natural ordering constructor
  public LinkedHeapPriorityQueue(){ super(); }
  //* with specified ordering constructor
  public LinkedHeapPriorityQueue(Comparator<K> comp){ super(comp); }

  //* heap priority queue is a array-list based data structure, which we access to a parent or child of specific element by pass the index to the function (e.g. parent(i) = ((i-1)/2) ).

  //* by introducing LinkedBinaryTree, we have the access to a parent of child of certain element by simply call the methods(e.g. tree.parent(Position)). Note, we need to use Position instead of index.

  //* therefor, the 5 protected utility methods are not needed.

  protected void swap(Position<Entry<K,V>> p1, Position<Entry<K,V>> p2){
    Entry<K,V> temp = p1.getElement();
    heap.set(p1, p2.getElement());
    heap.set(p2, temp);
  }

  protected void upheap(Position<Entry<K,V>> p){
    while(p != heap.root()){
      Position<Entry<K,V>> parent = heap.parent(p);
      if( compare(p.getElement(),parent.getElement()) >=0 )break;
      swap(p, parent);
      p = parent;
    }
  }

  protected void downheap(Position<Entry<K,V>> p){
    while(heap.left(p) != null){
      Position<Entry<K,V>> leftChild = heap.left(p);
      Position<Entry<K,V>> smallChild = leftChild;
      if(heap.right(p) != null){
        Position<Entry<K,V>> rightChild = heap.right(p);
        if(compare(leftChild.getElement(), rightChild.getElement()) >0 ){
          smallChild = rightChild; // if right is smaller
        }
      }
      if(compare(smallChild.getElement(), p.getElement()) >= 0)break;// break if children is greater than p
      swap(p, smallChild);
      p = smallChild;
    }
  }

  public int size() { return heap.size(); }

  private Position<Entry<K, V>> path(int x){
    Stack<Integer> stack = new Stack<>();
    while(x >1){
      stack.push(x%2);
      x = x/2;
    }// heap.size = 1 - 3, this will not run

    Position<Entry<K, V>> walk = heap.root();
    while(!stack.empty()){//empty when size = 1 - 3
      if(stack.pop() == 0){
        walk = heap.left(walk);
      }
      else{
        walk = heap.right(walk);
      }
    }
    return walk;
  }

  public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);//throw exception for invalid key
    Entry<K,V> newest = new PQEntry<>(key, value);
    if(heap.isEmpty()){
      heap.addRoot(newest);
    }
    else{
      Position<Entry<K, V>> nextAvailable = path((heap.size()+1)/2);
      if(heap.left(nextAvailable) == null){
        upheap(heap.addLeft(nextAvailable, newest));
        //the add method returns the position of added element
      }
      else{
        upheap(heap.addRight(nextAvailable, newest));
        //the add method returns the position of added element
      }
    }
    return newest;
  }

 
  public Entry<K, V> min() {
    if(heap.isEmpty()){ return null; }
    return heap.root().getElement();//root has the mallest key
  }


  public Entry<K, V> removeMin() {
    if(heap.isEmpty()){ return null; }
    Position<Entry<K, V>> lastPosition = path(heap.size());
    Position<Entry<K, V>> min = heap.root();
    swap(lastPosition, min);//swap the root with the min position
    heap.remove(lastPosition);
    downheap(heap.root());//since the original last element placed at root, need to down heap
    return lastPosition.getElement();
  }

  public String toString(){
    if(isEmpty()){return "Empty Tree";}
    StringBuilder tree = new StringBuilder();
    tree.append("Tree\n"+"-----\n");
    //! position() method has been overriden as "inorder", call preorder to run specific traversal method
    int currentRow = -1;
    int rowSpacing = heap.height(heap.root());
    System.out.println(rowSpacing);
    for(Position<Entry<K, V>> p:heap.breadthfirst()){
      if(currentRow != heap.depth(p)){
        String space = "";
        for(int s = 0; s < rowSpacing; s++){
          space += "    ";
        }
        tree.append("\n"+space);
        currentRow++;
        rowSpacing--;
      }
      tree.append(" "+p.getElement());
    }
    return tree.toString();
  }
}
