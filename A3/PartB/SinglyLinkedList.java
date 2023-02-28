

public class SinglyLinkedList<E> implements Cloneable {
  // todo: inner Node object(element) class
  // ! Note: it's a static class
  public static class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // getter
    public E getElement() {
      return element;
    }

    public Node<E> getNext() {
      return next;
    }

    // setter
    public void setElement(E element) {
      this.element = element;
    }

    public void setNext(Node<E> next) {
      this.next = next;
    }

    public boolean equals(Object o){
      if(o == null){
        System.out.println("object == null");
        return false;        
      }
      if(this.getClass() != o.getClass()){
        System.out.println("Class != Class");
        return false;
      }
      //since two class is equal
      Node<E> aNode = (Node<E>)o;
      if(!this.element.equals(aNode.getElement())){
        System.out.println("e ≠ e");
        return false;
      }
      return true;
    }

    //toString 
    @Override
    public String toString() {
      return this.getElement().toString();
    }
  }

  // todo: SinglyLinkedList class structure
  private Node<E> head = null;
  private Node<E> tail = null;
  private int size = 0;

  public SinglyLinkedList() {
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * first: to get first element
   * 
   * @return E
   */
  // the head
  public E first() {
    if (isEmpty())
      return null;
    return head.getElement();
  }

  /**
   * last: to get the last element
   * 
   * @return E
   */
  // the tail
  public E last() {
    if (isEmpty())
      return null;
    return tail.getElement();
  }

  /**
   * addFirst: add new element before current first element
   * 
   * @param e take element(node)
   */
  // add Node
  public void addFirst(E e) {
    head = new Node<>(e, head);
    if (size == 0)
      tail = head;
    size++;
  }

  /**
   * addLast: add on after another(current last element)
   * 
   * @param e take an element(Node)
   */
  public void addLast(E e) {
    Node<E> newest = new Node<>(e, null);
    if (isEmpty()) {
      head = newest;
    } else {
      // the CURRENT tail points to new element
      tail.setNext(newest);
    }
    // SET tail to new element
    tail = newest;
    size++;
  }

  /**
   * remove element
   * 
   * @return E
   */
  public E removeFirst() {
    if (isEmpty())
      return null;
    E answer = head.getElement();
    head = head.getNext();
    size--;
    // answer stores the head element
    // by setting current head to next element,
    // remove the answer from the list
    if (size == 0)
      tail = null;
    // ensure when size is down to 0, tail = null
    return answer;
  }

  public boolean equals(Object obj) {
    if (obj == null){ 
      System.out.println("List = null");
      return false;
    };

    if (this.getClass() != obj.getClass()) {
      System.out.println("Class != Class");
      return false;
    };
    
    // cast object
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) obj;
    if (this.size != other.size) {
      System.out.println("Size != Size");
      return false;
    };

    Node<E> walkA = this.head;
    Node<E> walkB = other.head;
    while (walkA != null) {
      //! head and tail are "copy" objects
      //! (refer to head and tail elements of the list)
      //! while doublyLinkedlist is different
      if (!walkA.getElement().equals(walkB.getElement())){ 
        //* Node will out put message unequal
        return false;
      }

      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;
  }
  //! Note: SinglyLinkeList is to build up the link
  //! by create a soild object(Node)
  //! then set the reference( setNext() )
  //!! while
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException{
    SinglyLinkedList<E> cloneList = (SinglyLinkedList<E>) super.clone();
    // System.out.println("cloneList size: "+ cloneList.size);
    // System.out.println(cloneList.toString());
    //! by refering clone within this class
    //! copy this list (same size same elements)

    if(size > 0){
      cloneList.head = new Node<>(this.head.getElement(), null);
      Node<E> cloneListTail = cloneList.head;
      //fist element added, both head and tail pointing to
      // the same element
      Node<E> walk = this.head.getNext();
      while( walk != null){
        Node<E> newest = new Node<>(walk.getElement(), null);
        cloneListTail.setNext(newest);
        cloneListTail = newest;
        walk = walk.getNext();
      }
    }
    return cloneList;
  }

  /**
   * toString
   * 
   * @return String: every element in SinglyLinkedList chain
   */
  public String toString() {
    StringBuilder st = new StringBuilder();
    st.append("[ ");
    Node<E> currentElement = head;
    if (currentElement.getElement() != null) {
      st.append(currentElement.getElement().toString());
      while (currentElement.getNext() != null) {
        currentElement = currentElement.getNext();
        st.append(", " + currentElement.getElement().toString());
      }
    }
    st.append(" ]");
    return st.toString();
  }
}
