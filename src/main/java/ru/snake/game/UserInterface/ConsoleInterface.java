package ru.snake.game.UserInterface;

import ru.snake.game.Pointer2D.Pointer2D;

import java.util.Arrays;

public class ConsoleInterface implements SnakeUserInterface {
    private final char[][] grid;

    private final char FOOD = '@';
    private final char FIELD = ' ';
    private final char SNAKE = '#';

    ConsoleInterface() {
        grid = new char[10][10];
        clear();
    }

    @Override
    public void clear() {
        for (char[] chars : grid) {
            Arrays.fill(chars, FIELD);
        }
    }

    @Override
    public void drawSnake(Pointer2D[] snake) {
        for (Pointer2D point : snake) {
            grid[point.y()][point.x()] = SNAKE;
        }
    }

    @Override
    public void drawFruit(Pointer2D fruit) {
        grid[fruit.y()][fruit.x()] = FOOD;
    }

    @Override
    public void update() {
        printGrid();
    }

    private void printGrid() {
        System.out.println(" ______________________________ ");
        for (int i = grid.length - 1; i >= 0; i--) {
            char[] chars = grid[i];
            System.out.print("|");
            for (char c : chars) {
                System.out.print(" " + c + " ");
            }
            System.out.println("|");
        }
        System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻ ");
    }
}
