/**
* @author  Tyler Weatherby
* @since   2018-12-06
*/

package pokerproject;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    /**
     * The deck will store Cards in an ArrayList.
     */
    protected ArrayList<Card> deck = new ArrayList<Card>();
    /**
     * This constructor will generate a standard deck of 52 cards.
     * @param args Unused
     * @return Nothing.
     */    
    Deck()
    {
        int x = 0;
        int y = 0;
        while (x < 4)
        {
            y = 0;
            while (y < 13)
            {
                deck.add(new Card(y, x));
                y++;
            }
            x++;
        }
        shuffleDeck();
    }
    
    /**
     * This method returns a card from the deck at a particular index.
     * @param card This is the index of the card to return.
     * @return Card This returns the card.
     */    
    public Card getCard(int card)
    {
        return deck.get(card);
    }
    /**
     * This method simply randomizes the ArrayList of Cards.
     * @param args Unused
     * @return Nothing.
     */    
    private void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    /**
     * This method returns the entire deck ArrayList as a string.
     * @param args Unused
     * @return Nothing.
     */   
    private String showDeck()
    {
        return deck.toString();
    }
    
}
