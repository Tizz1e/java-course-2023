package edu.hw2.expressions;

public record Constant(double value) implements Expr {
    public double evaluate() {
        return value;
    }
}
