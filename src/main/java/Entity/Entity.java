package Entity;

import java.awt.image.BufferedImage;

abstract class Entity {
    protected int worldX;
    protected int worldY;

    protected int screenX;
    protected int screenY;

    protected int cameraX;
    protected int cameraY;

    protected BufferedImage frames[][];
    protected int frameIndex;
    protected int timeCounter;

    protected Direction direction;
    protected int velocity[];
}
