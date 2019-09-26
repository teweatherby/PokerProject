/**
 * @author  Tyler Weatherby
 * @since   2018-12-06
 */

package pokerproject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Player {
    /** 
     * Each player will have a "hand" implemented as an ArrayList that holds
     * Card objects.
     */
    protected ArrayList<Card> hand = new ArrayList<Card>();
    // Each player will have a name.
    private String name = new String("Nothing");
    /** 
     * Each player will have a primary and secondary score to determine
     * the winner of each game.
     */
    private int primaryScore = 0;
    private int secondaryScore = 0;
    
    /**
     * This constructor is used to create a Player object.
     * @param name This constructor initializes the name of the player.
     * @return Nothing.
     */    
    Player(String name)
    {
        this.name = name;
    }
    /**
     * This method is used to add a card to the player's hand.
     * @param rank This is the first parameter defined by values 0 to 12.
     * @param suit This is the second parameter from 0 to 3.
     * @return Nothing.
     */    
    protected void addCard(int rank, int suit)
    {
        hand.add(new Card(rank, suit));
    }
    /**
     * This method is used to add a card to the player's hand.
     * @param newCard This method overrides the previous implementation incase
     * we need to pass the card into the players hand as an object.
     * @return Nothing.
     */   
    protected void addCard(Card newCard)
    {
        hand.add(new Card(newCard.getRank(), newCard.getSuit()));
    }
    /**
     * This method takes in the players score as an int and saves
     * it to the object.
     * @param score The primary score to be set.
     * @return Nothing.
     */   
    protected void setScore(int score)
    {
        this.primaryScore = score;
    }
    /**
     * This method takes in the players secondary score as an int and saves
     * it to the object.
     * @param secScore The secondary score to be set.
     * @return Nothing.
     */   
    protected void setSecScore(int secScore)
    {
        this.secondaryScore = secScore;
    }
    /**
     * This method simply clears the Player's ArrayList of cards.
     * @param args unused.
     * @return Nothing.
     */    
    protected void clearHand()
    {
        hand.clear();
    }
    /**
     * This method returns the suit of the needed card as an integer value.
     * @param x This parameter should be the index of the card's suit 
     * we need to return.
     * @return int This returns the integer value of the card's suit.
     */    
    public int getSuit(int x)
    {
        int suit = hand.get(x).getSuit();
        return suit;
    }
    /**
     * This method returns the rank of the needed card as an integer value.
     * @param x This parameter should be the index of the card's rank
     * we need to return.
     * @return int This returns the integer value of the card's rank.
     */
    public int getRank(int x)
    {
        int rank = hand.get(x).getRank();
        return rank;
    }
    /**
     * This method returns the particular card as a String.
     * @param x This parameter should be the index of the card we
     * want to return.
     * @return String This returns a string of the card's rank and suit.
     */
    public String getCard(int x)
    {
        return hand.get(x).toString();
    }
    /**
     * This method returns the entire hand as a string.
     * @param args Unused.
     * @return ArrayList The ArrayList will return its elements as a String.
     */
    public String showHand()
    {
        return hand.toString();
    }
    /**
     * This method will return the player's name.
     * @param args Unused.
     * @return String This will return the objects name.
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * This method simply returns the player's primary score as an
     * integer value. 
     * @param args Unused.
     * @return int This will return the player's primary score.
     */
    public int getScore()
    {
        return primaryScore;
    }
    /**
     * This method simply returns the player's secondary score as an
     * integer value.
     * @param args Unused.
     * @return int This will return the player's secondary score.
     */
    public int getSecScore()
    {
        return secondaryScore;
    }
    /**
     * This method will sort the Player's ArrayList named hand.
     * @param args Unused.
     * @return Nothing.
     */
    public void sortCards()
    {
        Collections.sort(hand, new CustomCompare());
    }
}
    /**
     * This override was written to sort players cards in ascending order.
     * @param hand This is the ArrayList to be sorted.
     * @param CustomeCompare This is the Comparator object to be created.
     * @return Unused.
     */
class CustomCompare implements Comparator<Card>
{
    @Override
    public int compare(Card o1, Card o2)
    {
        if (o1.getRank() < o2.getRank())
        {
            return -1;
        }
        else if (o1.getRank() > o2.getRank())
        {
            return 1;
        }
        return 0;
    }
}