package ru.snake.game.Fruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.snake.game.Pointer2D.Pointer2D;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    Food food;

    @BeforeEach
    void setUp() {
        food = new Food(new Pointer2D(1,1));
    }

    @Test
    void setFoodPointer() {
        Pointer2D expectedPointer = new Pointer2D(5,5);
        Food food = new Food(new Pointer2D(1,1));
        food.setFoodPointer(new Pointer2D(5,5));
        assertEquals(expectedPointer, food.getFoodPointer());
    }
}