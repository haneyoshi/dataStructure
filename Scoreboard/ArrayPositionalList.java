import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPositionalList<E> implements PositionalList<E>{

  //todo: -----------  nested class: ArrPos   --------
  private static class ArrPos<E> implements Position<E>{
    private E element;
    private int index;

    public ArrPos(int i,E e){
      this.element = e;
      this.index = i;
    }

    public E getElement()throws IllegalStateException{
      if(this.index == -1){
        throw new IllegalStateException(("The elements is no longer in the list"));
      }
      return this.element;
    }

    public int getIndex(){
      return this.index;
    }

    public void setElemenet(E e){
      this.element = e;
    }

    public void setIndex(int i){
      this.index = i;
    }

    public String toString(){
      return "["+this.index+"]"+this.element;
    }

  }//--------------   end of nested class ArrPos -----

  //todo: ------- Structure of ArrayPositionalList ----
  public static final int CAPACITY = 16;
  ArrPos<E>[] data;
  private int size = 0;
  //* arg constructor
  public ArrayPositionalList(int capacity){
    //! array of "Arrpos", E is the object element:
    //data = (ArrPos<E>[]) new Object[capacity]; is type cast error!!!!
    data = (ArrPos<E>[]) new ArrPos[capacity];
  }

  //* no-arg constructor
  public ArrayPositionalList(){ this(CAPACITY); }

  public int size() { return size; }
  public boolean isEmpty() { return size == 0; }
  public boolean isFull() { return size == data.length; }

  
  /** validate method
   * take the Position parameter, throw exception if the position is invalid
   * @param position if valid then cast to ArrPos
   * @return arrpos.getIndex()-> the index of the element in the data[]
   * @throws IllegalArgumentException
   */
  private int validate(Position<E> position)throws IllegalArgumentException{
    //* to be valid, object type must be ArrPos
    if(!(position instanceof ArrPos))throw new IllegalArgumentException("Invalid position");
    //* if valid, cast the parameter to Arrpos which then grant some access methods, such as getIndex
    ArrPos<E> arrpos =(ArrPos<E>)position;
    //* index = -1, means the position 
    if(arrpos.getIndex()== -1)throw new IllegalArgumentException("position is no longer in the list");
    return arrpos.getIndex();
  }

  
  /** position method
   * @param arrpos
   * @return return Position<E>
   */
  private Position<E> position(ArrPos<E> arrpos){
    return arrpos;
  }

  
  /** first method
   * @return Position<E> of first element in data[]
   */
  public Position<E> first(){
    if(isEmpty()){ return null; }
    return position(data[0]);
  }

  
  /** last method
   * @return Position<E> of last element in data[]
   */
  public Position<E> last() {
    if(isEmpty()){ return null; }
    return position(data[size-1]);
  }

  
  /** before method
   * @param position 
   * @return Position<E> before the parameter(position)
   * @throws IllegalArgumentException
   */
  public Position<E> before(Position<E> position) throws IllegalArgumentException {
    //check if the position parameter is valid, which will return the index of position
    int targetIndx = validate(position)-1;
    if(targetIndx == -1){ return null; }//first position index is 0
    ArrPos<E> arrpos = data[targetIndx];
    return position(arrpos);
  }

  
  /** after method
   * @param position
   * @return Position<E> after the parameter(position)
   * @throws IllegalArgumentException
   */
  public Position<E> after(Position<E> position) throws IllegalArgumentException {
    //check if the position parameter is valid, which will return the index of position
    int targetIndx = validate(position)+1;
    if(targetIndx == size){ return null; }//last position index is size -1
    ArrPos<E> arrpos = data[targetIndx];
    return position(arrpos);
  }

  
  /** addFirst method
   * @param e
   * @return Position<E> of an added object element(ArrPos) in data[]
   */
  public Position<E> addFirst(E e) {
    ArrPos<E> newArrpos = new ArrPos<>(0,e);
    inserts(0, newArrpos);
    return position(newArrpos);
  }

  
  /** addLast
   * @param e
   * @return Position<E> of an added object element(ArrPos) in data[]
   */
  public Position<E> addLast(E e) {
    ArrPos<E> newArrpos = new ArrPos<>(size,e);
    inserts(size, newArrpos);
    return position(newArrpos);
  }

  
  /** addBefore
   * *get the index of the position(passed as parameter)
   * @param position
   * @param e to be added before the position
   * @return Position<E> of an added object element(ArrPos) in data[]
   * @throws IllegalArgumentException
   */
  public Position<E> addBefore(Position<E> position, E e) throws IllegalArgumentException {
    int targetIndx = validate(position);
    ArrPos<E> newArrpos = new ArrPos<>(targetIndx,e);
    inserts(targetIndx, newArrpos);
    //inset method will shif all elemets from the target intext forward
    return position(data[targetIndx]);
  }

  
  /** addAfter
   * *get the index of the position(passed as parameter)
   * @param position
   * @param e to be added after the position
   * @return Position<E> of an added object element(ArrPos) in data[]
   * @throws IllegalArgumentException
   */
  public Position<E> addAfter(Position<E> position, E e) throws IllegalArgumentException {
    int targetIndx = validate(position)+1;
    //targetIndex is pointing to the index after the position
    if(targetIndx == size){
      addLast(e);
    }
    else{
      ArrPos<E> newArrpos = new ArrPos<>(targetIndx,e);
      inserts(targetIndx, newArrpos);
      //inset method will shif all elemets from the target intext forward
    }
    return position(data[targetIndx]);
  }

  
  /** set method
   * @param position to get the index 
   * @param e to be set to the position(passed as parameter)
   * @return E =data[the index]
   * @throws IllegalArgumentException
   */
  public E set(Position<E> position, E e) throws IllegalArgumentException {
    //check if the position parameter is valid, which will return the index of position
    int targetIndx = validate(position);
    data[targetIndx].setElemenet(e);
    return data[targetIndx].getElement();
  }

  
  /** remove
   * *after remove the element from data[],
   * *shift rest of elements, and reset the index field(ArrPos)
   * @param position
   * @return E, the element been removed
   * @throws IllegalArgumentException
   */
  public E remove(Position<E> position) throws IllegalArgumentException {
    int targetIndx = validate(position);
    ArrPos<E> removedArrpos = data[targetIndx];
    E e = data[targetIndx].getElement();
   removedArrpos.setIndex(-1);
    for(int k=targetIndx; k<size-1; k++){
      data[k] = data[k+1];
      //e.g. 0 1 2 e 3 4 5 6
      //     0 1 2 3 3 4 5 6
      //     0 1 2 3 4 5 6 6
      data[k].setIndex(k);//reset index
    }
    data[size-1] = null;
    size--;
    if(size < (data.length/4)){
      resize(data.length / 2);//cut in half
    }
    return e;
  }

  
  /** private inserts method
   * *addFirst, addLst, addBefore, addAfter all need this method
   * *by targeting specific index of data[]
   * *shift rest of elements to make a room for the element
   * @param index
   * @param arrpos
   */
  private void inserts(int index, ArrPos<E> arrpos){
    if(isFull()){
      resize(data.length * 2);//double up the size
    }
    size++;
    int tail = size -1;
    //* from bottom to the target index, shift all elements forward, then reset the element index.
    for(int i = tail; i>index ;i--){
      data[i]=data[i-1];
      data[i].setIndex(i);
    }
    //addlast will skip the for loop, come straight here
    data[index]=arrpos;
  }

  
  /** private resize method
   * although this method is not used in this assignment
   * @param capacity passed by other method
   */
  private void resize(int capacity){
    ArrPos<E>[] temp = (ArrPos<E>[]) new ArrPos[capacity];
    //size is the current number of elements
    for(int k = 0; k< size; k++){
      temp[k] = data[k];
    }
    data = temp;
  }

  private class PositionIterator implements Iterator<Position<E>>{
    private int nextIndex = 0;
    private boolean removeable = false;
    public boolean hasNext(){ return nextIndex<size; }
    public Position<E> next()throws NoSuchElementException{
      if(nextIndex==size){
        throw new NoSuchElementException("No next element");
      }
      removeable = true;
      return position(data[nextIndex++]);
    }

    public void remove()throws IllegalStateException{
      if(!removeable){
        throw new IllegalStateException("Nothing to remove");
      }
      ArrayPositionalList.this.remove(position(data[nextIndex-1]));
      nextIndex--;
      removeable = false;
    }
  }
  
  private class PositionIterable implements Iterable<Position<E>>{
    public Iterator<Position<E>> iterator(){
      return new PositionIterator();
    }
  }
  
  
  /** positions method
   * for iteration use, return the positions
   * @return Iterable<Position<E>>
   */
  public Iterable<Position<E>> positions() {
    return new PositionIterable();
  }
  
  private class ElementIterator implements Iterator<E>{
    Iterator<Position<E>> posIterator = new PositionIterator();
    public boolean hasNext(){ 
      return posIterator.hasNext(); 
    }
    public E next(){ 
      return posIterator.next().getElement(); 
    }
    public void remove(){ posIterator.remove(); }
  }

  
  /** iterator method
   * * used to iter through the list and return the element
   * @return Iterator<E>
   */
  public Iterator<E> iterator(){
    return new ElementIterator();
  }
  
  public String toString(){
    StringBuilder st = new StringBuilder();
    st.append("{ ");

    //* output the oject(Position) to have index presentation
    if(!isEmpty()){
      Iterable<Position<E>> iterable = this.positions();
      Iterator<Position<E>> iter = iterable.iterator();
      st.append(iter.next());
      while(iter.hasNext()){
        st.append(", "+iter.next());
      }
    }

    st.append(" }");
    return st.toString();  
  }
}
