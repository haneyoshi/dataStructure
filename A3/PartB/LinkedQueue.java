/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: LinkedQueue
 ********************************************************/

public class LinkedQueue<E> implements Queue<E> {
  private SinglyLinkedList<E> list = new SinglyLinkedList<>();
  public LinkedQueue(){ }//constructor
  public int size(){ return list.size(); }
  public boolean isEmpty(){ return list.isEmpty(); }
  public void equeue(E e){ list.addLast(e); }
  public E first(){ return list.first(); }
  public E dequeue(){ return list.removeFirst(); }
}
