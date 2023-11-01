package edu.hw3.StockMarket;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarketImplementation implements StockMarket {
    private final PriorityQueue<Stock> stocks;

    public StockMarketImplementation() {
        stocks = new PriorityQueue<>(
            Comparator.reverseOrder()
        );
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
