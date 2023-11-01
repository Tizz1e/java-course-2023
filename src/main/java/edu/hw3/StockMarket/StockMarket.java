package edu.hw3.StockMarket;

public interface StockMarket {
    void add(Stock stock);

    void remove(Stock stock);

    Stock mostValuableStock();
}
