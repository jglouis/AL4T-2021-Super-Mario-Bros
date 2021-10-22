package be.ecam.stream;

import be.ecam.card.Card;
import be.ecam.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class StreamScenario implements Runnable {

    @Override
    public void run() {
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }

        int sum = cards.stream()
                .filter(card -> card.getSuit().equals(Suit.HEART))
                .mapToInt(Card::getValue)
                .sum();
        System.out.println(sum);

    }
}
