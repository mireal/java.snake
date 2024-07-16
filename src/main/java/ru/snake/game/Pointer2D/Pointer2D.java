package ru.snake.game.Pointer2D;

import ru.snake.game.Directions.Directions;

public record Pointer2D(int x, int y) {
    public Pointer2D add(Pointer2D pointer2D) {
        return new Pointer2D(x + pointer2D.x(),y + pointer2D.y());
    }

    public Pointer2D sub(Pointer2D pointer2D) {
        return new Pointer2D(x - pointer2D.x(),y - pointer2D.y());
    }

    public static Pointer2D getDirectionalPointer(Directions direction) {
        switch (direction) {
            case UP -> {
                return new Pointer2D(0,1);
            }
            case DOWN -> {
                return new Pointer2D(0,-1);
            }
            case LEFT -> {
                return new Pointer2D(-1,0);
            }
            case RIGHT -> {
                return new Pointer2D(1,0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    @Override
    public String toString() {
        return "Pointer2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pointer2D pointer2D = (Pointer2D) o;
        return x == pointer2D.x && y == pointer2D.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
