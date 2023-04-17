package sk.stuba.fei.uim.oop.tile;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    protected int angle = 0;
    @Setter
    private boolean highlight;

    public Tile() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0x14F3D1));
    }


    public void rotate() {
        angle += 90;
        angle %= 360;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);
//        g.setColor(Color.BLUE);
//        g.drawRect(3,3 , this.getWidth() - 6, this.getHeight() - 6);
        if (highlight) {
            g.setColor(new Color(0xDBFFFB49, true));
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            highlight = false;
        }

    }


}




