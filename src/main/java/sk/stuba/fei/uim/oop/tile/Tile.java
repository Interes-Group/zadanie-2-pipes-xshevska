package sk.stuba.fei.uim.oop.tile;

import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Rotation;

import javax.swing.*;
import java.awt.*;


public class Tile extends JPanel {
    @Setter
    private boolean highlight;

    protected int angle;

    protected Rotation rotation;

    public Tile() {
        this.setBackground(new Color(0x14F3D1));
        this.rotation = Rotation.DEGREES_0.randomRotation();
    }


    public void rotate() {
        this.rotation = rotation.next();
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.angle = rotation.getAngle();
        g2.rotate(Math.toRadians(angle), (getWidth() / 2), (getHeight() / 2));

        if (highlight) {
            g.setColor(new Color(0xDBFFFB49, true));
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            highlight = false;
        }
    }

}



