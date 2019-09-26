/**
* @author  Tyler Weatherby
* @since   2018-12-06
*/

package pokerproject;
import java.util.ArrayList;
import static pokerproject.PokerGame.*;

public class PokerProject {

    public static void main(String[] args) {
        /*
        System.out.println("Sample test cases...");
        Player Black = new Player("Black");
        Player White = new Player("White");
 
        White.addCard(0, 3);
        White.addCard(1, 0);
        White.addCard(2, 1);
        White.addCard(6, 3);
        White.addCard(12, 0);        
        
        Black.addCard(3, 0);
        Black.addCard(10, 2);
        Black.addCard(10, 1);
        Black.addCard(12, 3);
        Black.addCard(12, 2);
        
        System.out.println("White: " + White.showHand());
        System.out.println("Black: " + Black.showHand());
        */
        int counter = 0;
        int checkRule = 9;
        while (counter < 20 )
        {
            System.out.println("Sample random run");
            PokerGame game = new PokerGame();
            game.addPlayer("Black");
            game.addPlayer("White");
            game.deal();
            game.showHands();
            System.out.println(game.rateHands());
            counter++;
            /*
            if(evalHand(game.players.get(0)) == checkRule ||
                    evalHand(game.players.get(1)) == checkRule)
            {
                counter = 11;
            }
            */
        }
    } 
}