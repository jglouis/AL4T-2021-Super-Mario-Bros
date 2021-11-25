package be.ecam.card;

import org.jetbrains.annotations.NotNull;

public class CardMock implements Comparable<CardMock> {
    private final int value;

    public CardMock(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull CardMock o) {
        return value - o.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
