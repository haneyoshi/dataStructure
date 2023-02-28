/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1: Stack interface 
 ********************************************************/

public interface Stack<E> {
  int size();
  boolean isEmpty();
  void push(E e);
  E pop();
  E top();
}
