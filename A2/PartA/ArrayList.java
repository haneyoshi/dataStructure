/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assingment2
 * 
 * class: ArrayList
 ********************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayList<E> implements List<E>{
  public static final int CAPACITY = 4;//default capacity 4
  private E[] data;
  private int size = 0;

  //constructor
  public ArrayList(){ this(CAPACITY); }
  //args constuctor
  public ArrayList(int capacity){
    data = (E[]) new Object[capacity];
  }

  //access method
  public int size(){ return size; }
  public boolean isEmpty(){ return size==0; }

  /** sizeAndCapa()
   * to illustate the implementation of dynamic data structure
   * @return string presentation of current size and capacity
   */
  public String sizeAndCapa(){
    return "(size = "+this.size+", capacity = "+ this.data.length+")";
  }
  public E get(int i)throws IndexOutOfBoundsException{
    checkIndex(i, size);
    return data[i];
  }

  //replace the element at the indext i with e,
  //and return the replaced element
  public E set(int i, E e)throws IndexOutOfBoundsException{
    checkIndex(i, size);
    E temp = data[i];
    data[i] = e;
    return temp;
  }

  //todo: --------    add and remove methods  -----
  /**
   * add method
   * *if list is full, double the size
   * inset the element e to be at index i,shift rest of subsequent elements backward
   * @param i 
   * @param e 
   * @throws IndexOutOfBoundsException
   */
  public void add(int i, E e)throws IndexOutOfBoundsException{
    checkIndex(i, size+1);//! actual number of element
    //! implement dynamic array method
    if(size+1 == data.length){// +1 for the purpose of illustrate the change of capacity
      resize(data.length * 2);//double up the size
    }

    for(int k = size -1; k>=i ; k--){
      data[k+1] = data[k];
      //e.g. 0 1 2 3 4 5 6 
      //     0 1 2 3 3 4 5 6
      //     0 1 2 e 3 4 5 6
    }
    data[i] = e;
    size++;
  }

  
  /**
   * remove method
   * *if the list is less than 1/4 of capacity, cut the capacity in half
   * @param i remove element at i, shift rest of subsequent elements forward.
   */
  public E remove(int i)throws IndexOutOfBoundsException{
    checkIndex(i, size);//! size = actual number of elements
    E temp = data[i];
    for(int k=i; k<size-1; k++){
      data[k] = data[k+1];
      //e.g. 0 1 2 e 3 4 5 6
      //     0 1 2 3 3 4 5 6
      //     0 1 2 3 4 5 6 
    }
    data[size-1] = null;
    size--;
    if(size < (data.length/4)){
      resize(data.length / 2);//cut in half
    }
    return temp;
  }

  /**
   * additional add method
   * @param e add the element to the last index
   */
  public void add(E e){
    if(size+1 == data.length){
      resize(data.length * 2);//double up the size
    }
    //! size tracks the current number of elements, also points to the vacant position after last element
    data[size] = e;
    size++;
  }//----------------------  end of add & remove methods  ----------------

  protected void checkIndex(int i, int n)throws IndexOutOfBoundsException{
    if(i < 0 || i>= n)throw new IndexOutOfBoundsException("Ellegal index: "+i);
  }

  //*to implement a dynamic array method
  protected void resize(int capacity){
    E[] temp = (E[]) new Object[capacity];
    for(int k = 0; k< size; k++){
      temp[k] = data[k];
    }
    data = temp;
  }

  //todo: -----------      customized equals method    ---------------------
  /**
   * equals method
   * ! the specified parameter <Peg> implies that this special method only compare two <Peg> lists
   * * build-in generic equals() method can be called by passing any ArrayList BUT NOT <Peg>
   * @param <Peg>list
   * @return true if every peg'colour matchs
   * @throws IllegalArgumentException if the parameter is not a list of pegs
   */
  public boolean equals(ArrayList<Peg> list)throws IllegalArgumentException{
    if(list.getClass() != this.getClass())throw new IllegalArgumentException("Lists types different");//make sure this method is only used to compare two Peg Arraylist
    //! in order to use equals() method in Peg class, elements of THIS list must cast into Peg object, since this ArrayList class is generic.
    //* Meaning that, by default, this.get(i) is calling java "Object" build-in equals method, which compare the addresses.
    boolean colourAllMatched = true;
    for(int i = 0; i< this.size && colourAllMatched ;i++){
      Peg pInCode = (Peg)this.get(i);
      //System.out.println(this.get(i).getClass()+" vs "+list.get(i).getClass());
      colourAllMatched = pInCode.equals(list.get(i));
      //System.out.println("code("+i+") vs guess("+i+") "+colourAllMatched);
    }
    //System.out.println("boolean: "+colourAllMatched);
    return colourAllMatched;
  }//------------------------------- end of special equals method   ------------------------

  //todo: -----------       implement Iterator   ---------------------
  /**
   * Iterator<E>
   * *private
   */
  private class ArrayIterator implements Iterator<E>{
    private int nextIndex = 0;
    private boolean removable = false;

    public boolean hasNext(){ return nextIndex < size; }
    public E next()throws NoSuchElementException{
      if(nextIndex==size)throw new NoSuchElementException("No next element");
      removable = true;
      return data[nextIndex++];
    }

    public void remove()throws IllegalStateException{
      if(!removable)throw new IllegalStateException("Nothing to remove");
      ArrayList.this.remove(nextIndex-1);
      //pass the index to remove
      nextIndex--;
      removable = false;
    }
  }
  //-----------  end of nested ArrayIterator class ------

  //todo: instance of ArrayIterator inner class  --------
  //* promised method
  public Iterator<E> iterator(){
    return new ArrayIterator();
  }

  //toString
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder("");
    Iterator<E> iter = this.iterator();
    while(iter.hasNext()){
      sb.append(iter.next()+" ");
    }
    // for(E e: this.data){
    //   sb.append(e+" ");
    // }
    return sb.toString();
  }
}
