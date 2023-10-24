package edu.hw2.expressions;

public record Negate(Expr expression) implements Expr {
    public double evaluate() {
        return -1 * expression.evaluate();
    }
}
