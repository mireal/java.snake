package ru.snake.game.Directions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionsTest {

    @Test
    void getOpposite() {
        Directions left = Directions.LEFT;
        Directions right = Directions.RIGHT;
        assertEquals(left, right.getOpposite());
    }
}