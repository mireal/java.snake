package ru.snake.game.Pointer2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pointer2DTest {
    @Test
    void addMethodTest() {
        Pointer2D pointer1 = new Pointer2D(1,1);
        Pointer2D pointer2 = new Pointer2D(2,2);

        Pointer2D additionResult = pointer1.add(pointer2);
        Pointer2D result = new Pointer2D(3,3);
        assertEquals(result,additionResult);
    }

    @Test
    void testEqualsPositive() {
        Pointer2D pointer1 = new Pointer2D(123,6544);
        Pointer2D pointer2 = new Pointer2D(123,6544);
        assertEquals(pointer1, pointer2);
    }

    @Test
    void testEqualsNegative() {
        Pointer2D pointer1 = new Pointer2D(123,6544);
        Pointer2D pointer2 = new Pointer2D(6453,6544);
        assertNotEquals(pointer1,pointer2);
    }


}