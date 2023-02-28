/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1:  CircularDoublyLinkedList Class
 ********************************************************/

public class CircularDoublyLinkedList<E> implements Cloneable{
  //todo: inner Node object(element) class
  public static class Node<E>{
    private E element;
    private Node<E> previous;
    private Node<E> next;
    // Node constructor
    private Node(E e, Node<E> p, Node<E> n){
      this.element = e;
      this.previous = p;
      this.next = n;
    }

    //getter
    public E getElement(){  return element; }
    public Node<E> getNext(){  return next; }
    public Node<E> getPrevious(){  return previous; }
    public void setNext(Node<E> n){ this.next = n; }
    public void setPrevious(Node<E> p){ this.previous = p;}
    public void setElement(E e){this.element = e;}

    //toString 
    @Override
    public String toString() {
      return this.getElement().toString();
    }
  }

  //todo: CircularDoublyLinkedList class structure
  //private Node<E> header;
  private Node<E> tail;
  private int size =0;
  private boolean reverseOrder;
  //! a default empty list, still have the "dummy fields"
  public CircularDoublyLinkedList(){ 
    //* (e, previous, next)
    tail = new Node<>(null, null, null);
    tail.setNext(tail);
    tail.setPrevious(tail);
    reverseOrder = false;
  };

  //* access method
  public int size(){ return size; }
  public boolean isEmpty(){ return size == 0; }

  /**
   * *depending on the current ordering,
   * return different elemenet
   * @return the NEXT element(player)
   */
  public E first(){
    if(isEmpty())return null;
    if(reverseOrder)return tail.previous.getElement();
    return tail.next.getElement();
  }

  // always return the last(tail)
  public E last(){
    if(isEmpty())return null;
    return tail.getElement();
  }

  //* update method
  //! private method
  private void addBetween(E e, Node<E> predecessor, Node<E> successor){
    //* (e, previous, next)
    Node<E> newest = new Node<>(e, predecessor, successor);
    //previous one link to newest
    predecessor.setNext(newest);
    //newest linked by next one 
    successor.setPrevious(newest);
    size++;
  }

  //add element to the head
  public void addFirst(E e){
    if(isEmpty()){
      this.tail.setElement(e);
      size++;
    }
    else{
      //* (e, previous, next)
      addBetween(e, tail, tail.getNext());
    }
  }
  
  // add element to tail
  public void addLast(E e){
    if(isEmpty()){
      this.tail.setElement(e);
      size++;
    }
    else{
      //* (e, previous, next)
      addBetween(e, tail, tail.getNext());
      tail = tail.next;
    }
  }

  //! private method
  private E remove(Node<E> node){
    Node<E> predecessor = node.getPrevious();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrevious(predecessor);
    size--;
    return node.getElement();
  }
  
  //remove first
  public E removeFirst(){
    if(isEmpty())return null;
    return remove(tail.next);
  }

  //remove last
  public E removeLast(){
    if(isEmpty())return null;
    return remove(tail);
  }


  //* rotate, base on the current ordering,
  public void rotate(){
    if(isEmpty())throw new IndexOutOfBoundsException("\n*** Nothing is in the list yet! ***");
    if(!reverseOrder){
      tail = tail.next;
    }
    else{
      tail = tail.previous;
    }
  }

  //* reverse
  public void reverse(){
    this.reverseOrder = (!reverseOrder);
  }


  //* toString
  public String toString(){
    StringBuilder st = new StringBuilder();
    st.append("[ ");
    Node<E> currentElement = tail.getNext();
    if(!isEmpty()){
      for(int i = 1; i <= this.size; i++){
        st.append(currentElement.getElement().toString());
        currentElement = currentElement.getNext();
      }
    }
    st.append(" ]");
    return st.toString();  
  }
}
