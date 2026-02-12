package Entity;

import java.util.Map;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import GamePanel.GamePanel;
import KeyHandler.KeyHandler;

public class Player extends Entity implements PlayerInterface {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private boolean lastKeyPress = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        this.worldX = 1 * gamePanel.TILESIZE;
        this.worldY = 0 * gamePanel.TILESIZE;

        this.screenX = worldX + gamePanel.TILESIZE;
        this.screenY = worldY + gamePanel.TILESIZE;

        this.cameraX = (gamePanel.MAX_SCREEN_WIDTH) / 2;
        this.cameraY = (gamePanel.MAX_SCREEN_HEIGHT) / 2;

        this.cameraWorldX = 8 * gamePanel.TILESIZE;
        this.cameraWorldY = 6 * gamePanel.TILESIZE;

        this.velocity = new int[] { 5, 5 };
        this.direction = Direction.DOWN;

        loadFrameImage("/Player/Player.png");
    }

    private void loadFrameImage(String path) {
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource(path));
            int row = 0;
            for (Direction imageDirection : Direction.values()) {
                frames.put(imageDirection, new BufferedImage[4]);
                for (int frameIndex = 0; frameIndex < frames.get(imageDirection).length; frameIndex++) {
                    int imageCol = 3 * frameIndex * 16 + 16;
                    int imageRow = 3 * row * 16 + 16;
                    frames.get(imageDirection)[frameIndex] = tileset.getSubimage(imageCol, imageRow, 16, 16);
                }

                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean isWalking = false;
        Map<String, Boolean> keyState = keyHandler.getKeyState();
        // velocity = new int[] { 0, 0 };

        velocity[0] += (velocity[0] == 0) ? 0 : (velocity[0] < 0) ? 1 : -1;
        velocity[1] += (velocity[1] == 0) ? 0 : (velocity[1] < 0) ? 1 : -1;

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

        g2d.drawImage(frames.get(direction)[frameIndex],
                effectScreenX, effectScreenY,
                gamePanel.TILESIZE, gamePanel.TILESIZE,
                null);
    }

    @Override
    public int getWorldX() {
        return worldX;
    }

    @Override
    public int getWorldY() {
        return worldY;
    }

    @Override
    public int getScreenX() {
        return screenX;
    }

    @Override
    public int getScreenY() {
        return screenY;
    }

    @Override
    public int getCameraX() {
        return cameraX;
    }

    @Override
    public int getCameraY() {
        return cameraY;
    }

    @Override
    public int getCameraWorldX() {
        return cameraWorldX;
    }

    @Override
    public int getCameraWorldY() {
        return cameraWorldY;
    }
}
