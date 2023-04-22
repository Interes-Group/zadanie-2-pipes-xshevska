package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.board.Direction;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class StartEnd extends Tile {
    private final boolean filledWithWater;

    public StartEnd(boolean filledWithWater) {
        this.filledWithWater = filledWithWater;
    }

    @Override
    public List<Direction> getOpenDirections() {
        switch (this.rotation) {
            case DEGREES_0:
                return List.of(Direction.RIGHT);
            case DEGREES_180:
                return List.of(Direction.LEFT);
            case DEGREES_90:
                return List.of(Direction.DOWN);
            case DEGREES_270:
                return List.of(Direction.UP);
            default:
                return Collections.emptyList();
        }
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
        g.fillRect(getWidth() / 3, getHeight() / 4 + 8, getWidth(), getHeight() / 3);

    }
}
