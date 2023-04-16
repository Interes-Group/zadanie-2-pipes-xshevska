package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

 //Прямая труба
public class StraightPipe extends Tile {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing StraightPipe");
        g.setColor(new Color(0x0132FF));
        g.fillRect(getWidth() / 3, 0, getWidth() /3, getHeight());
    }
}
