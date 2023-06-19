package uno;
import uno.util.*;
import java.util.*;


public class Game
{
    LinkedList<Card> deck;
    List<Player> players;
    Card currentCard;
    int currentPlayerIdx;
    boolean isForward;
    boolean isOn;
    InputSource inputSource;

    public Game(int numberOfCards, InputSource is, String... vararg) throws NotEnoughPlayersException
    {
        if (vararg.length < 2)
            throw new NotEnoughPlayersException(1);

        players = new ArrayList<Player>();
        initDeck();
        initPlayers(numberOfCards, vararg);
        currentCard = drawCards(1).get(0);
        currentPlayerIdx = 0;
        isForward = true;
        isOn = true;
            
    }

    public List<Player> getPlayers()
    {
        List<Player> newList = new ArrayList<Player>();
        for (Player p : players)
        {
            newList.add(p);
        }
        return newList;
    }

    private void initDeck()
    {
        deck = new LinkedList<Card>();
        for (int i=1;i<=9;i++)
        {
            deck.add(new Card(Color.RED, CardType.VALUE, i));
            deck.add(new Card(Color.BLUE, CardType.VALUE, i));
            deck.add(new Card(Color.GREEN, CardType.VALUE, i));
            deck.add(new Card(Color.YELLOW, CardType.VALUE, i));
        }
        for (int i=0;i<2;i++)
        {
            deck.add(new Card(Color.RED, CardType.SKIP, 0));
            deck.add(new Card(Color.RED, CardType.TAKE, 0));
            deck.add(new Card(Color.RED, CardType.REVERSE, 0));

            deck.add(new Card(Color.BLUE, CardType.SKIP, 0));
            deck.add(new Card(Color.BLUE, CardType.TAKE, 0));
            deck.add(new Card(Color.BLUE, CardType.REVERSE, 0));

            deck.add(new Card(Color.GREEN, CardType.SKIP, 0));
            deck.add(new Card(Color.GREEN, CardType.TAKE, 0));
            deck.add(new Card(Color.GREEN, CardType.REVERSE, 0));

            deck.add(new Card(Color.YELLOW, CardType.SKIP, 0));
            deck.add(new Card(Color.YELLOW, CardType.TAKE, 0));
            deck.add(new Card(Color.YELLOW, CardType.REVERSE, 0));
        }

        if (!inputSource.isInteractive)
        {
            Collections.shuffle(deck, new java.util.Random(12345));
        }
        else{
            Collections.shuffle(deck);
        }
    }
    private void initPlayers(int numberOfCards, String... names)
    {
        for (String p : names)
        {
            players.add(new Player(p, drawCards(numberOfCards), this));
        }
    }
    private List<Card> drawCards(int numberOfCards){
        List<Card> newList = new ArrayList<Card>();
        for (int i=0;i<numberOfCards;i++)
        {
            Card a = deck.removeFirst();
            newList.add(a);
        }
        return newList;
    }

    public void playNext()
    {
        currentPlayerIdx = getNextPlayerIdx();
        interactiveMsg(getCurrentPlayer().toString());
        
        if (currentCard.isPlayableOver(getCurrentPlayer().getHand().get(inputSource.getNextInput())))
        {
            Card a = getCurrentPlayer().getHand().get(inputSource.getNextInput());
            getCurrentPlayer().removeFromHand(inputSource.getNextInput());
            currentCard = a;
            useCardEffect();

        }
        else{
            interactiveMsg("You can't play this card");
            getCurrentPlayer().addToHand(drawCards(1));
        }


        
    }
    public int getNextPlayerIdx()
    {
        int nextplayer = currentPlayerIdx;
        if (isForward)
        {
            nextplayer++;
            if (nextplayer >= players.size())
                nextplayer = 0;
        }
        else{
            nextplayer--;
            if (nextplayer < 0)
                nextplayer = players.size()-1;
        }
        return nextplayer;
    }
    private void currentPlayerDrawCard()
    {
        getCurrentPlayer().addToHand(drawCards(1));
    }
    public Player getCurrentPlayer()
    {
        return players.get(currentPlayerIdx);
    }
    private void interactiveMsg(String player)
    {
        if (inputSource.isInteractive)
            System.out.println(player);
    }
    private void useCardEffect()
    {
        if (currentCard.type == CardType.SKIP)
        {
            currentPlayerIdx = getNextPlayerIdx();
        }
        else if (currentCard.type == CardType.REVERSE)
        {
            isForward = !isForward;
        }
        else if (currentCard.type == CardType.TAKE)
        {
            currentPlayerIdx = getNextPlayerIdx();
            currentPlayerDrawCard();
        }
    }
    public static void main(String[] args) throws NotEnoughPlayersException
    {
        // InputSource is = new InputSource();
        // Game g = new Game(7, is, "Player1", "Player2", "Player3");
        // while (g.isOn)
        // {
        //     g.playNext();
        // }
    }

}