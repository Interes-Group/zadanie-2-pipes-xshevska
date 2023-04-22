package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class StraightPipe extends Tile {
    @Getter
    @Setter
    private State state;

    public StraightPipe(State state) {
        this.state = state;
    }


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
        if (this.state == State.WATER_PRESENT) {
            g.setColor(new Color(0x308AF3));
            this.state = State.NO_WATER;
        } else {
            g.setColor(new Color(0xC0E085C4));
        }
        g.fillRect(getWidth() / 3, 0, getWidth() / 3, getHeight());
    }
}
