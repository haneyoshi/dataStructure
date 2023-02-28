/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: LinkedBinaryTree
 ********************************************************/

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
  
  //todo: ---------------  nested Node class  ------------------
  protected static class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    //* constructor
    public Node(E e, Node<E> above, Node<E> leftChilde, Node<E> rightChild){
      this.element = e;
      this.parent = above;
      this.left = leftChilde;
      this.right = rightChild;
    }

    //* accessor methods
    public E getElement(){ return this.element; }
    public Node<E> getParent(){ return this.parent; }
    public Node<E> getLeft(){ return this.left; }
    public Node<E> getRight(){ return this.right; }
    //* update methods
    public void setElement(E e){ element = e; }
    public void setParent(Node<E> p){ this.parent = p; }
    public void setLeft(Node<E> l){ this.left = l; }
    public void setRight(Node<E> r){ this.right = r; }

    public String toString(){
      return this.getElement().toString();
    }
  }//------------------------------------  end of nested Node class

  //todo: ----------------  factory method pattern  ---------------
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
    return new Node<E>(e, parent, left, right);
  }//------------------------------------  end of factory method

  //todo: ----------------  LinkedBinaryTree  ---------------
  //* instance variables
  protected Node<E> root = null;
  private int size = 0;

  //* constructor
  public LinkedBinaryTree(){}

  //* validate the position
  protected Node<E> validate(Position<E> p)throws IllegalArgumentException{
    if(!(p instanceof Node))throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;
    if(node.getParent() == node)throw new IllegalArgumentException("the position is no longer in the tree");
    return node;
  }


  //* accessor methods
  public int size() { return size; }
  public Position<E> root(){ return root; }

  public Position<E> parent(Position<E> p)throws IllegalArgumentException{
    Node<E> node = validate(p);
    return node.getParent();
  }

  public Position<E> left(Position<E> p)throws IllegalArgumentException{
    Node<E> node = validate(p);
    return node.getLeft();
  }

  public Position<E> right(Position<E> p)throws IllegalArgumentException{
    Node<E> node = validate(p);
    return node.getRight();
  }

  //* update method
  public Position<E> addRoot(E e)throws IllegalStateException{
    if(!isEmpty())throw new IllegalStateException("The tree is not empty");
    //! inherit isEmpty method from super class 
    this.root = createNode(e, null, null, null);
    size = 1;
    return root;
  }

  //* add left child
  public Position<E> addLeft(Position<E> p, E e)throws IllegalArgumentException{
    Node<E> parent = validate(p);
    if(parent.getLeft() != null)throw new IllegalArgumentException("p already has a left child");
    Node<E> child = createNode(e, parent, null, null);
    parent.setLeft(child);
    size++;
    return child;
  }

  //* add right child
  public Position<E> addRight(Position<E> p, E e)throws IllegalArgumentException{
    Node<E> parent = validate(p);
    if(parent.getRight() != null)throw new IllegalArgumentException("p already has a right child");
    Node<E> child = createNode(e, parent, null, null);
    parent.setRight(child);
    size++;
    return child;
  }

  //* replace the element at the position
  public E set(Position<E> p, E e)throws IllegalArgumentException{
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }

  
  /**
   * attach tree1 and tree2 to the position
   * @param p the target position(must be no children)
   * @param t1  tree1 as left
   * @param t2  tree2 as right
   * @throws IllegalArgumentException if the position has children
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)throws IllegalArgumentException{
    Node<E> node = validate(p);
    if(isInternal(p))throw new IllegalArgumentException("The Position must be a leaf(no child)");
    size += t1.size() + t2.size();
    if(!t1.isEmpty()){
      t1.root.setParent(node);
      node.setLeft(t1.root);
      t1.root = null;
      t1.size =0;
    }
    if(!t2.isEmpty()){
      t2.root.setParent(node);
      node.setRight(t2.root);
      t2.root = null;
      t2.size = 0;
    }
  }

  /**
   * remove the position(can NOT have two children) form the tree
   * @param p target position
   * @return E removed element
   * @throws IllegalArgumentException if the position has two children
   */
  public E remove(Position<E> p)throws IllegalArgumentException{
    Node<E> node = validate(p);
    if(numChildren(p) == 2){
      throw new IllegalArgumentException("The position has two children");
    }
    //find either left or right is the child
    Node<E> child = (node.getLeft() != null? node.getLeft():node.getRight());

    if(child != null){
      child.setParent(node.getParent());//child's grandparent
    }
    if(node == root){ 
      root = child;
    //when the parent is the root, the child becomes the room(parent will be removed)
    }
    else{//if there is a grandparent
      Node<E> parent = node.getParent();
      //find out either the position is the right or left, then attach the child to the grandparent
      if(node == parent.getLeft()){ parent.setLeft(child); }
      else{ parent.setRight(child); }
    }
    //garbage the collection
    size--;
    E temp = node.getElement();
    node.setLeft(null);
    node.setRight(null);
    node.setParent(node);//one's parent is itself => no longer in the list
    return temp;
  }

  public String toString(){
    if(isEmpty()){return "Empty Tree";}
    StringBuilder tree = new StringBuilder();
    tree.append("Tree\n"+"-----\n");
    //! position() method has been overriden as "inorder", call preorder to run specific traversal method
    for(Position<E>p:preorder()){
      String d ="";
      int depth = depth(p);
      for(int i = 0; i < depth; i++){
        d += "  ";
      }
      tree.append(d+"-"+p.getElement()+"\n");
    }
    return tree.toString();
  }

}
