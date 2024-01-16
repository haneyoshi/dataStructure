
public class ScoreBoard {
  private ArrayPositionalList <GameEntry> board;
  public ScoreBoard(int capacity){
    board = new ArrayPositionalList<GameEntry>(capacity);
  }

  public boolean isEmpty(){
    return board.isEmpty();
  }

  
  /** is full
   * @return true if the list is full
   */
  public boolean isFull(){
    return board.isFull();
  }

  public int numberOfEntries(){
    //since we have the list.size to track the actual number of elements
    //no need to initiate another variable "numberOfEntries"
    //we can use list.size instead
    return board.size();
  }

  
  /** add method
   * entry will only be added if the newRecord > bottom record of the list
   * or the list is not full yet
   * *by adapting insertion sort, find the right position to place the newRecord
   * @param g
   */
  public void add(GameEntry g){
    int newRecord = g.getScore();
    if(!isFull()
    || newRecord>board.last().getElement().getScore()){
      if(isEmpty()){
        board.addLast(g);
      }
      else{
        //Since the board is not empty there must be at least one element
        //and we know the new record is at least greater than bottom record on the board list
        Position<GameEntry> cursor = board.last();
        if(isFull()){ 
          //make a room for new record
          cursor = board.before(cursor);
          board.remove(board.after(cursor));//remove last e
        }
        //* the loop will stop when either cursor reachs the first, or new record < the record of the element
        while(cursor != null && newRecord > cursor.getElement().getScore()){
          cursor = board.before(cursor);
        }
  
        if(cursor == null){// new record is greater than the top record
          board.addBefore(board.first(), g);
        }
        else{// new record is less than cursor, so add after cursor
          board.addAfter(cursor, g);
        }
        
      }
    }

  }

  
  /** remove method
   * Exception uccor if index < 0 || index > numberOfEntries
   * @param index is the pisition need to be delete
   * @return the deleted GameEntry
   * @throws IndexOutOfBoundsException
   */
  public GameEntry remove(int index) throws IndexOutOfBoundsException{
    if (index < 0 || index > numberOfEntries())
    throw new IndexOutOfBoundsException("Invalid index: "+ index);

    Position<GameEntry> cursor = board.first();
    for(int i = 0; i<index;i++){
      cursor = board.after(cursor);
    }
    GameEntry deletedElement = cursor.getElement();
    board.remove(cursor);
    return deletedElement;
  }

  public PositionalList<GameEntry> getBoard() {
    return board;
  }

  //toString
  public String toString(){
    StringBuilder boardList = new StringBuilder();
    boardList.append("ScoreBoard: \t{ ");
    for(Position<GameEntry> g: this.board.positions()){
      boardList.append(g+" ");  
    }
    boardList.append("}");  
    return boardList.toString();
  }
}
