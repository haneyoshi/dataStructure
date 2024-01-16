
public class Peg {
  private String colour;
  public Peg(String colour){
    this.colour = colour;
  }

  //setter and getter
  public void setColour(String c){
    this.colour = c;
  }
  public String getColour(){
    return this.colour;
  }

  /**
   * equals method:
   * *compare two Peg object with their colours
   * @param p another object Peg
   * @return boolean if both are the same colour
   */
  public boolean equals(Peg p){
    return this.getColour().equals(p.getColour());
  }

  //toString
  public String toString(){
    return this.colour;
  }
}
