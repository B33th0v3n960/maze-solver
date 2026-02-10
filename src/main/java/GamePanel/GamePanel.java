package GamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import KeyHandler.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler();
    private Player player = new Player(this, keyHandler);

    private final int FPS = 60;

    private final int UNSCALED_TILEDSIZE = 16;
    private final int SCALE = 8;
    public final int TILESIZE = SCALE * UNSCALED_TILEDSIZE;

    public final int MAX_SCREEN_COLUMN = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int MAX_SCREEN_WIDTH = MAX_SCREEN_COLUMN * TILESIZE;
    public final int MAX_SCREEN_HEIGHT = MAX_SCREEN_ROW * TILESIZE;

    public final int MAX_WORLD_COLUMN = 50;
    public final int MAX_WORLD_ROW = 50;
    public final int MAX_WORLD_WIDTH = MAX_WORLD_COLUMN * TILESIZE;
    public final int MAX_WORLD_HEIGHT = MAX_WORLD_ROW * TILESIZE;

    public GamePanel() {
        this.setPreferredSize(new Dimension(MAX_SCREEN_WIDTH, MAX_SCREEN_HEIGHT));
        this.setBackground(new Color(0x2e3440));

        // Double buffer reduces flickering, improves rendering
        this.setDoubleBuffered(true);

        // Focusable must be set to true for keyEventListener
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
    }

    // This initialised the Game
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000.0; // Converts to milliseconds
                remainingTime = (remainingTime < 0) ? 0 : remainingTime;

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException error) {
                error.printStackTrace();
            }

        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics;
        player.draw(g2d);

        g2d.dispose();
    }
}
