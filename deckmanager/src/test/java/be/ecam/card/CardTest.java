package be.ecam.card;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    final Card card = new Card(1, Suit.SPADE);
    final Card card2 = new Card(2, Suit.SPADE);
    final Card card3 = new Card(1, Suit.SPADE);

    @Test
    public void getValue() {
        assertEquals(1, card.getValue());
    }

    @Test
    public void getSuit() {
        assertEquals(Suit.SPADE, card.getSuit());
    }

    @Test
    public void testToString() {
        assertEquals("Card{value:1,suit:SPADE}", card.toString());
    }

    @Test
    public void compareTo() {
        assertEquals(0, card.compareTo(card3));
        assertEquals(-1, card.compareTo(card2));
        assertEquals(1, card2.compareTo(card));
    }
}