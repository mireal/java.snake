package ru.snake.game.Controller;

import ru.snake.game.Directions.Directions;
import ru.snake.game.Fruit.Food;
import ru.snake.game.KeyListener.KeyEventListener;
import ru.snake.game.KeyListener.PressedKeys;
import ru.snake.game.Pointer2D.Pointer2D;
import ru.snake.game.Snake.Snake;
import ru.snake.game.Snake.SnakeInterface;
import ru.snake.game.UserInterface.SnakeUserInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SnakeGameController implements Controller {
    private boolean gameOver;
    private int score = 0;

    private SnakeInterface snake;
    private Food food;
    private final KeyEventListener keyListener;
    private final SnakeUserInterface userInterface;

    private final Pointer2D[] snakeInitialPosition =
            new Pointer2D[]{new Pointer2D(1, 1), new Pointer2D(2, 1), new Pointer2D(3, 1)};

    public SnakeGameController(SnakeUserInterface userInterface, KeyEventListener keyListener) {
        this.keyListener = keyListener;
        this.userInterface = userInterface;

        restart();
    }


    @Override
    public void move() {
        makeMove();
        draw();
    }

    @Override
    public void restart() {
        snake = new Snake(snakeInitialPosition);
        food = new Food(getRandomFoodPointer());
        gameOver = false;
        score = 0;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    private Pointer2D getRandomFoodPointer() {
        List<Pointer2D> pointers = getPossibleFoodPositions();

        if (pointers.isEmpty()) return null;

        int index = (int) (Math.random() * pointers.size());

        return pointers.get(index);
    }

    private List<Pointer2D> getPossibleFoodPositions() {
        Set<Pointer2D> possibleFoodPositions = getSetOfGridPointers();

        for (Pointer2D point : snake.toArray()) {
            possibleFoodPositions.remove(point);
        }

        return possibleFoodPositions.stream().toList();
    }

    private Set<Pointer2D> getSetOfGridPointers() {
        Set<Pointer2D> result = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                result.add(new Pointer2D(i, j));
            }
        }

        return result;
    }

    private Directions setAndGetDirection(Directions newDirection) {
        if (!snake.setDirection(newDirection)) {
            return snake.getDirection();
        }

        return newDirection;
    }

    private Pointer2D getNextHeadPointer(Pointer2D snakeHead, Directions direction) {
        return snakeHead.add(Pointer2D.getDirectionalPointer(direction));
    }

    private void makeMove() {
        if (gameOver) return;

        PressedKeys keys = keyListener.getPressedKeys();

        Directions direction = setAndGetDirection(keys.getDirection());
        Pointer2D snakeHeadNext = getNextHeadPointer(snake.getHeadPointer(), direction);

        boolean moved;

        if (snakeHeadNext.equals(food.getFoodPointer())) {
            moved = snake.grow();
            if (moved) {
                score += 1;
                Pointer2D nextFoodPointer = getRandomFoodPointer();

                if (nextFoodPointer != null) {
                    food.setFoodPointer(nextFoodPointer);
                }
            }
        }
        else {
            moved = snake.move();
        }

        if (!moved || snake.getLength() == 100) gameOver = true;
    }

    private void draw() {
        userInterface.clear();
        userInterface.drawFruit(food.getFoodPointer());
        userInterface.drawSnake(snake.toArray());
        userInterface.update();
    }
}
