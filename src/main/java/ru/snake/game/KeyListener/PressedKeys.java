package ru.snake.game.KeyListener;

import ru.snake.game.Directions.Directions;

public class PressedKeys {
    private Directions direction;
    private boolean escapePressed = false;
    private boolean spacePressed = false;

    PressedKeys() {}

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public boolean isEscapePressed() {
        return escapePressed;
    }

    public void setEscapePressed(boolean escapePressed) {
        this.escapePressed = escapePressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }
}
