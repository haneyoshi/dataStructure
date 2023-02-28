/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: Queue
 ********************************************************/

public interface Queue <E>{
  int size();
  boolean isEmpty();
  void equeue(E e);
  E first();
  E dequeue();
}
