package sk.stuba.fei.uim.oop.gui;


import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("WaterPipes!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
//        frame.getContentPane().setBackground(new Color(0xE8164A));

        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        GameLogic logic = new GameLogic(frame);
        frame.addKeyListener(logic);
        frame.add(createBottomMenu(logic), BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    private JPanel createBottomMenu(GameLogic logic) {
        JPanel bottomMenu = new JPanel();
        bottomMenu.setBackground(new Color(0x2AFFC4));
        bottomMenu.setLayout(new GridLayout(2, 2));


        bottomMenu.add(logic.getLabel());

        JButton buttonCheck = new JButton("CHECK WIN");
        buttonCheck.addActionListener(logic);
        buttonCheck.setFocusable(false);
        buttonCheck.setBackground(Color.cyan);
        bottomMenu.add(buttonCheck);


        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);
        buttonRestart.setBackground(Color.cyan);
        bottomMenu.add(buttonRestart);


        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(logic);
        bottomMenu.add(slider);

        return bottomMenu;
    }


}
