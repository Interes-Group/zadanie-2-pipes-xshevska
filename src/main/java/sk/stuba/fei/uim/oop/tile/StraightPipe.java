package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

 //Прямая труба
public class StraightPipe extends Tile {
     private int angle = 0;

     public void rotate() {
         angle += 90;
         angle %= 360;
         repaint();
     }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing StraightPipe");
        g.setColor(new Color(0x0132FF));
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);

        g.fillRect(getWidth() / 3, 0, getWidth() / 4 - 5, getHeight());
    }
}
