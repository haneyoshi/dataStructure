/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1:  GameSim Class
 ********************************************************/

import java.util.ArrayList;

public class GameSim {

  private CircularDoublyLinkedList<Player> players;
  private Deck desk;
  private Card lastCard;
  public GameSim(){ 
      this.desk = new Deck();
      players = new CircularDoublyLinkedList<>();
   }

   /**
    * gameStart
    * atribute 7 cards for each player
    * and deal first card
    */
  public void gameStart(){
    System.out.println("\n\n\t\t***** GAME STARTS *****\n\n");
    desk.shuffle();
    Player nextPlayer = nextPlayer();//player.first
    //give a card to every one and for 7 times
    for(int i = 1; i <= 7; i++){
      for(int j = 0; j < players.size(); j++){
      nextPlayer = nextPlayer();//rotate
      nextPlayer.addCardToHand(desk.deal());
      rotaPlayer();
      }
    }
    lastCard = desk.deal();
    if(lastCard.getFace().getFaceLetter().equals("Reverse")){
      rotaPlayer();//since the tail pointing to the last element, here is to offset the order.
    }
    System.out.println("First card: "+ lastCard+"\n");
  }
  
  /**
   * nextTurn
   * next player in the list(from last player),
   * either way(reverse)
   * check if the player has any cards in hand can play
   * (match last card)
   */
  public void nextTurn(){
    Player currentPlayer = nextPlayer();
    ArrayList<Card> cardInhand = currentPlayer.getHand();
    boolean cardFoud = false;
    Card cardCanPlay = new Card();
    System.out.println(currentPlayer);// name' hand: [cards...]
    for(int i = 0; i< cardInhand.size() && !cardFoud; i++){
      if(lastCard.playable(cardInhand.get(i))){
        cardCanPlay = cardInhand.get(i);
        currentPlayer.plays(i);
        cardFoud = true;
       }
     }

    if(cardFoud){// print out the message: player + the card
      playerPlays(currentPlayer, cardCanPlay);
    }
    else if(!cardFoud && currentPlayer.hasWildCard){
      //no card in hand match, but has wild card to play
      playerPlays(currentPlayer, currentPlayer.getWildCard());
    }
    else{
      //deal a card, and see if it can be played
      dealCard(currentPlayer); 
    }
  }

    
    /** just to output 
     * @param p as current player
     * @param card the card played
     */
    public void playerPlays(Player p, Card card){
      lastCard = card;
      System.out.println(nextPlayer().name+" plays "+ lastCard+"\n");
    //last two cases can have 0 or 1 card left
    if(p.getHand().size() == 1){
      //yell uno
      p.uno();
    }
  }

  
  /**  deal a card, and see if it can be played
   * @param p current player
   */
  //! edited
  private void dealCard(Player p){
    Card newCard = desk.deal();
    p.addCardToHand(newCard);
    System.out.println(p.name+" has no card to play, draw "+ newCard);

      if(!lastCard.playable(newCard) && (!p.hasWildCard) ){
        //the card doesn't match
        System.out.println(p.name+" can't play it\n");
      }
      else if(p.hasWildCard){
        //lucky draw a wild card, play it
        playerPlays(p, p.getWildCard());
      }
      else{
        playerPlays(p,newCard);
        //to print out the player plays this card
      }
  }

  
  /** add player
   * @param p player
   */
  public void addPlayer(Player p){
      players.addLast(p);
  }

  
  /** 
   * @return next Player to play
   */
  //* get next player, and rotate
  public Player nextPlayer(){
    Player nextPlayer = players.first();
    return nextPlayer;
  }


  
  /** 
   * @return Card
   */
  public Card getLatCard(){
    return this.lastCard;
  }
  
  
  /** if desk empty first, means no winner
   * @return boolean 
   */
  public boolean isDeskEmpty(){
    return desk.isEmpty();
  }

  //* rotate set tail to last player
  public void rotaPlayer(){
     players.rotate();
  }

  
  /** check if the card is an action card
   * and apply to special fuction
   * @param card the card was played
   */
  //* check action card
  public void cardIs(Card card){
    if(card.special){
      Face f = card.getFace();
        if(f==Face.WILD){ //wild card
          wildAction(); 
        }else if(f == Face.REVERSE){//reverse card
          reverseAction();
          lastCard = card;
        }
        else{//default skip card
          skipAction();
          lastCard = card;
        }

      }     
      
  }

  //*a wild card has been played
  public void wildAction(){
    //get random int 1 to 4
    int r = (int)((1 + (4 - 1 + 1)*Math.random()));
    String colour = "";
    // get random colour: Yellow, Green, Blue, red
    switch(r){
      case 1: colour = "YELLOW";
      break;
       case 2: colour = "GREEN";
      break;
      case 3: colour = "BLUE";
        break;
        default: colour = "RED";
        break;
    }
    String wild = "WILD";
    lastCard = new Card( Colour.valueOf(colour), Face.valueOf(wild));
    System.out.println("Colour is now: "+colour+"\n");  
  }
  
  
  //*a skip card has been played
  public void skipAction(){
    Player nextPlayer = nextPlayer();
    //this player who is passed because the skip card
    System.out.println(nextPlayer.name+" misses a turn\n");
    rotaPlayer();//next player
  }

  //*a reverse card has been played
  public void reverseAction(){
    players.reverse();
    //players.rotate();
  }

  //to String
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Current desk:\n" +desk.toString()+"\n\n");
    sb.append("Current players:\n" +players+"\n");
    sb.append("last card: "+ lastCard);
    return sb.toString();
  }
}

