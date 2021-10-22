package be.ecam;

import be.ecam.builder.StringBuilderScenario;
import be.ecam.card.Card;
import be.ecam.card.Deck;
import be.ecam.card.Suit;
import be.ecam.closure.ClosureKt;
import be.ecam.lambda.NaiveClosure;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final Card card1 = new Card(1, Suit.SPADE);
        Card card2 = new Card(2, Suit.SPADE);

        System.out.println(card1);

        List<Card> array = new ArrayList<>();
        array.add(card1);
        array.add(card2);

        Deck<Card> deck = new Deck<>();
        deck.add(card1);
        deck.add(card2);

        deck.sort();

        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        deck.sort();
        System.out.println(deck);
        Card drawnCard = null;
        try {
            drawnCard = deck.draw();
            drawnCard.getSuit();
        } catch (Deck.NoMoreCardException e) {
            System.out.println(e.getMessage());
        }

        new StringBuilderScenario().run();


        Runnable counter = NaiveClosure.getCounter();
        counter.run();
        counter.run();
        counter.run();
        counter.run();

        Function0<Unit> counter1 = ClosureKt.getCounter();
        counter1.invoke();

    }

}
