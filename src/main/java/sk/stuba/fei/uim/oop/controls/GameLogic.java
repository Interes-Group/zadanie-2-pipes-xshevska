package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;

import javax.swing.*;

public class GameLogic extends UniversalAdapter {
    @Getter
    private JLabel boardSizeLabel;
    @Getter
    private JLabel label;

    public GameLogic(JFrame frame) {
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
    }
}
