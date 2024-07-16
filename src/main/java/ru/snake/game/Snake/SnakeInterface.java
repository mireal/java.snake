package ru.snake.game.Snake;

import ru.snake.game.Directions.Directions;
import ru.snake.game.Pointer2D.Pointer2D;

public interface SnakeInterface {
    /**
     * @return {@code int} length of a snake body
     */
    int getLength();

    /**
     * @return {@code Directions} Enum direction representing
     * the current direction of a snake
     */
    Directions getDirection();

    /**
     * Call this method to make an attempt to change the snake direction.
     *
     * @return {@code true} if direction was changed successfully.
     * {@code false} if direction could be set to the opposite
     * and the snake would move into itself.
     */
    boolean setDirection(Directions direction);

    /**
     * Call this method to make an attempt to extend the snake to the set direction.
     *
     * @return {@code true} if snake extends itself successfully.
     * {@code false} if snake extend into the border or itself.
     */
    boolean grow();

    /**
     * Call this method to make an attempt to move the snake to the set direction.
     *
     * @return {@code true} if snake makes a move successfully.
     * {@code false} if snake moves into the border or itself.
     */
    boolean move();

    /**
     * Returns array of pointers representing the snake body,
     * starts with the snake tail and end with the snake head
     *
     * @return {@code Pointer2D[]} array of snake pointers
     */
    Pointer2D[] toArray();

    /**
     * Return Pointer2D object that store the value of the snake head position.
     *
     * @return {@code Pointer2D} object of Snake head
     */
    Pointer2D getHeadPointer();
}