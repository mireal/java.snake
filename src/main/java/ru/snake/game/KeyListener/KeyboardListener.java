package ru.snake.game.KeyListener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import ru.snake.game.Directions.Directions;


public class KeyboardListener implements KeyEventListener, NativeKeyListener {
    private PressedKeys pressedKeys;

    KeyboardListener() {
        pressedKeys = new PressedKeys();
        init();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        mapPressedKeys(nativeEvent);
    }

    @Override
    public PressedKeys getPressedKeys() {
        PressedKeys result = pressedKeys;
        pressedKeys = new PressedKeys();
        return result;
    }

    private void init() {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    private void mapPressedKeys(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == NativeKeyEvent.VC_ESCAPE) {
            pressedKeys.setEscapePressed(true);
        }
        else if (keyCode == NativeKeyEvent.VC_SPACE) {
            pressedKeys.setSpacePressed(true);
        }
        else if (keyCode == NativeKeyEvent.VC_UP || keyCode == NativeKeyEvent.VC_W) {
            pressedKeys.setDirection(Directions.UP);
        }
        else if (keyCode == NativeKeyEvent.VC_DOWN || keyCode == NativeKeyEvent.VC_S) {
            pressedKeys.setDirection(Directions.DOWN);
        }
        else if (keyCode == NativeKeyEvent.VC_LEFT || keyCode == NativeKeyEvent.VC_A) {
            pressedKeys.setDirection(Directions.LEFT);
        }
        else if (keyCode == NativeKeyEvent.VC_RIGHT || keyCode == NativeKeyEvent.VC_D) {
            pressedKeys.setDirection(Directions.RIGHT);
        }
    }
}
