/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assingment2
 * 
 * class: GameEntry
 ********************************************************/
public class GameEntry{
  private String name;
  private int score;

  public GameEntry(String name, int score){
    this.name = name;
    this.score = score;
  }

  //getter
  public String getName(){ return this.name; }
  public int getScore(){ return this.score; }

  //toString
  public String toString(){
    return this.name+ ": "+ this.score;
  }
}
