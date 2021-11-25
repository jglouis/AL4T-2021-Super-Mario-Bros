package be.ecam.card;

import java.util.Collections;
import java.util.List;

public class DeckShufflerImpl<T> implements DeckShuffler<T> {
    @Override
    public void shuffle(List<T> cards) {
        Collections.shuffle(cards);
    }
}
