package Entity;

import java.awt.image.BufferedImage;
import java.util.HashMap;

abstract class Entity {
    protected int worldX;
    protected int worldY;

    protected int screenX;
    protected int screenY;

    protected int cameraX;
    protected int cameraY;

    protected int cameraWorldX;
    protected int cameraWorldY;

    protected HashMap<Direction, BufferedImage[]> frames = new HashMap<>();
    protected int frameIndex;
    protected int timeCounter;

    protected Direction direction;
    protected int velocity[];
}
