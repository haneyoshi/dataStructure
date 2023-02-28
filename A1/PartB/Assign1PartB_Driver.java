import java.util.Scanner;

/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1: PartB Driver Class 
 ********************************************************/
public class Assign1PartB_Driver {
  public static void main(String[] args){
    System.out.println("\n\n\t\t***** program STARTS *****\n\n");

    DummyCalculator dummy = new DummyCalculator();
    Scanner kb = new Scanner(System.in);
    System.out.println("\nSimple Calculator: type z to undo, y to redo, q to quit\n");

    dummy.prompt();//prompt first input
    String input = kb.nextLine().toLowerCase();

    //as long as the input is not "q"
    while(!input.equals("q")){
      if(input.equals("z")){
        dummy.undo();
      }
      else if(input.equals("y")){
        dummy.redo();
      }
      else{
        //default, send input to calculator class. which will check the validation and then does the calculation
        dummy.feedInput(input);
      }
      dummy.prompt();//prompt next input
      input = kb.nextLine().toLowerCase();//all inputs are casted to low case
    }    
    kb.close();

    System.out.println("\n\n\t ***** End of the program ***** \n\n");
  }

}
