package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.Rotation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Collections;
import java.util.List;


public class Tile extends JPanel {
    @Setter
    private boolean highlight;
    @Getter
    protected Rotation rotation;
    protected int angle;

    public Tile() {
        this.setBackground(new Color(0x14F3D1));
        setBorder(new LineBorder(Color.BLACK, 2));
        this.rotation = Rotation.DEGREES_0.randomRotation();
    }

    public List<Direction> getOpenDirections() {
        return Collections.emptyList();
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
        g2.rotate(Math.toRadians(angle), (getWidth() >> 1), (getHeight() >> 1));
        if (highlight) {
            g.setColor(new Color(0xDBFFFB49, true));
            ((Graphics2D) g).setStroke(new BasicStroke(20));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            highlight = false;
        }
    }

}


