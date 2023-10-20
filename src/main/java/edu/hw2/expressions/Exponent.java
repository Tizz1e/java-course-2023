package edu.hw2.expressions;

public record Exponent(Expr expression1, int power) implements Expr {
    public double evaluate() {
        return Math.pow(expression1.evaluate(), power);
    }
}
