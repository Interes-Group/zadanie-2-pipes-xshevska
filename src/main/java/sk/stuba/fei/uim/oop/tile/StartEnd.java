package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class StartEnd extends Tile {
    private boolean filledWithWater;

    public StartEnd(boolean filledWithWater) {
        this.filledWithWater = filledWithWater;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.filledWithWater){
            g.setColor(new Color(0x1E7EEA));
        }else{
            g.setColor(new Color(0x888181));
        }
        System.out.println(getWidth() + " toto je sirka");
        g.drawOval(getWidth() /4, getHeight() /5, getWidth()/2, getHeight()/2+10);
        g.fillOval(getWidth() /4, getHeight() /5, getWidth()/2, getHeight()/2+10);

//        g.setColor(new Color(0x0132FF));
        g.fillRect(getWidth() / 3, getHeight() / 4 + 10, getWidth() / 2 + 18, getHeight() / 4);

    }
}
