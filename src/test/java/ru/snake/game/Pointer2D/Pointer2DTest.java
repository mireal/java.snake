package ru.snake.game.Pointer2D;

import org.junit.jupiter.api.Test;
import ru.snake.game.Directions.Directions;

import static org.junit.jupiter.api.Assertions.*;

class Pointer2DTest {
    @Test
    void add_Method_Test() {
        Pointer2D pointer1 = new Pointer2D(1,1);
        Pointer2D pointer2 = new Pointer2D(2,2);

        Pointer2D additionResult = pointer1.add(pointer2);
        Pointer2D result = new Pointer2D(3,3);
        assertEquals(result,additionResult);
    }

    @Test
    void sub_Method_Test() {
        Pointer2D pointer1 = new Pointer2D(1,1);
        Pointer2D pointer2 = new Pointer2D(1,1);

        Pointer2D additionResult = pointer1.sub(pointer2);
        Pointer2D result = new Pointer2D(0,0);
        assertEquals(result,additionResult);
    }

    @Test
    void getDirectionalPointer_Return_Expected_Direction() {
        Pointer2D pointerLeft = new Pointer2D(-1,0);

        assertEquals(pointerLeft, Pointer2D.getDirectionalPointer(Directions.LEFT));
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