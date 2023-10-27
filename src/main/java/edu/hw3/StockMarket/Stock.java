package edu.hw3.StockMarket;

import org.jetbrains.annotations.NotNull;

public record Stock(int price) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock other) {
        return price - other.price;
    }
}
