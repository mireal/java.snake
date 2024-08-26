package ru.snake.game.Controller;

/**
 * Game controller interface that handles the basic game functionality.
 */
public interface Controller {
    /**
     * Call this method to move a snake.
     * The controller should call a key listener to update the direction,
     * draw elements on the canvas and move the snake.
     */
    void move();

    /**
     * Call this method to restart the game.
     * After restart score is sets to 0.
     */
    void restart();

    /**
     * Call this method to check the state of the game.
     * @return {@code boolean} representing the state of the game/
     */
    boolean isGameOver();

    /**
     * Call this method to get the current score.
     * @return {@code int} score.
     */
    int getScore();
}
