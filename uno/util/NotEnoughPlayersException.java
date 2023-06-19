package uno.util;

public class NotEnoughPlayersException extends Exception
{
    public NotEnoughPlayersException(int message)
    {
        super("Not enough players to play the game:" + message);
    }
}