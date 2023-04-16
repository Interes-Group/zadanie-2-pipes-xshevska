package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public abstract class PipeTile extends Tile {
    protected int angle = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);

    }

    public void rotate() {
        angle += 90;
        angle %= 360;
        repaint();
    }
}
