import java.util.Scanner;

public class MasterMind {
  public static void main(String args[]){
    System.out.println("\n\nBrief decription: An empty ArrayList takes the random 4 colours as code(capacity change will show along the game). Another ArrayList takes guess from input, any element match will be remove at meantime add coresponding marker(O, X, or -) to feedback. The guess list changes will also illustrate along the game");

    System.out.println("\n\n\t\t***** board game Mastermind *****\n");

    //* some instances & variables
    Scanner kb = new Scanner(System.in);
    ArrayList<Peg> code = setCode();
    ArrayList<Peg> guess = new ArrayList<>();
    String feedback =""; //to store the result from comparison, ready for output
    int attempts = 0;
    boolean allMatch = false;

    //todo: -----------  game starts here  ----------
    System.out.println("\n\t------ Game Starts ------");
    //System.out.println("[Code: "+code+"]");
    while(attempts!=12 && !allMatch){
      attempts++;
      System.out.println("System:\t"+"Guess #"+attempts+":");
      System.out.print("Player:\t");
      String input = "";
      input = kb.nextLine();
      guess = getGuess(input);//retrieve the guesses from string input
      allMatch = code.equals(guess);
      if(allMatch){
        System.out.println("System:\t"+"You cracked the code!\n");
      }
      else{// call feedback method to compare the code and guess
        feedback = feedback(code, guess);
        //ready to output feedback(String) result
        System.out.println("System:\t"+feedback);
        System.out.println("Guess:"+guess.sizeAndCapa()+"\n");
      }
    }
    kb.close();//close scanner

     if(!allMatch){
      System.out.println("12 attempts, out of lucks");
     }

    System.out.println("\n\t\t***** end of the game *****");
  }//-------------------  end of main  ---------------------


  //todo: ---------------  some methods  --------------------
  /**setCode
   * to set up the code
   * @return ArrayList<Peg> code
   */
  public static ArrayList<Peg> setCode(){
    String[] colours = {"white","black","red","blue","yellow","green"};

    //default capacity
    ArrayList<Peg> code = new ArrayList<>();
    System.out.println("Empty ArrayList: "+code.sizeAndCapa());
    for(int i = 0; i< 4; i++){
      //*random integer = (int)(min + (max - min + 1)*Math.random()) => from 0 to 5
      Peg peg = new Peg(colours[(int)(0 + (5 - 0 + 1)*Math.random())]);
      code.add(peg);
    }
    System.out.println("Code: "+code+"\n"+code.sizeAndCapa());
    return code;
  }

  /**getGuess
   * take the input String line to generate the answer from user
   * @param input
   * @return ArrayList<Peg> guess
   * @throws IllegalArgumentException throw exception if the input format is incorrect
   */
  public static ArrayList<Peg> getGuess(String input)throws IllegalArgumentException{
    ArrayList<Peg> guess = new ArrayList<>();
    //* aasume imput formation: text + space + text...
    String[] tokens = input.split(" ");
    if(tokens.length != 4)throw new IllegalArgumentException("4 colours should have the space between them(e.g. red red red red)");
    for(int i = 0; i < 4; i++){
      Peg peg = new Peg(tokens[i]);
      guess.add(i,peg);
    }
    System.out.println("Guess: "+guess.sizeAndCapa());
    return guess;
  }

  /** feedback
   * compare code with guess give back the result
   * @param code
   * @param guess
   * @return String restul ready for ouput
   */
  public static String feedback(ArrayList<Peg> code, ArrayList<Peg> guess){
    //todo: create a temp arraylist for code, any element match(follow the mesurement), then cross out the mathec element from guess list
    ArrayList<Peg> tempC = new ArrayList<>();
    String feedback ="";
    ArrayList<String> markers = new ArrayList<>();
    for(Peg c: code){
      tempC.add(c);
    }
    for(int i = 0; i<tempC.size();i++){
      if(tempC.get(i).equals(guess.get(i))){
        markers.add("X ");
        tempC.remove(i);
        guess.remove(i);
        i--;// since e has been removed from the list, offset the index
      }
    }

    //* an element from guess vs every element from code
    for(int j = 0; j<guess.size();j++){
      boolean colourFound = false;
      for(int h = 0; h<tempC.size()&&(!colourFound) ;h++){
        if(guess.get(j).equals(tempC.get(h))){
          colourFound = true;
          markers.add("O ");
          guess.remove(j);
          tempC.remove(h);
          j--;// since e has been removed from the list, offset the index
        }
      }
    }

    //* if any unmatched elements, add "-" marker
    for(int k = 0; k<tempC.size();k++){
      markers.add("- ");
    }
    
    //* format the 2x2 presentation
    markers.set(1, markers.get(1)+"\n      \t");
    for(String s: markers){
      feedback += s;
    }
    return feedback;
  }
}
