/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assignment3
 * 
 * Class: AbstractPriorityQueue
 ********************************************************/

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V>{
  
  //todo:-------------------- nested PQEntry class  --------------------
  protected static class PQEntry<K,V> implements Entry<K,V>{
    private K k;//key
    private V v;//value

    //* constructor
    public PQEntry(K key, V value){
      k = key;
      v = value;
    }

    //* methods 
    public K getKey(){ return k; }
    public V getValue(){ return v; }

    protected void setKey(K key){ k = key; }
    protected void setValue(V value){ v = value; }

    public String toString(){
      return "{"+k+": "+v+"}";
    }
  }//----------------------------    end of nested PQEntry class

  //todo:------------- nested AbstractPriorityQueue class  ---------------
  //* instance variavle
  private Comparator<K> comp;// for data ordering

  //* comstructor
  //use the given ordering
  protected AbstractPriorityQueue(Comparator<K> c){ comp = c; }
  //default ordering
  protected AbstractPriorityQueue(){ this(new DefaultComparator<K>()); }

  /**
   * compare two Entries by their keys
   * @param a first Entry
   * @param b second Entry
   * @return integer
   */
  protected int compare(Entry<K,V> a, Entry<K,V> b){
    //! not the elements can be compared, only if the elements are comparable
    return comp.compare(a.getKey(),b.getKey());
  }

  protected boolean checkKey(K key)throws IllegalArgumentException{
    try{//! see if the key can be compared to itself
      return (comp.compare(key, key) ==0);
    }catch(ClassCastException e){// if false
      throw new IllegalArgumentException("Incompatible key");
    }
  }

  public boolean isEmpty(){ return size() == 0; }

}
