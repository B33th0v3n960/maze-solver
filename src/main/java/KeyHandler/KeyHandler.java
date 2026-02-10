package KeyHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private HashMap<String, Boolean> keyState = new HashMap<>();

    public KeyHandler() {
        keyState.put("up", false);
        keyState.put("down", false);
        keyState.put("left", false);
        keyState.put("right", false);
    }

    public Map<String, Boolean> getKeyState() {
        return Collections.unmodifiableMap(keyState);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_W)
            keyState.put("up", true);
        if (keycode == KeyEvent.VK_S)
            keyState.put("down", true);
        if (keycode == KeyEvent.VK_A)
            keyState.put("left", true);
        if (keycode == KeyEvent.VK_D)
            keyState.put("right", true);

        if (keycode == KeyEvent.VK_Q)
            System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_W)
            keyState.put("up", false);
        if (keycode == KeyEvent.VK_S)
            keyState.put("down", false);
        if (keycode == KeyEvent.VK_A)
            keyState.put("left", false);
        if (keycode == KeyEvent.VK_D)
            keyState.put("right", false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOT USE!!!
    }
}
