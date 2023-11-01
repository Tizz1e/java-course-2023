package edu.hw4;

import org.jetbrains.annotations.NotNull;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) implements Comparable<Animal> {
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER;

    }

    enum Sex {
        M, F;

    }

    @SuppressWarnings("MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    @Override
    public int compareTo(@NotNull Animal o) {
        if (type() != o.type()) {
            return type().compareTo(o.type());
        } else if (sex() != o.sex()) {
            return sex().compareTo(o.sex());
        } else {
            return name().compareTo(o.name());
        }
    }
}
