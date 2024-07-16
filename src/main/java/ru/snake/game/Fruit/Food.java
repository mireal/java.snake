package ru.snake.game.Fruit;

import ru.snake.game.Pointer2D.Pointer2D;

public class Food {
    private Pointer2D food;

    Food(Pointer2D food) {
        this.food = food;
    }

    public Pointer2D getFoodPointer() {
        return food;
    }

    public void setFoodPointer(Pointer2D pointer) {
        food = pointer;
    }
}
