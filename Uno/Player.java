


import java.util.ArrayList;

public class Player {

  public String name;
  private ArrayList<Card> hand;

  //* to check if the player has the special card in hand
  public boolean hasWildCard;

  public Player(){
    name = "";
    hand = new ArrayList<>();
    hasWildCard = false;
  }

  public Player(String name){
    this.name = name;
    //default no card is hold by the player
    hand = new ArrayList<>();
    hasWildCard = false;
  }

  //access method
  public int size(){
    return this.hand.size();
  }

  public boolean isEmpty(){
    return this.size() ==0;
  }

  public ArrayList<Card> getHand(){
    return this.hand;
  }

  //add card to player's hand
  public void addCardToHand(Card c){
    this.hand.add(c);
    if(c.getFace().getFaceLetter().equals("Wild")){
      hasWildCard = true;
    }
  }

  //todo: a special method, allow player to play wild card when no card to play.
  public Card getWildCard(){
    Card w = null;
    if(hasWildCard){
      for(int i = 0; i < hand.size(); i++){
        if(hand.get(i).getFace().getFaceLetter().equals("Wild")){
          w = hand.get(i);
          //play wild card and remove it
          this.plays(i);
          hasWildCard = false;
        }
      }
    }
    return w;
  }

  //remove the card from playe's hand, and return to output
  public Card plays(int index){
    if(isEmpty()){
      return null;
    }
    Card card  = hand.get(index);
    hand.remove(index);
    return card;
  }

  //call this method when one card left in hand
  public void uno(){
    System.out.println(this.name+" yells \"UNO!\"\n");
  }

  
  @Override
  public String toString(){
    return this.name+"'s hand': " + hand.toString();
  }
}
