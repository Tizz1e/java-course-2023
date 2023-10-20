package edu.hw2.expressions;

public sealed interface Expr
    permits Constant, Negate, Exponent, Addition, Multiplication {
    double evaluate();
}
