/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: DefaultComparator
 ********************************************************/


import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E>{
  public int compare(E a, E b) throws ClassCastException{
    return ((Comparable<E>)a).compareTo(b);
    //! i < 0 => a < b
    //! i = 0 => a = b
    //! i > 0 => a > b
  }
}
