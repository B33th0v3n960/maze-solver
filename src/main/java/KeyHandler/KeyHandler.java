package KeyHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {
    private final HashMap<String, Boolean> keyState = new HashMap<>();

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
    public void keyPressed(final KeyEvent e) {
        final int keycode = e.getKeyCode();
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
    public void keyReleased(final KeyEvent e) {
        final int keycode = e.getKeyCode();
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
    public void keyTyped(final KeyEvent e) {
        // DO NOT USE!!!
    }
}
