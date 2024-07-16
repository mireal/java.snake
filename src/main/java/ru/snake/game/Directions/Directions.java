package ru.snake.game.Directions;

import ru.snake.game.Pointer2D.Pointer2D;

public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Directions getOpposite() {
        switch (this) {
            case UP -> {
                return DOWN;
            }
            case DOWN -> {
                return UP;
            }
            case LEFT -> {
                return RIGHT;
            }
            case RIGHT -> {
                return LEFT;
            }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public static Directions getDirectionByPointer(Pointer2D pointer2D) {
        if (Math.abs(pointer2D.x() + pointer2D.y()) != 1) {
            return null;
        }
        if (pointer2D.x() == 0) {
            if (pointer2D.y() == 1) return UP;
            else return DOWN;
        } else {
            if (pointer2D.x() == 1) return RIGHT;
            else return LEFT;
        }
    }
}
