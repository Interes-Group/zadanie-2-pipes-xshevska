package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class StartEnd extends Tile {
    @Getter
    @Setter
    private State state;

    public StartEnd(State state) {
        this.state = state;
    }

    @Override
    public List<Direction> getAccessibleDirections() {
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
        if (this.state.equals(State.WATER_PRESENT)) {
            g.setColor(new Color(0x0B61C7));
        } else {
            g.setColor(new Color(0x12C466));
        }
        g.fillOval(getWidth() / 5, getHeight() / 4, getWidth() / 2, getHeight() / 2);
        g.fillRect(getWidth() / 3, getHeight() / 3, getWidth(), getHeight() / 3);

    }
}
