package be.ecam.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck<CardMock> createDummyDeck() {
        final Deck<CardMock> deck = new Deck<>();
        for (int i = 1; i <= 10; i++) {
            deck.add(new CardMock(i));
        }
        return deck;
    }

    @Test
    public void draw() throws Deck.NoMoreCardException {
        Deck<CardMock> deck = createDummyDeck();
        for (int i = 1; i <= 10; i++) {
            final CardMock card = deck.draw();
            assertEquals(i, card.getValue());
        }
        boolean hasRaisedException = false;
        try {
            deck.draw(); // should raise an exception
        } catch (Deck.NoMoreCardException e) {
            hasRaisedException = true;
        }
        assertTrue(hasRaisedException);
    }

    @Test
    public void shuffle() {
    }

    @Test
    public void add() throws Deck.NoMoreCardException {
        Deck<CardMock> deck = new Deck<>();
        CardMock addedCard = new CardMock(0);
        deck.add(addedCard);
        assertEquals(deck.draw(), addedCard);
    }

    @Test
    public void sort() {
        Deck<CardMock> deck = new Deck<>();
        deck.add(new CardMock(8), new CardMock(5), new CardMock(3), new CardMock(2),
                new CardMock(4), new CardMock(6), new CardMock(7), new CardMock(1),
                new CardMock(9), new CardMock(10));
        deck.sort();
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", deck.toString());
    }

    @Test
    public void testToString() {
        Deck<CardMock> deck = createDummyDeck();
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", deck.toString());
    }
}