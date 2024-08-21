package ru.snake.game.Directions;

import org.junit.jupiter.api.Test;
import ru.snake.game.Pointer2D.Pointer2D;

import static org.junit.jupiter.api.Assertions.*;

class DirectionsTest {

    @Test
    void getOpposite_Return_Opposite_Direction() {
        Directions left = Directions.LEFT;
        Directions right = Directions.RIGHT;
        assertEquals(left, right.getOpposite());
    }

    @Test
    void getDirectionByPointer_Return_Correct_Direction() {
        Directions left = Directions.LEFT;
        Pointer2D pointerLeft = new Pointer2D(-1,0);
        assertEquals(left,Directions.getDirectionByPointer(pointerLeft));
    }

    @Test
    void getDirectionByPointer_Return_Null_When_Given_Incorrect_Pointer() {
        Pointer2D pointerLeft = new Pointer2D(-1666,124);
        assertNull(Directions.getDirectionByPointer(pointerLeft));
    }
}