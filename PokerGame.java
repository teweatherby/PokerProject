/**
* @author  Tyler Weatherby
* @since   2018-12-06
*/

package pokerproject;
import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class PokerGame {
    /*    
    Index: 0, 1, 2, 3
    Suits: H, S, D, C

    Index:   0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    Cards:   2, 3, 4, 5, 6, 7, 8, 9, T, J,  Q,  K,  A
    */
    
    // This ArrayList will hold Player objects
    protected ArrayList<Player> players = new ArrayList<Player>();
    // We create a new deck object for use within this class
    protected Deck deck = new Deck();
    /* These rankings are stored in a static, final String array.
       The lowest scoring is at index 0 and the highest is at 9. */
    protected final static String[] handRankings  = { "High Card", "Pair", 
            "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House", 
            "Four of a Kind", "Straight Flush", "Royal Flush"};
    /* If we choose this implementation of the game, we need to track the
       deck's index. */
    private int deckIndex = 0;
    
    /**
     * This method adds a used to the "game" and ArrayList. The String we pass
     * into the method assigns the Player's name.
     * @param name The name of the player to be added.
     * @return Nothing
     */
    protected void addPlayer(String name)
    {
        players.add(new Player(name));
    }
    
    public void deal()
    {
        int counter = 0;
        while(counter < players.size())
        {
            while (players.get(counter).hand.size() < 5)
            {
                players.get(counter).addCard(deck.getCard(deckIndex));
                deckIndex++;
            }
            players.get(counter).sortCards();
            counter++;
        }
    }
    /**
     * This method will iterate through the ArrayList of Players and println
     * their hands.
     * @param args Unused.
     * @return Nothing.
     */
    public void showHands()
    {
        int counter = 0;
        while(counter < players.size())
        {
            System.out.println(players.get(counter).getName() + players.get(counter).showHand());
            counter++;
        }
    }
    /**
     * This method iterates though the ArrayList of Players and scores them.
     * @param args Unused.
     * @return String Will return the name and type of hand the player
     * won with.
     */
    public String rateHands()
    {   
        int counter = 0;
        while (counter < players.size())
        {
            int score = evalHand(players.get(counter));
            int secScore = players.get(counter).getRank(4);
            players.get(counter).setScore(score);
            counter++;
        }
        sortPlayers();

        if(players.get(0).getScore() > players.get(1).getScore())
        {
            return players.get(0).getName() + " wins with " + 
                    handRankings[players.get(0).getScore()];
        }
        else if(players.get(0).getScore() < players.get(1).getScore())
        {
            return players.get(1).getName() + " wins with " + 
                    handRankings[players.get(1).getScore()];
        }
        else
        {
            return "There is a tie with a " + handRankings[players.get(0).getScore()];
            /*
            if(players.get(0).getSecScore() > players.get(1).getSecScore())
            {
                return "There are players tied with " + 
                    handRankings[players.get(0).getScore()] + "\n" +
                    players.get(0).getName() + " wins with highest card/rank: ";
            }
            else if(players.get(0).getSecScore() < players.get(1).getSecScore())
            {
                return "There are players tied with " + 
                    handRankings[players.get(1).getScore()] + "\n" +
                    players.get(1).getName() + " wins with highest card/rank: " + 
                    players.get(1).getRank(players.get(1).getSecScore());
            }
            else
            {
                return "Really, a tie";
            }
            */        
        }
    }
            /**
             * This is the only portion of the code that I really had trouble
             * trying to figure out how I'll implement the logic to. Handling
             * a tie between each hand seems tricky, especially for n players.
             * I bet there's an easier approach to solving this problem though.
             * 
             * I tried keeping track of both a primary score (for the hand type)
             * and a secondary score (for, say, the highest card), but I
             * couldn't really figure out how to adequately utilize this.
             * 
             * I plan to finish this on my own time regardless. I tried a few
             * different ways, but in the end my allotted time has run out.
             */    
    public String handleTie()
    {
        boolean tie = true;
        int compare = players.get(0).hand.size() -1;
        while(tie == true)
        {
            if(players.get(0).getRank(compare) == players.get(1).getRank(compare))
            {
                compare--;
                if(compare == 0)
                {
                    return "Something was not handled.";
                }
            }
            else if(players.get(0).getRank(compare) > players.get(1).getRank(compare))
            {
                players.get(0).setSecScore(players.get(0).getRank(compare));
                return "Next highest card was " + players.get(0).getName() + 
                       " with " + players.get(0).getCard(compare);
            }
            else
            {
                players.get(1).setSecScore(players.get(1).getRank(compare));
                return "Next highest card was " + players.get(1).getName() + 
                       " with " + players.get(1).getCard(compare);
            }
        }
        return "Something was not handled";
    }
    
    
    /**
     * This method will sort the players in the ArrayList descending by score.
     * Therefore the player with the highest score will be in the
     * index 0 position.
     * @param args Unused.
     * @return Nothing.
     */    
    public void sortPlayers()
    {
        Collections.sort(players, new comparePlayers());
    }
    
    /**
     * This method takes in the Player whose hand is to be evaluated. They are
     * then assigned an integer value, which can reference the static array of
     * Poker Hand Rankings.
     * @param x This is the player whose hand is to be evaluated.
     * @return int Returns an integer value that refers to what hand a player
     * won with.
     */
    public static int evalHand(Player x)
    {
        if(isRoyalFlush(x) == true)
        {
            return 9;
        }
        else if(isStraightFlush(x) == true)
        {
            return 8;
        }
        else if(isFourPair(x) == true)
        {
            return 7;
        }
        else if(isFullHouse(x) == true)
        {
            return 6;
        }
        else if(isFlush(x) == true)
        {
            return 5;
        }
        else if(isStraight(x) == true)
        {
            return 4;
        }
        else if(isThreePair(x) == true)
        {
            return 3;
        }
        else if(isTwoPair(x) == true)
        {
            return 2;
        }
        else if(isPair(x) == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * This method looks at the first card, which when sorted, should be a 10
     * (which is at index 8) if this condition is to be true. If 10 is not the 
     * first card, this condition is false.
     * Then it evaluates if it's a straight flush. 
     * If true, this condition will be true.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if all cards are of same rank 
     * and cards are sequential from T, J, Q, K, A.
     */    
    public static boolean isRoyalFlush(Player x)
    {
        if(x.getRank(0) != 8)
        {
            return false;
        }
        else
        {
            return isStraightFlush(x);
        }
    }
    
    /**
     * This method was easy to implement, it utilizes isFlush and isStraight to
     * determine whether to return true. If both return true, then this method
     * will return true.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if the card's ranks are sequential
     * and the card's suit are all the same
     */
    public static boolean isStraightFlush(Player x)
    {
        if(isFlush(x) == false)
        {
            return false;
        }
        if(isStraight(x) == false)
        {
            return false;
        }
        return true;
    }
    
    /**
     * This method uses findPairs to determine if there are four cards with
     * the same rank
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if there are four cards with the same
     * rank.
     */
    public static boolean isFourPair(Player x)
    {
        return findPairs(x, 4, 0);
    }
    
    /**
     * This method also utilizes findPairs to determine if the hand has ranks in
     * the set of 3-2 or 2-3.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if a pair of 3-2 or 2-3 were found.
     */
    public static boolean isFullHouse(Player x)
    {
        if(findPairs(x, 3, 0, 2) == true)
        {
            return(findPairs(x, 2, 3, 4));
        }
        else if(findPairs(x, 2, 0, 1) == true)
        {
            return(findPairs(x, 3, 2, 4));
        }
        else
        {
            return false;
        } 
    }
    
    /**
     * This method simply iterates through the hand to determine if all cards
     * are of the same rank.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if all cards have same suit.
     */
    public static boolean isFlush(Player x)
    {   
        int counter = 1;
        int zeroCardSuit = x.getSuit(0);
        while(counter < x.hand.size())
        {
            if (zeroCardSuit == x.getSuit(counter))
            {
                counter++;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * This method iterates through the hand, comparing to the next card, to
     * determine if its value is n + 1.
     * I believe a recursive function could be used here.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if all five cards are sequential in rank.
     */
    public static boolean isStraight(Player x)
    {
        int counter = 0;
        int zeroCardRank = x.getRank(0);
        while(counter < x.hand.size())
        {
            if (zeroCardRank == x.getRank(counter))
            {
                counter++;
                zeroCardRank++;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * This method uses findPairs to determine if the hand has three of a kind.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if three of a kind were found.
     */
    public static boolean isThreePair(Player x)
    {
        return findPairs(x, 3, 0);
    }
    
    /**
     * This method uses findPairs to locate a two pairs of two cards
     * inside the hand.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if two pairs were found.
     */
    public static boolean isTwoPair(Player x)
    {
        if(isPair(x) == false)
        {
            return false;
        }
        else if(isThreePair(x) == true)
        {
            return false;
        }
        else if(isFourPair(x) == true)
        {
            return false;
        }
        else
        {
            if(findPairs(x, 2, 0, 2) == true && findPairs(x, 2, 2) == true)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
         
    /**
     * This method uses findPairs to locate a Pair of two cards inside the hand.
     * @param x This is the player whose hand is to be evaluated.
     * @return boolean Returns true if the pair was found.
     */
    public static boolean isPair(Player x)
    {
        return findPairs(x, 2, 0);
    }
    
    /**
     * This method finds cards inside the hand with duplicate ranks.
     * @param x This is the player where we want to find pairs.
     * @param pairNum This is the number of a kind we want to find (2, 3, 4)
     * @param indexStart This is where we want to start looking inside the hand.
     * @return boolean Returns true if the pairs were found.
     */       
    private static boolean findPairs(Player x, int pairNum, 
            int indexStart)
    {
        int counter = indexStart;
        int pairCount = 1;
        while(counter < x.hand.size() - 1)
        {
            if(x.getRank(counter) == x.getRank(counter + 1))
            {
                pairCount++;
                if(pairNum == pairCount)
                {
                    return true;
                }
            }
            else
            {
                pairCount = 1;
            }
            counter++;
        }
        return false;
    }
    /**
     * This method is an overload with where we can define what index to end
     * with as well. This is useful if we need to stop looking mid-hand.
     * @param x This is the player where we want to find pairs.
     * @param pairNum This is the number of a kind we want to find (2, 3, 4)
     * @param indexStart This is where we want to start looking inside the hand.
     * @param indexEnd This is where we want to stop looking inside the hand.
     * @return boolean Returns true if the pairs were found.
     */
        private static boolean findPairs(Player x, int pairNum, 
            int indexStart, int indexEnd)
    {
        int counter = indexStart;
        int pairCount = 1;
        while(counter < indexEnd)
        {
            if(x.getRank(counter) == x.getRank(counter + 1))
            {
                pairCount++;
                if(pairNum == pairCount)
                {
                    return true;
                }
            }
            else
            {
                pairCount = 1;
            }
            counter++;
        }
        return false;
    }
    
    /**
     * This method returns the particular card as a String.
     * @param args Unused
     * @return int .
     */   
   public static int highestCard(Player x)
   {   
       x.setSecScore(x.getRank(x.hand.size()));
       return x.getRank(x.hand.size());
   }
}
    /**
     * This implementation compares players scores and organizes them in
     * descending order.
     * @param args Unused
     * @return Nothing.
     */   
class comparePlayers implements Comparator<Player>
{
    @Override
    public int compare(Player o1, Player o2)
    {
        if (o1.getScore() > o2.getScore())
        {
            return -1;
        }
        else if (o1.getScore() < o2.getScore())
        {
            return 1;
        }
        return 0;
    }
}
