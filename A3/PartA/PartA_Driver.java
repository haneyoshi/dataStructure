/*******************************************************
 * Name:        YUSHAN HUNG
 * Course:       ACS-2947
 * Student ID:  3133878
 * Assignment:  Assigment3
 * 
 * Class: PartA_Driver
 ********************************************************/

import java.util.Arrays;
import java.util.Scanner;

public class PartA_Driver {
  public static void main(String[] args){
    System.out.println("\n\n\t***** A3 Part A *****\n");
    //                    A
    //           B                 C
    //       D       E         F       G
    //    H    I   J    K

    //todo: ----------------     todo: Q1     -----------------
    LinkedBinaryTree<String> familyTree = new LinkedBinaryTree<>();
    //* root
    familyTree.addRoot("A");

    //* children of A
    Position<String> cursor = familyTree.root();//A
    familyTree.addLeft(cursor, "B");
    familyTree.addRight(cursor, "C");

    //* children of B
    cursor = familyTree.left(cursor);//B
    familyTree.addLeft(cursor, "D");
    familyTree.addRight(cursor, "E");

    //* children of D
    cursor = familyTree.left(cursor);//D
    familyTree.addLeft(cursor, "H");
    familyTree.addRight(cursor, "I");

    //* children of E
    cursor = familyTree.sibling(cursor);//E
    familyTree.addLeft(cursor, "J");
    familyTree.addRight(cursor, "K");

    //* children of C
    cursor = familyTree.right(familyTree.root());//C
    familyTree.addLeft(cursor, "F");
    familyTree.addRight(cursor, "G");


    System.out.println("\n\n** Q1:");
    System.out.println(familyTree+"\n");



    //todo: ----------------     todo: Q2     -----------------
    System.out.println("\n\n** Q2:");
    LinkedBinaryTree<String> decisionTree = new LinkedBinaryTree<>();
    String[] questions = {"Like science?(yes/no)","Like Mathmetics or data(sorfeware) computing?(yes/no)","Like data science or computing?(yes/no)"};
    String[] suggetions = {"Non-science major","General Chemistry or Biochemistry","Mathematical Physics","Welcome to computer science"};
    
    //* very first question (root)
    Position<String> decision = decisionTree.root();;
    decisionTree.addRoot(questions[0]);
    decision = decisionTree.root();

    for(int k = 1; k<suggetions.length;k++){
      //* left branch
      decisionTree.addLeft(decision, suggetions[k-1]);
      //* right branch
      if(k==suggetions.length-1){
        decisionTree.addRight(decision, suggetions[k]);// no more question
      }
      else{
        decisionTree.addRight(decision, questions[k]);//next question
      }
      decision = decisionTree.right(decision);// move cursor to next
    }
    System.out.println(decisionTree+"\n");

    Scanner query = new Scanner (System.in);
    String answer = "";
    boolean interestFound = false;


    decision =decisionTree.root(); 
    while(!interestFound){
      System.out.println(decision);
      answer = query.next().toLowerCase();
      if(answer.equals("no")){
        decision = decisionTree.left(decision);
        interestFound = true;
      }
      else{
        decision = decisionTree.right(decision);//next quetions
        if(decisionTree.isExternal(decision)){//the external node
          interestFound = true;
        }
      }
    }
    query.close();
    System.out.println("Final Decision: "+ decision +"\n");



    //todo: ----------------     todo: Q3     -----------------
    System.out.println("\n\n** Q3:");
    LinkedBinaryTree<String> arithmeticExpression = new LinkedBinaryTree<>();
    arithmeticExpression.addRoot("-");
    cursor = arithmeticExpression.root();
    arithmeticExpression.addLeft(cursor, "/");
    arithmeticExpression.addRight(cursor, "+");

    //* left branch
    cursor = arithmeticExpression.left(cursor);// cursor =  /
    arithmeticExpression.addLeft(cursor, "*");
    arithmeticExpression.addRight(cursor, "+");

    cursor = arithmeticExpression.left(cursor);// cursor = *
    arithmeticExpression.addLeft(cursor, "+");
    arithmeticExpression.addRight(cursor, "3");

    cursor = arithmeticExpression.left(cursor);// cursor = +
    arithmeticExpression.addLeft(cursor, "3");
    arithmeticExpression.addRight(cursor, "1");

    cursor = arithmeticExpression.left(arithmeticExpression.root());// cursor = /
    cursor = arithmeticExpression.right(cursor);// cursor = +
    arithmeticExpression.addLeft(cursor, "-");
    arithmeticExpression.addRight(cursor, "2");

    cursor = arithmeticExpression.left(cursor);// cursor = -
    arithmeticExpression.addLeft(cursor, "9");
    arithmeticExpression.addRight(cursor, "5");

    //* right branch
    cursor = arithmeticExpression.right(arithmeticExpression.root());// cursor = +
    arithmeticExpression.addLeft(cursor, "*");
    arithmeticExpression.addRight(cursor, "6");

    cursor = arithmeticExpression.left(cursor);// cursor = *
    arithmeticExpression.addLeft(cursor, "3");
    arithmeticExpression.addRight(cursor, "-");

    cursor = arithmeticExpression.right(cursor);// cursor = /
    arithmeticExpression.addLeft(cursor, "7");
    arithmeticExpression.addRight(cursor, "4");


    StringBuffer sb = new StringBuffer();
    System.out.println("Arithmetic Expression tree:");
    for(Position<String> p: arithmeticExpression.postorder()){
      sb.append(p);
    }
  
    String expression = sb.toString();
    ArrayStack<Integer> as = new ArrayStack<>();
    int result = 0;

    for(int i = 0; i < expression.length(); i++){
      char c = expression.charAt(i);
      if(!Character.isDigit(c)){
        int op2 = as.pop();// op1 + op2, op2 will be popped up first
        int op1 = as.pop();
        result = getOperation(c, op1, op2);
        as.push(result);
      }
      else{
        as.push(Integer.parseInt(Character.toString(c)));
      }
    }

    char [] ch = expression.toCharArray();
    System.out.println("Postorder: "+Arrays.toString(ch));
    System.out.println("Tree value: "+result);


    System.out.println("\n\t***** End of Part A *****\n");
  }

  public static int getOperation(char c, int o1, int o2){
    int result = 0;
    switch(c){
      case '+': result = o1 + o2;
      break;
      case '-': result = o1 - o2;
      break;
      case '*': result = o1 * o2;
      break;
      default: result = o1/o2;
    }
    return result;
  }

}
