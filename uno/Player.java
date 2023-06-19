package uno;
import uno.util.*;
import java.util.*;

public class Player{

    private String name;
    private List<Card> hand;
    private Game game;

    public List<Card> getHand()
    {
        List<Card> newList = new ArrayList<Card>();
        for (Card c : hand)
        {
            newList.add(c);
        }
        return newList;
    }

    public String getName()
    {
        return name;
    }

    public Player(String name, List<Card> hand, Game game)
    {
        this.name = name;
        this.hand = hand;
        this.game = game;
    }

    public void addToHand(List<Card> cards)
    {
        for (Card c : cards)
        {
            hand.add(c);
        }
    }
    public void removeFromHand(int number)
    {
        hand.remove(number);
    }

    public String toString()
    {
        String out = "Player " + name+":";
        int i = 1;
        for (Card c : hand)
        {
            if (game.currentCard.isPlayableOver(c))
            {
                out+=" "+i+"*="+c.toString();
            }
            else
            {
                out+=" "+i+"="+c.toString();
            }
            i++;
        }
        return out;
    }
}