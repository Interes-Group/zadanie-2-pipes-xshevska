package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class StartEnd extends Tile {
    private boolean filledWithWater;

    public StartEnd(boolean filledWithWater) {
        this.filledWithWater = filledWithWater;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.filledWithWater) {
            g.setColor(new Color(0x1E7EEA));
        } else {
            g.setColor(new Color(0x888181));
        }
        g.drawOval(getWidth() / 6, getHeight() / 5, getWidth() / 2 + 10, getHeight() / 2 + 7);
        g.fillOval(getWidth() / 6, getHeight() / 5, getWidth() / 2 + 10, getHeight() / 2 + 7);

//        g.setColor(new Color(0x0132FF));
        g.fillRect(getWidth() / 3, getHeight() / 4 + 8, getWidth(), getHeight() / 3);

    }
}
