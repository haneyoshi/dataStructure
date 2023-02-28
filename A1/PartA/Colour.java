
/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1:  Colour class
 ********************************************************/
public enum Colour
{
    BLUE("Blue"),RED("Red"),
    GREEN("Green"),YELLOW("Yellow"),BLACK("Black");
    private String colour;
    private Colour(String colour){
    this.colour = colour;
    }
    //getter
    public String getcolour(){
        return this.colour;
    }
    
    //toString
    public String toString(){
        return this.colour;
    }
}
