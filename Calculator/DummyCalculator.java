

public class DummyCalculator {
  
  private ArrayStack<Integer> traces;
  private ArrayStack<Integer> backup;
  public DummyCalculator(){
    //default capacity for both are 1000
    traces = new ArrayStack<>();
    backup = new ArrayStack<>();
  }

  public DummyCalculator(int capacity){
    traces = new ArrayStack<>(capacity);
    backup = new ArrayStack<>(capacity);
  }

  /**
   * return the last elemet(top on the stack),
   *  but not remove from stack
   * use for calculating 
   * @return  int
   */
  public int result(){
    return traces.top();
  }

  public boolean isTraceEmpty(){
    return traces.isEmpty();
  }

  /**
   * once triger undo, store the element to backup stack
   * can redo putback later.
   * no need to throw a stack full exception(already setup in ArrayStack class)
   */
  public void undo(){
    String message = "";
    if(traces.isEmpty()){
      message = "Nothing to undo.";
    }
    else{int n = traces.pop();
    backup.push(n);
    if(traces.isEmpty()){//another inner if
      message = "UNDO: "+ traces.top();}
      else{
        message = "Nothing to undo.";//adter pop last element out
      }
    }
    System.out.println(message);
  }

  /**
   * putback the element done because of undo
   * !note: this backup stack will be reset once an operation is implement
   */
  public void redo(){
    String message = "";
    if(backup.isEmpty()){
      message = "Nothing to redo.";
    }
    else {
      int n = backup.pop();
      traces.push(n);
      message = "REDO: "+ traces.top();
     }
     System.out.println(message);
  }

  
  
  //todo: input validation & calculation method below
  //! except this receiving input method is public, all other validation and calculation method are set private to prevent from accessing 
  public void feedInput(String input){
    if(traces.isEmpty()){
      int firstNum =firstNum(input);//check if is an integer input
      traces.push(firstNum);
    }
    else{
      // check valid input, expected operator + space + operant
      int result = nextNum(input);
      System.out.println("= "+ result);
      traces.push(result);
      //* every time deos an operation, dump the redo stacks
      this.backup = new ArrayStack<>();
    }
  }

  /**
   * this method only applys first number(when ArrayStack is empty)
   * @param input string input from user
   * @return integer if input valid
   * @throws IllegalArgumentException
   */
  private int firstNum(String input)throws IllegalArgumentException{
    for(int i = 0; i < input.length() ; i++){
      char c = input.charAt(i);
      if(!Character.isDigit(c))throw new IllegalArgumentException("Not digit character found!");
    }
    return(Integer.parseInt(input));
  }

  /**
   * 
   * @param input passed from feedInput public method
   * @return int result after calucalation to main method(feedInput)
   * @throws IllegalArgumentException if any invalid input is found
   */
  private int nextNum(String input)throws IllegalArgumentException{

    String[] st = input.split(" ");

    //*check format
    if(st.length != 2)throw new IllegalArgumentException("invalid format, expected: operator + space + integer");
    
    //*check operator
    String o = st[0];
    if(!o.equals("*") && !o.equals("/") && !o.equals("+") && !o.equals("-"))throw new IllegalArgumentException("invalide operator");

    //*check integer
    String num = st[1];
    for(int i = 0; i < num.length();i++){
      char c = num.charAt(i);
      if(!Character.isDigit(c))throw new IllegalArgumentException("invalide integer");
    }
    int number = Integer.parseInt(num);

    //if input is valid, start calculating
    //return the result of operation
    return calculation(o,number);
  }

  /**
   * this method does the calculation job based on different case of operators
   * @param operator 
   * @param number the input integer
   * @return the result
   */
  private int calculation(String operator, int number){
    int result = result();//get current result
    switch(operator){
      case "+": result = result + number;
      break;
      case "-" : result = result - number;
      break;
      case "*" : result = result * number;
      break;
      default : result = result / number;
      break;
    }
    return result;
  }

  /**
   * public method be accessed by this class and driver class
   * simply output the message
   */
  public void prompt(){
    if(traces.isEmpty()){
      System.out.println("Enter the first number:");
    }
    else{
      System.out.println("Enter the next operation on "+ result()
      + "\n(expected: operator + space + operant)");
    }
  }

  //toString method
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Current Undoable value list: "+traces.toString());
    sb.append("\n\n");
    sb.append("Current Redoable value list: "+backup.toString());
    return sb.toString();
  }
}



