import javax.swing.JFrame;

import GamePanel.GamePanel;

public class Main {
    public static void main(String[] args) {
        // Force opengl rendering
        System.setProperty("sun.java2d.opengl", "true");

        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze walker");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
