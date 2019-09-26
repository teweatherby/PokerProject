/**
 * @author  Tyler Weatherby
 * @since   2018-12-06
 */

package pokerproject;
import java.lang.String;

public class Card {
    
    // Each card will keep track of its own rank and suit.
    private int rank = 0;
    private int suit = 0;
    
    /**
     * I created two String arrays of the suits and ranks. The suits order
     * did not matter, really. However, the ranks ordered mattered. They're
     * ordered from lowest rank at index 0 to the highest ranked (ace) at
     * index 12. I chose this method to give a numeric value to T, J, Q, K,
     * and A both for scoring and sorting purposes within the hand.
     */
    private final static String[] suits = { "H", "S", "D", "C" };
    private final static String[] ranks  = { "2", "3", "4", "5", "6", "7", 
                        "8", "9", "T", "J", "Q", "K", "A" };
    
   /**
    * This constructor is used to create a Card object.
    * @param rank This is the first parameter defined by values 0 to 12
    * @param suit This is the second parameter from 0 to 3
    * @return Nothing.
    */
    Card(int rank, int suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
   /**
    * This() method is used to return the ranks and suit of the current
    * card object as a String.
    * @return String This returns both the rank and suit of the given Card
    * object as a String ex. (AH or 3D)
    */    
    public String toString()
    {
        return ranks[rank] + suits[suit];
    }
    
   /**
    * This method is used to return the rank of the card as an integer.
    * @param args Unused.
    * @return int This returns the rank of the given object Card as a
    * numeric value.
    */    
    public int getRank()
    {
        return rank;
    }
    
   /**
    * This method is used to return the suit of the card as an integer.
    * @param args Unused.
    * @return int This returns the suit of the given object Card as a 
    * numeric value.
    */    
    public int getSuit()
    {
        return suit;
    }
}
