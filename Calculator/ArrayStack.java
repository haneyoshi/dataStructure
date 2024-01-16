

public class ArrayStack<E> implements Stack<E>{
  //default capacity
  public static final int CAPACITY  = 1000;
  private E[] data;
  private int topElementIndex = -1;

  //todo: constructor
  //* default constructor
  public ArrayStack(){ this(CAPACITY); }

  //* args constructor
  public ArrayStack(int capacity){
    this.data = (E[]) new Object[capacity];
  }
  
  //todo: access & update method
  public int size(){ return (topElementIndex + 1); }
  public boolean isEmpty(){return (topElementIndex == -1); }

  //* stack up an element
  public void push(E e) throws IllegalStateException{
    if(size() == data.length)throw new IllegalStateException("Stack is full");
    //! original expression will lead to an index our of boundary erro, since topElementIndex starts " -1 "
    topElementIndex++;
    data[topElementIndex] = e;
  }

  //* get the top element
  public E top(){
    if(isEmpty()) return null;
    return data[topElementIndex];
  }

  //* remove the top element
  public E pop(){
    if(isEmpty()) return null;
    E takenElement = data[topElementIndex];
    data[topElementIndex] = null;
    topElementIndex--;
    return takenElement;
  }

  //todo: generic method to the reversing array
  public void reverse(){
    E[] buffered = (E[]) new Object[data.length];
    int reverseOrder = data.length-1;
    for(int i =0; i< data.length; i++){
      buffered[reverseOrder] = data[i];
    }
    data = buffered;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for(int i = 0; i<data.length;i++){
      sb.append(data[i]);
    }
    sb.append(" ]");
    return sb.toString();
  }
}