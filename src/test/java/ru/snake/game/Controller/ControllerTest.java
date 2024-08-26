package ru.snake.game.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import ru.snake.game.Directions.Directions;
import ru.snake.game.Fruit.Food;
import ru.snake.game.KeyListener.KeyEventListener;
import ru.snake.game.KeyListener.PressedKeys;
import ru.snake.game.Pointer2D.Pointer2D;
import ru.snake.game.Snake.Snake;
import ru.snake.game.Snake.SnakeInterface;
import ru.snake.game.UserInterface.SnakeUserInterface;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;

class ControllerTest {
    Controller controller;
    SnakeUserInterface ui;
    KeyEventListener listener;
    PressedKeys pressedKeys;

    @BeforeEach
    void setUp() {
        ui = Mockito.mock(SnakeUserInterface.class);
        listener = Mockito.mock(KeyEventListener.class);
        controller = new SnakeGameController(ui, listener);
        pressedKeys = Mockito.mock(PressedKeys.class);
    }

    @Test
    void move_Works_Correctly() {
        when(pressedKeys.getDirection()).thenReturn(Directions.RIGHT);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        controller.move();
        controller.move();
        controller.move();
        controller.move();

        Assertions.assertFalse(controller.isGameOver());
    }

    @Test
    void restart_Sets_GameOver_Flag_To_False() {
        when(pressedKeys.getDirection()).thenReturn(Directions.DOWN);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        while (!controller.isGameOver()) {
            controller.move();
        }

        controller.restart();
        Assertions.assertFalse(controller.isGameOver());
    }

    @Test
    void isGameOver_Works_Correctly() {
        when(pressedKeys.getDirection()).thenReturn(Directions.DOWN);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        controller.move();
        controller.move();

        Assertions.assertTrue(controller.isGameOver());
    }

    @Test
    void getScore_Increases_After_Fruit_Collision() throws NoSuchFieldException, IllegalAccessException {
        when(pressedKeys.getDirection()).thenReturn(Directions.RIGHT);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        SnakeInterface snake = getSnakeReflective();
        Food food = getFoodReflective();

        int initialScore = controller.getScore();
        int initialSnakeSize = snake.getLength();
        int steps = 3;

        for (int i = 0; i < steps; i++) {
            Pointer2D head = snake.getHeadPointer();
            Directions direction = listener.getPressedKeys().getDirection();
            Pointer2D headNext = head.add(Pointer2D.getDirectionalPointer(direction));
            food.setFoodPointer(headNext);

            controller.move();
        }

        int score = controller.getScore();
        int snakeSize = snake.getLength();

        Assertions.assertEquals(score - initialScore, steps);
        Assertions.assertEquals(snakeSize - initialSnakeSize, steps);
    }

    @Test
    void move_Increase_Snake_Length_After_Fruit_Collision() throws NoSuchFieldException, IllegalAccessException {
        when(pressedKeys.getDirection()).thenReturn(Directions.RIGHT);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        SnakeInterface snake = getSnakeReflective();
        Food food = getFoodReflective();

        int initialLength = snake.getLength();
        int steps = 3;

        for (int i = 0; i < steps; i++) {
            Pointer2D head = snake.getHeadPointer();
            Directions direction = listener.getPressedKeys().getDirection();
            Pointer2D headNext = head.add(Pointer2D.getDirectionalPointer(direction));
            food.setFoodPointer(headNext);

            controller.move();
        }

        int snakeLength = snake.getLength();

        Assertions.assertEquals(snakeLength - initialLength, steps);
    }

    @Test
    void move_Can_Make_Last_Moves_With_Big_Snake_Correctly() throws NoSuchFieldException, IllegalAccessException {
        setSnakeReflective(getBigSnakeArray());

        Food food = getFoodReflective();


        when(pressedKeys.getDirection()).thenReturn(Directions.LEFT);
        when(listener.getPressedKeys()).thenReturn(pressedKeys);

        food.setFoodPointer(new Pointer2D(1,9));
        controller.move();

        Assertions.assertFalse(controller.isGameOver());

        controller.move();
        Assertions.assertTrue(controller.isGameOver());
    }

    private Food getFoodReflective() throws NoSuchFieldException, IllegalAccessException {
        Field field = controller.getClass().getDeclaredField("food");
        field.setAccessible(true);
        return  (Food) field.get(controller);
    }

    private SnakeInterface getSnakeReflective() throws NoSuchFieldException, IllegalAccessException {
        Field field = controller.getClass().getDeclaredField("snake");
        field.setAccessible(true);
        return  (SnakeInterface) field.get(controller);
    }

    private void setSnakeReflective(Pointer2D[] snakeArray) throws NoSuchFieldException, IllegalAccessException {
        SnakeInterface newSnake = new Snake(snakeArray);

        Field field = controller.getClass().getDeclaredField("snake");
        field.setAccessible(true);
        field.set(controller, newSnake);
    }


    /**
     * Sets up an array of pointers representing snake body with the length of 98.
     * Valid fruit positions are: x:0,y:9 and x:1,y:9
     * Snake can only move to the left.
     * @return {@code Pointer2D[]} array representing the snake body
     */
    private Pointer2D[] getBigSnakeArray() {

        List<Pointer2D> newSnake = new ArrayList<>();

        for (int y = 0; y < 9; y++) {
            if (y % 2 == 0) {
                for (int x = 0; x < 10; x++) {
                    newSnake.add(new Pointer2D(x, y));
                }
            } else {
                for (int x = 9; x >= 0; x--) {
                    newSnake.add(new Pointer2D(x, y));
                }
            }

        }

        for (int x = 9; x >= 2; x--) {
            newSnake.add(new Pointer2D(x, 9));
        }

        return newSnake.toArray(new Pointer2D[0]);
    }
}