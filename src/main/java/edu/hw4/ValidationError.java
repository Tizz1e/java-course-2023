package edu.hw4;

public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ValidationError{" + getMessage() + "}";
    }
}
