package ru.snake.game.Controller;

public interface SnakeGameController {
    void move();

    void restart();

    boolean isGameOver();

    int getScore();
}
