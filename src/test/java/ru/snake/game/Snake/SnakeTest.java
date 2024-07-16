package ru.snake.game.Snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.snake.game.Directions.Directions;
import ru.snake.game.Pointer2D.Pointer2D;


import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    Pointer2D[] initialArray;
    SnakeInterface snake;


    @BeforeEach
    void setUp() {
        initialArray = new Pointer2D[]{new Pointer2D(1, 1), new Pointer2D(2, 1), new Pointer2D(3, 1)};
        snake = new Snake(initialArray);
    }

    @Test
    void getLength_Returns_Correct_Length_After_Initialization() {
        assertEquals(initialArray.length, snake.getLength());
    }

    @Test
    void getLength_Returns_Correct_Length_After_Grow() {
        snake.grow();
        snake.grow();
        assertEquals(initialArray.length + 2, snake.getLength());
    }

    @Test
    void getLength_Returns_Same_Length_After_Move() {
        int initialLength = snake.getLength();
        snake.move();
        assertEquals(initialLength, snake.getLength());
    }

    @Test
    void getDirection_Returns_Correct_Direction_After_Initialization() {
        assertEquals(snake.getDirection(), Directions.RIGHT);
    }

    @Test
    void setDirection_Can_Change_To_Up_When_Set_Right() {
        assertTrue(snake.setDirection(Directions.UP));
        assertEquals(Directions.UP, snake.getDirection());
    }

    @Test
    void setDirection_Cant_Change_To_Left_When_Set_To_Right() {
        assertFalse(snake.setDirection(Directions.LEFT));
        assertEquals(Directions.RIGHT, snake.getDirection());
    }

    @Test
    void grow_Adds_New_Pointer_To_The_Snake_Array() {
        Pointer2D[] expectedArray = {new Pointer2D(1, 1), new Pointer2D(2, 1), new Pointer2D(3, 1), new Pointer2D(4, 1)};
        assertTrue(snake.grow());
        assertArrayEquals(expectedArray, snake.toArray());
    }

    @Test
    void grow_Return_False_When_Growing_Into_Wall() {
        assertTrue(snake.setDirection(Directions.DOWN));
        assertTrue(snake.grow());
        assertFalse(snake.grow());
    }

    @Test
    void grow_Return_False_When_Growing_Into_Itself() {
        assertTrue(snake.setDirection(Directions.DOWN));
        assertTrue(snake.grow());
        assertTrue(snake.setDirection(Directions.LEFT));
        assertTrue(snake.grow());
        assertTrue(snake.setDirection(Directions.UP));
        assertFalse(snake.grow());
    }

    @Test
    void move_Works_Correctly() {
        Pointer2D[] expectedArray = {new Pointer2D(2, 1), new Pointer2D(3, 1), new Pointer2D(4, 1)};
        assertTrue(snake.move());
        assertArrayEquals(expectedArray, snake.toArray());
    }

    @Test
    void move_Can_Move_To_The_Tail_Coordinate() {
        snake.setDirection(Directions.DOWN);
        snake.grow();
        snake.setDirection(Directions.LEFT);
        snake.move();
        snake.setDirection(Directions.UP);
        assertTrue(snake.move());
    }

    @Test
    void move_Cant_Move_Into_Border() {
        snake.setDirection(Directions.DOWN);
        snake.move();
        assertFalse(snake.move());
    }

    @Test
    void toArray_Returns_Correct_Array_After_Initialization() {
        assertArrayEquals(initialArray, snake.toArray());
    }

    @Test
    void toArray_Returns_Correct_Array_After_Few_Moves() {
        Pointer2D[] expectedArray = {new Pointer2D(3, 1), new Pointer2D(3, 0), new Pointer2D(2, 0), new Pointer2D(2, 1)};
        snake.setDirection(Directions.DOWN);
        snake.grow();
        snake.setDirection(Directions.LEFT);
        snake.move();
        snake.setDirection(Directions.UP);
        snake.move();
        assertArrayEquals(expectedArray, snake.toArray());
    }

    @Test
    void getHeadPointer_Returns_Correct_Head_Pointer_After_Initialization() {
        assertEquals(snake.getHeadPointer(), new Pointer2D(3, 1));
    }
}