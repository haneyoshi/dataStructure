/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-2947
 * Student ID:  3133878
 * Assignment1: Card Class 
 ********************************************************/
public class Card implements Playable
{
    private Colour colour;
    private Face face;

    //this feild allow program to check whether it's regular card or special function card
    public boolean special;

    //default constructor
    public Card(){
        colour=null;
        face =null;
    }

    //args constructor
    //action card: special = true
    public Card (Colour c, Face f){
        this.colour = c;
        this.face = f;
        if(face!= Face.REVERSE && face!=Face.WILD && face!=Face.SKIP){
            special = false;
        }
        else{
            special = true;
        }
    
    }
    
    //getter
    public Colour getcColour(){
        return colour;
    }
    public Face getFace(){
        return face;
    }
    public String getCard(){
        return this.colour+": "+this.face;
    }

    //* equal, two card are equal only when all feilds match
    public boolean equals(Card c){
        if(!this.colour.equals(c.colour))return false;
        if(!this.face.equals(c.face))return false;
        return true;
    }
    

    @Override
    public boolean playable(Card card){
        if(this.colour.getcolour().equals(card.colour.getcolour()) || this.face.getFaceLetter().equals(card.face.getFaceLetter())){return true;}
        return false;
    }
    
    //toString
    @Override
    public String toString(){
        return getCard();
    }
}
