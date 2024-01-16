
import java.util.Iterator;
public class GameDriver {
  public static void main(String[] args){
    System.out.println("\n\n\t***** Score Board *****\n");
    Position<GameEntry> cursor;
    //* some data
    taskMessage(" ** some data: ");
    GameEntry[] entries = {
      new GameEntry("Rose", 590),
      new GameEntry("Paul", 720),
      new GameEntry("Jack", 510),
      new GameEntry("Anna", 660),
      new GameEntry("Mike", 1150),
      new GameEntry("Rob", 750)
    };
    for(GameEntry entry:entries){
      System.out.print(entry+" ");
    }
    

    //todo: create scor board and add data
    System.out.print("\n\n ** add data to the score board, at meantime place to the right index sorted by score");
    ScoreBoard scoreBoard = new ScoreBoard(7);
    for(GameEntry g: entries){
      scoreBoard.add(g);
    }
    output(scoreBoard);


    //todo: add Jill and remove Paul
    taskMessage("\n ** add Jill:");
    scoreBoard.add(new GameEntry("Jill", 740));
    output(scoreBoard);
    taskMessage("\n ** remove Paul(by index):");
    scoreBoard.remove(3);//Paul was original at the index of 2
    output(scoreBoard);
    
    taskMessage("\n------- from here: starts to show all implemented method  -------");
    taskMessage(" ** get first record:");
    output(scoreBoard.getBoard().first());

    taskMessage("\n ** get last record:");
    output(scoreBoard.getBoard().last());

    taskMessage("\n ** add A to first:");
    scoreBoard.getBoard().addFirst(new GameEntry("A", 0));
    output(scoreBoard);

    taskMessage("\n ** add Z to last:");
    scoreBoard.getBoard().addLast(new GameEntry("Z", 900));
    output(scoreBoard);

    taskMessage("\n ** add Y before Jack:");
    cursor = scoreBoard.getBoard().before(scoreBoard.getBoard().last());
    //sursor points to the position before last(Z)
    scoreBoard.getBoard().addBefore(cursor, new GameEntry("Y", 1));
    output(scoreBoard);

    taskMessage("\n ** add B after Mike:");
    cursor = scoreBoard.getBoard().after(scoreBoard.getBoard().first());
    //sursor points to the position after first(A)
    scoreBoard.getBoard().addAfter(cursor, new GameEntry("B", 9));
    output(scoreBoard);

    taskMessage("\n ** remove A(by position):");
    //remove the first "position"
    scoreBoard.getBoard().remove(scoreBoard.getBoard().first());
    output(scoreBoard);

    taskMessage("\n ** element iterator:");
    Iterable<Position<GameEntry>> iterable = scoreBoard.getBoard().positions();
    Iterator<Position<GameEntry>> iter = iterable.iterator();
    cursor = iter.next();
    System.out.print(cursor);
    while(iter.hasNext()){
      cursor = iter.next();
      System.out.print(", "+cursor);
    }

    taskMessage("\n\n ** element iterator:");
    Iterator<GameEntry> eIter = scoreBoard.getBoard().iterator();
    GameEntry entry = eIter.next();
    System.out.print(entry);
    while(eIter.hasNext()){
      entry = eIter.next();
      System.out.print(", "+entry);
    }

    System.out.println("\n\t***** End of program *****");
  }

  
  /** taskMessage
   * @param m string message to output
   */
  public static void taskMessage(String m){
    System.out.println(m);
  }

  
  /** output
   * as long as toString method is well set up
   * @param e generic parameter
   */
  public static <E>void output(E e){
    System.out.println(e);
  }
}
