package ru.snake.game.UserInterface;

import ru.snake.game.Pointer2D.Pointer2D;

public interface SnakeUserInterface {
    /**
     * Call this function to clear the canvas.
     */
    void clear();

    /**
     * Passes the snake pointers array to the drawer to draw the snake body.
     * Should be called after the clear method to avoid drawing overlay.
     * @param snake Array of Pointer2D objects representing the snake body.
     */
    void drawSnake(Pointer2D[] snake);

    /**
     * Passes the fruit pointer to the drawer to draw the fruit on the canvas.
     * Should be called after the clear method to avoid drawing overlay.
     * @param fruit Pointer2D object containing the fruit coordinate.
     */
    void drawFruit(Pointer2D fruit);

    /**
     * Updates the user interface by redrawing the elements on the canvas.
     * Should be called after calling drawSnake and drawFruit to ensure the re-drawing.
     */
    void update();
}
