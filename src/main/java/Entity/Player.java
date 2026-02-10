package Entity;

import java.util.Map;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import GamePanel.GamePanel;
import KeyHandler.KeyHandler;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private boolean lastKeyPress = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        this.worldX = 25 * gamePanel.TILESIZE;
        this.worldY = 25 * gamePanel.TILESIZE;
        this.screenX = (gamePanel.MAX_SCREEN_WIDTH + gamePanel.TILESIZE) / 2;
        this.screenY = (gamePanel.MAX_SCREEN_HEIGHT + gamePanel.TILESIZE) / 2;
        this.velocity = new int[] { 5, 5 };
        this.direction = Direction.DOWN;

        loadFrameImage("/Player/Player.png");
    }

    private void loadFrameImage(String path) {
        frames = new BufferedImage[4][4];

        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource(path));
            for (int row = 0; row < frames.length; row++) {
                for (int col = 0; col < frames[row].length; col++) {
                    int imageCol = 3 * col * 16 + 16;
                    int imageRow = 3 * row * 16 + 16;
                    frames[row][col] = tileset.getSubimage(imageCol, imageRow, 16, 16);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean isWalking = false;
        Map<String, Boolean> keyState = keyHandler.getKeyState();
        velocity = new int[] { 0, 0 };

        if (keyState.get("up") == true) {
            velocity[1] = -5;
            direction = Direction.UP;
            isWalking = true;
        }
        if (keyState.get("down") == true) {
            velocity[1] = 5;
            direction = Direction.DOWN;
            isWalking = true;
        }
        if (keyState.get("left") == true) {
            velocity[0] = -5;
            direction = Direction.LEFT;
            isWalking = true;
        }
        if (keyState.get("right") == true) {
            velocity[0] = 5;
            direction = Direction.RIGHT;
            isWalking = true;
        }

        // worldX += velocity[0];
        // worldY += velocity[1];
        screenX += velocity[0];
        screenY += velocity[1];

        if (timeCounter++ % 20 == 0 || (isWalking && !lastKeyPress)) {
            if (isWalking)
                frameIndex = ((++frameIndex) % 2) + 2;
            else
                frameIndex = (++frameIndex) % 2;
        }

        lastKeyPress = isWalking;
    }

    public void draw(Graphics2D g2d) {
        int effectScreenX = screenX - gamePanel.TILESIZE;
        int effectScreenY = screenY - gamePanel.TILESIZE;
        int state = 0;
        if (direction == Direction.UP)
            state = 1;
        else if (direction == Direction.DOWN)
            state = 0;
        else if (direction == Direction.LEFT)
            state = 2;
        else if (direction == Direction.RIGHT)
            state = 3;

        g2d.drawImage(frames[state][frameIndex], effectScreenX, effectScreenY, gamePanel.TILESIZE, gamePanel.TILESIZE,
                null);
    }
}
