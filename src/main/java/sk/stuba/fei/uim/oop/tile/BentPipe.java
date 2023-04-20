package sk.stuba.fei.uim.oop.tile;


import java.awt.*;

public class BentPipe extends Tile {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x0B1DE7));

        g.fillRect(getWidth() / 3, getHeight() / 4 + 8, getWidth(), getHeight() / 3);
        g.fillRect(getWidth() / 3, getHeight() / 4 + 10, getWidth() / 3, getHeight());

    }
}
