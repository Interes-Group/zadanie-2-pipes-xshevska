package sk.stuba.fei.uim.oop.tile;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private int pipeType = 0;
    public Tile() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0x14F3D1));
    }

}
