package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> collection;
    private int cursor;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor != 0;
    }

    @Override
    public T next() {
        cursor--;
        if (cursor < 0) {
            throw new NoSuchElementException("No next element");
        }
        return collection.get(cursor);
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        Iterator.super.forEachRemaining(action);
    }
}
