package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.board.Direction;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Прямая труба
public class StraightPipe extends Tile {

    @Override
    public List<Direction> getOpenDirections() {
        switch (this.rotation) {
            case DEGREES_0:
            case DEGREES_180:
                return Arrays.asList(Direction.UP, Direction.DOWN);
            case DEGREES_90:
            case DEGREES_270:
                return Arrays.asList(Direction.LEFT, Direction.RIGHT);
            default:
                return Collections.emptyList();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x0132FF));
        g.fillRect(getWidth() / 3, 0, getWidth() / 3, getHeight());
    }
}
