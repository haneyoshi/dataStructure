
/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1: PartB Driver Class 
 ********************************************************/

public class Assign1PartA_Driver {
  public static void main(String[] args){
    
    GameSim uno = new GameSim();
    Player nala = new Player("A");//nala Nala
    Player simba = new Player("B");//simba Simba
    Player kion = new Player("C");//kion Kion
    Player kiara = new Player("D");//kiara Kiara
    
    uno.addPlayer(nala);
    uno.addPlayer(simba);
    uno.addPlayer(kion);
    uno.addPlayer(kiara);

    boolean hasWinner = false;
    Player thePlayer = new Player();

    //** game starts */
    uno.gameStart();
    //atribute 7 cards for each player
    //deal first card
    //print to console

   
    while( (!uno.isDeskEmpty()) && (!hasWinner) ){
      uno.cardIs(uno.getLatCard());
      uno.nextTurn();
      thePlayer = uno.nextPlayer();
      hasWinner = (thePlayer.getHand().size() == 0 );
      uno.rotaPlayer();
    }
    if(hasWinner){
      System.out.println(thePlayer.name+" won the game!");
    }
    else{
      System.out.println("The game is over, no winner");
    }

    System.out.println("\n\n\t ***** End of the program ***** \n\n");
  }

  
}
