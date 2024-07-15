package ru.snake.game.Pointer2D;

public record Pointer2D(int x,int y) {
    public Pointer2D add(Pointer2D pointer2D) {
        return new Pointer2D(x + pointer2D.x(),y + pointer2D.y());
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
