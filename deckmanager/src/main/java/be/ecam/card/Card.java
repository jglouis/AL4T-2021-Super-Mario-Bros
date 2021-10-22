package be.ecam.card;

public class Card implements Comparable<Card> {
    private final int value;
    private final Suit suit;

    public Card(int v, Suit suit) {
        this.value = v;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return String.format("Card{value:%d,suit:%s}", value, suit);
    }

    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }
}
