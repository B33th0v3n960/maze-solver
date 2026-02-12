package Tile;

import java.awt.image.BufferedImage;

class Tile {
    private BufferedImage image;
    private boolean collision = false;

    public Tile(BufferedImage image) {
        if (image == null)
            throw new NullPointerException();
        else
            this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setCollision() {
        this.collision = true;
    }
}
