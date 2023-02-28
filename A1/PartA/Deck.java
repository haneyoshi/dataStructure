import java.util.ArrayList;
import java.util.Collections;
/*******************************************************
 * Name:        YUSHAN HUNG
 * Class:       ACS-1904
 * Student ID:  3133878
 * Assignment2:  Deck Class
 ********************************************************/
public class Deck
{
    private ArrayList<Card> cards;
    public Deck(){
        // (12*2*4) + 4 = 100
        cards = new ArrayList<>(100);
        createCards();
    }

    private void createCards(){
        //every face has 2 in same colour
        for(Colour c:Colour.values()){
            for(Face f: Face.values()){
                if( c!=Colour.BLACK && f!=Face.WILD ){
                    cards.add( new Card(c,f));
                    cards.add( new Card(c,f));
                }
            }
        }
        for(int i = 1; i <= 4; i++){
            cards.add(new Card(Colour.BLACK,Face.WILD));
        }
    }
    
    public void shuffle(){
        Collections.shuffle(this.cards); 
    }

    public boolean isEmpty(){
        return cards.size() == 0;
    }

    public Card deal(){
        if(isEmpty()){
            return null;
        }
        int top = 0;
            Card card = cards.get(top);
            cards.remove(top);
            return card;
    }
    
    @Override
    public String toString(){
        return "Desk: "+cards.toString() +"\n";
    }
    
}
