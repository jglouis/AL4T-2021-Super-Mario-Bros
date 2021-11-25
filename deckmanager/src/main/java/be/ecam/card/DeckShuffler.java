package be.ecam.card;

import java.util.List;

public interface DeckShuffler<T> {
    void shuffle(List<T> cards);
}
