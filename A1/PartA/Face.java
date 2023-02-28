
/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1:  Face Class
 ********************************************************/
public enum Face
{
    ZERO("0"),ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),NIGHT("9"),SKIP("Skip"),REVERSE("Reverse"),WILD("Wild");
    private String faceLetter;
    private Face(String faceLetter){
        this.faceLetter = faceLetter;
    }
    //getter
    public String getFaceLetter(){
        return faceLetter;
    }
    
    //toString
    public String toString(){
        return this.faceLetter;
    }
}
