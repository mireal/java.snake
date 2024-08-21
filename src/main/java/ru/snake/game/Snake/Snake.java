package ru.snake.game.Snake;

import org.jetbrains.annotations.NotNull;
import ru.snake.game.Directions.Directions;
import ru.snake.game.Pointer2D.Pointer2D;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class Snake implements SnakeInterface {
    LinkedHashSet<Pointer2D> snake;
    Directions direction;
    int borderLimit = 10;

    public Snake(Pointer2D[] snakeBody) {
        int len = snakeBody.length;
        Pointer2D snakeHeadPointer = snakeBody[len - 1];
        Pointer2D snakeHeadNextPointer = snakeBody[len - 2];
        Pointer2D directionPointer = snakeHeadPointer.sub(snakeHeadNextPointer);
        direction = Directions.getDirectionByPointer(directionPointer);

        snake = new LinkedHashSet<>(Arrays.asList(snakeBody));
    }

    @Override
    public int getLength() {
        return snake.size();
    }

    @Override
    public Directions getDirection() {
        return direction;
    }

    @Override
    public boolean setDirection(Directions direction) {
        if (direction == this.direction.getOpposite()) return false;
        this.direction = direction;
        return true;
    }

    @Override
    public boolean grow() {
        Pointer2D newHeadPosition = getNewHeadPosition();
        if (outOfBorder(newHeadPosition) || movingIntoItself(newHeadPosition)) return false;

        snake.addLast(newHeadPosition);

        return true;
    }

    @Override
    public boolean move() {
        Pointer2D newHeadPosition = getNewHeadPosition();
        Pointer2D tail = snake.removeFirst();

        if (outOfBorder(newHeadPosition) || movingIntoItself(newHeadPosition)) {
            snake.addFirst(tail);
            return false;
        }

        snake.addLast(newHeadPosition);

        return true;
    }

    @Override
    public Pointer2D[] toArray() {
        return snake.toArray(new Pointer2D[getLength()]);
    }

    @Override
    public Pointer2D getHeadPointer() {
        return snake.getLast();
    }

    private @NotNull Pointer2D getNewHeadPosition() {
        Pointer2D head = getHeadPointer();
        return head.add(Pointer2D.getDirectionalPointer(direction));
    }

    private boolean outOfBorder(@NotNull Pointer2D pointer) {
        boolean xOutOfBorder = (pointer.x() < 0 || pointer.x() >= borderLimit);
        boolean yOutOfBorder = (pointer.y() < 0 || pointer.y() >= borderLimit);
        return (xOutOfBorder || yOutOfBorder);
    }

    private boolean movingIntoItself(@NotNull Pointer2D pointer) {
        return snake.contains(pointer);
    }
}
