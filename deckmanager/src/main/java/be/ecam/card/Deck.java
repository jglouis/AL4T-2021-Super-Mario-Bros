package be.ecam.card;

import java.util.*;

public class Deck<T extends Comparable<T>> {

    // type erasure
    private final List<T> cards = new ArrayList<>();

    public T draw() throws NoMoreCardException {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoMoreCardException();
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    // add(card1, card2, card3)
    public void add(T... cards) {
        this.cards.addAll(List.of(cards));
    }

    public void sort() {
        Collections.sort(cards);
    }

    @Override
    public String toString() {
        return Arrays.toString(cards.toArray());
    }

    public static class NoMoreCardException extends Exception {
        public NoMoreCardException() {
            super("No mare cards in the deck");
        }
    }
}

