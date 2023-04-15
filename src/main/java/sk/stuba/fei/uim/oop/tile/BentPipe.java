package sk.stuba.fei.uim.oop.tile;


import java.awt.*;

public class BentPipe extends Tile {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x0B1DE7));

        g.fillRect(getWidth() / 3, getHeight() / 4 + 15, getWidth() / 2 + 18, getHeight() / 4);
        g.fillRect(getWidth() / 3, getHeight()/2 + 14, getWidth() / 4 - 5, getHeight());


    }
}
