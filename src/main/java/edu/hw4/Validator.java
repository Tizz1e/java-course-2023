package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class Validator {
    private Validator() {
    }

    public static Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (!isCorrectName(animal)) {
            errors.add(new ValidationError("Incorrect name"));
        }
        if (!isCorrectAge(animal)) {
            errors.add(new ValidationError("Incorrect age"));
        }
        if (!isCorrectHeight(animal)) {
            errors.add(new ValidationError("Incorrect height"));
        }
        if (!isCorrectWeight(animal)) {
            errors.add(new ValidationError("Incorrect weight"));
        }
        return errors;
    }

    public static String incorrectFields(Animal animal) {
        StringBuilder builder = new StringBuilder();
        if (!isCorrectName(animal)) {
            builder.append("Name");
        }
        if (!isCorrectAge(animal)) {
            builder.append("Age");
        }
        if (!isCorrectHeight(animal)) {
            builder.append("Height");
        }
        if (!isCorrectWeight(animal)) {
            builder.append("Weight");
        }
        return builder.toString();
    }

    public static boolean check(Animal animal) {
        return !(isCorrectName(animal) && isCorrectAge(animal) && isCorrectHeight(animal) && isCorrectWeight(animal));
    }

    private static boolean isCorrectName(Animal animal) {
        return animal.name() != null && !animal.name().isEmpty();
    }

    private static boolean isCorrectAge(Animal animal) {
        return animal.age() >= 0;
    }

    private static boolean isCorrectHeight(Animal animal) {
        return animal.height() > 0;
    }

    private static boolean isCorrectWeight(Animal animal) {
        return animal.weight() > 0;
    }
}
