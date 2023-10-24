package edu.hw2;

import edu.hw2.expressions.Addition;
import edu.hw2.expressions.Constant;
import edu.hw2.expressions.Exponent;
import edu.hw2.expressions.Multiplication;
import edu.hw2.expressions.Negate;
import edu.hw2.remoteServer.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExpressionsTest {
    @Test
    @DisplayName("Expressions calculator #1")
    public void first() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37.0);
    }

    @Test
    @DisplayName("Expressions calculator #2")
    public void second() {
        var five = new Constant(5);
        var negativeFive = new Negate(five);
        var mult = new Multiplication(five, negativeFive);
        var res = new Exponent(mult, 2);

        assertThat(res.evaluate()).isEqualTo(625.0);
    }

    @Test
    @DisplayName("Expressions calculator #3")
    public void third() {
        var one = new Constant(1);
        var negOne = new Constant(-1);
        var zero = new Addition(one, negOne);
        var res = new Exponent(zero, 128);
        assertThat(res.evaluate()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Expressions calculator #4")
    public void fourth() {
        var one = new Constant(1);
        var negOne = new Constant(-1);
        var res = new Addition(one, negOne);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                res = new Addition(res, one);
            } else {
                res = new Addition(res, negOne);
            }
        }
        assertThat(res.evaluate()).isEqualTo(0.0);
    }

}
