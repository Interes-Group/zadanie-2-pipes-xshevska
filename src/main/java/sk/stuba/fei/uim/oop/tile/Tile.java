package sk.stuba.fei.uim.oop.tile;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private int pipeType = 0;
    public Tile() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0x14F3D1));
    }

    public void setPipeType(int pipeType) {
        this.pipeType = pipeType;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (pipeType) {
            case 1:
                drawStraightPipe(g);
                break;
            case 2:
                drawGShapedPipe(g);
                break;
            default:
                break;
        }
    }

    private void drawStraightPipe(Graphics g) {
        int tileSize = Math.min(getWidth(), getHeight());
        g.setColor(Color.BLUE);
        g.fillRect(0, tileSize / 4, tileSize, tileSize / 4);
        g.fillRect(0, tileSize * 3 / 4, tileSize, tileSize / 4);
    }

    private void drawGShapedPipe(Graphics g) {
        int tileSize = Math.min(getWidth(), getHeight());
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, tileSize / 4, tileSize);
        g.fillRect(0, 0, tileSize, tileSize / 4);
    }






}
