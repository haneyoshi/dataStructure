/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: AbstractBinaryTree
 ********************************************************/

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

  public Position<E> sibling(Position<E> p){
    Position<E> parent  = parent(p);
    if(parent == null) return null;// p must be the root
    if( p == left(parent)) //reft(position) return the left child of the position
      return right(parent);
    else
      return left(parent);
  }

  public int numChildren(Position<E> p){
    int count  = 0;
    if(left(p) != null){ count++; }
    if(right(p) != null){ count++; }
    return count;
  }

  public Iterable<Position<E>> children(Position<E> p){
    List<Position<E>> snapshot = new ArrayList<>(2);
    if(left(p) != null){ snapshot.add(left(p)); }
    if(right(p) != null){ snapshot.add(right(p)); }
    return snapshot;
  }

  //todo: --------------  Preoder Traversals  -------------------------
  private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
    if(left(p) != null){ inorderSubtree(left(p), snapshot); };
    snapshot.add(p);//* from left to right
    if(right(p) != null){ inorderSubtree(right(p), snapshot); };
  }
  public Iterable<Position<E>> inorder(){
    List<Position<E>> snapshot = new ArrayList<>();
    if(!isEmpty()){
      inorderSubtree(root(), snapshot);
    }
    return snapshot;
  }

  //todo: --------------  positions()  -------------------------
  //* since iterator() relies on position(), here is to overrive the position() from parent(AbstractTree class)
  @Override
  public Iterable<Position<E>> positions(){ return inorder(); }
}
