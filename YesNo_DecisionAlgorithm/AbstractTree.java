
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E>{
  //todo: some query methods
  public boolean isInternal(Position<E> p){ return numChildren(p)>0; }
  public boolean isExternal(Position<E> p){ return numChildren(p) == 0; }
  public boolean isRoot(Position<E> p){ return p == root(); }
  public boolean isEmpty(){ return size() == 0; }

  //todo: depth and height
  public int depth(Position<E> p){
    if(isRoot(p)) return 0;
    return 1 + depth(parent(p));
  }

  public int height(Position<E> p){
    int h = 0;
    for(Position<E> c: children(p)){
      h = Math.max(h, 1+ height(c));
    }
    return h;
  }

  //todo: --------------  Preoder Traversals  -------------------------
  //* definition: root -> children
  public Iterable<Position<E>> preorder(){
    List<Position<E>> snapshot = new ArrayList<>();
    if(!isEmpty()){
      preoderSubtree(root(), snapshot);
    }
    return snapshot;
  }
  
  /**
   * Recursive process with a specidic position of the tree that serves as the root of a subtree to traverse
   * @param p Position<E> parameter
   * @param snapshot List<Position<E>> ArrayList
   */
  private void preoderSubtree(Position<E> p, List<Position<E>> snapshot){
    snapshot.add(p);
    for(Position<E> c: children(p)){
      preoderSubtree(c, snapshot);
    }
  }//-------------------------------------- end of Preoder Traversals

  //todo: --------------  Postoder Traversals  -------------------------
  //* definition: children(sibling to sibling) -> root(parent)
  public Iterable<Position<E>> postorder(){
    List<Position<E>> snapshot = new ArrayList<>();
    if(!isEmpty()){
      postorderSubtree(root(), snapshot);
    }
    return snapshot;
  }
  
  /**
   * Recursive process with a specidic position of the tree that serves as an End-Point where we stretch out to the end of it's branch(children) and add children to the list alone the way back.
   * @param p Position<E> parameter
   * @param snapshot List<Position<E>> ArrayList
   */
  private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
    for(Position<E> c: children(p)){
      postorderSubtree(c, snapshot);
    }
    snapshot.add(p);//! for postorder, we add position p after exploring subtrees
  }//-------------------------------------- end of Postoder Traversals

  //todo: --------------  Breadthfirst Traversals  -------------------------
  /**
   * !Not recursive approch
   * Row by row, take a position one at a time by using LinkedQueue(first in, first out) data structure
   */
  public Iterable<Position<E>> breadthfirst(){
    List<Position<E>> snapshot = new ArrayList<>();
    if(!isEmpty()){
      Queue<Position<E>> fringe = new LinkedQueue<>();
      fringe.equeue(root());
      while(!fringe.isEmpty()){
        Position<E> p = fringe.dequeue();
        snapshot.add(p);
        for(Position<E> c: children(p)){
          fringe.equeue(c);
        }
      }
    }
    return snapshot;
  }//-------------------------------------- end of Breadthfirst Traversals

  //todo: --------------  positions ot return Iterable  -------------------
  //* for(Position<E> p: xxx.positions()) => to be legal, return type must be Iterable
  public Iterable<Position<E>> positions(){ return preorder(); }

  //todo: --------------  Nested ElementIterator class  -------------------
  private class ElementIterator implements Iterator<E>{
    //! note, there is no PositionIterator() which is linkedlist data structure. We can just call positions to create a iterator.
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext(){ return posIterator.hasNext(); }
    public E next(){ return posIterator.next().getElement(); }
    public void remove(){ posIterator.remove(); } //??? not defined
  }

  public Iterator<E> iterator(){return new ElementIterator();}
}
