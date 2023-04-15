package sk.stuba.fei.uim.oop.tile;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    @Setter
    private boolean highlight;

    public Tile() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0x14F3D1));
    }

    //0xFF1FA5

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.BLUE);
//        g.drawRect(3,3 , this.getWidth() - 6, this.getHeight() - 6);
        if (highlight) {
            g.setColor(new Color(0xDBFFFB49, true));
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawRect(0,0 , this.getWidth(), this.getHeight());
            highlight = false;
        }
    }


}




