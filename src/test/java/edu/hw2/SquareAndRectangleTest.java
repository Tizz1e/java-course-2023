package edu.hw2;

import edu.hw2.squareAndRectangle.Rectangle;
import edu.hw2.squareAndRectangle.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SquareAndRectangleTest {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Square and Rectangle #1")
    void first(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Square and Rectangle #2")
    void second(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(20);
        assertThat(rect.area()).isEqualTo(400.0);
        rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }
}
