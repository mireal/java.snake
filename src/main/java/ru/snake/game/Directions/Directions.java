package ru.snake.game.Directions;

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
}
