package uno.util;

import java.util.Objects;

public class Card
{
    public final Color color;
    public final CardType type;
    public final int value;
    
    public Card(Color col, CardType ct, int v)
    {
        this.color = col;
        this.type = ct;
        this.value = v;
    }

    public String toString()
    {   
        if (type == CardType.VALUE)
        {
            return color + " " + value;
        }
        else
        {
            return color + " " + type;
        }
    }    
    public boolean equals(Card b)
    {
        return Objects.hash(this.toString()) == Objects.hash(b.toString());
    }
    public boolean isPlayableOver(Card b)
    {
        if (b.type != CardType.VALUE)
        {
            return b.color == this.color;
        }
        return b.color == this.color || b.value == this.value;
    }


}
