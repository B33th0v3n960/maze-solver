package Tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Entity.PlayerInterface;
import GamePanel.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    PlayerInterface player;
    int tilemapNum[][];

    public TileManager(GamePanel gamePanel, PlayerInterface player) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.tilemapNum = new int[gamePanel.MAX_SCREEN_ROW][gamePanel.MAX_SCREEN_COLUMN];

        loadMap("/Map/Map.csv");
    }

    private void getTileImage() {
    }

    public void loadMap(String path) {
        try {
            InputStream input = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

            for (int row = 0; row < tilemapNum.length; row++) {
                String line = bufferedReader.readLine();
                System.out.println(line);

                for (int col = 0; col < tilemapNum[row].length; col++) {
                    String tileTypeArray[] = line.split(",");
                    int tileType = Integer.parseInt(tileTypeArray[col]);
                    tilemapNum[row][col] = tileType;
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {

        for (int worldRow = 0; worldRow < tilemapNum.length; worldRow++) {

            for (int worldCol = 0; worldCol < tilemapNum[worldRow].length; worldCol++) {
                int tileWorldX = worldCol * gamePanel.TILESIZE;
                int tileWorldY = worldRow * gamePanel.TILESIZE;
                int screenX = tileWorldX - player.getCameraWorldX() + player.getCameraX();
                int screenY = tileWorldY - player.getCameraWorldY() + player.getCameraY();

                if (tileWorldX + gamePanel.TILESIZE < player.getCameraWorldX() - player.getCameraX() ||
                        tileWorldX - gamePanel.TILESIZE > player.getCameraWorldX() + player.getCameraX() ||
                        tileWorldY + gamePanel.TILESIZE < player.getCameraWorldY() - player.getCameraY() ||
                        tileWorldY - gamePanel.TILESIZE > player.getCameraWorldY() + player.getCameraY())
                    continue;

                // g2d.drawImage(waterTiles[0][mapTileNum[0][worldRow][worldCol]].image,
                // screenX, screenY,
                // gamePanel.tileSize, gamePanel.tileSize, null);

                if (tilemapNum[worldRow][worldCol] == 1) {
                    g2d.setColor(new Color(0xd8dee9));
                    g2d.fillRect(screenX, screenY, gamePanel.TILESIZE, gamePanel.TILESIZE);
                }
            }
        }

    }
}
