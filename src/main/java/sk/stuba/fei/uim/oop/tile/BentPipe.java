package sk.stuba.fei.uim.oop.tile;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BentPipe extends Tile {
    @Getter
    @Setter
    private State state;

    public BentPipe(State state) {
        this.state = state;
    }


    @Override
    public List<Direction> getOpenDirections() {
        switch (this.rotation) {
            case DEGREES_0:
                return Arrays.asList(Direction.DOWN, Direction.RIGHT);
            case DEGREES_180:
                return Arrays.asList(Direction.LEFT, Direction.UP);
            case DEGREES_90:
                return Arrays.asList(Direction.DOWN, Direction.LEFT);
            case DEGREES_270:
                return Arrays.asList(Direction.UP, Direction.RIGHT);
            default:
                return Collections.emptyList();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x0B1DE7));

        g.fillRect(getWidth() / 3, getHeight() / 4 + 8, getWidth(), getHeight() / 3);
        g.fillRect(getWidth() / 3, getHeight() / 4 + 10, getWidth() / 3, getHeight());

    }
}
