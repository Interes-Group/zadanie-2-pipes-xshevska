package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.tile.PipeTile;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int FIRST_LEVEL = 1;
    public static final int SECOND_LEVEL = 2;
    public static final int THIRD_LEVEL = 3;


    public static final int INITIAL_BOARD_SIZE = 8;
    private int currentBoardSize;
    private JFrame mainGame;
    private Board currentBoard;
    private int level;
    @Getter
    private JLabel label;


    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        this.level = FIRST_LEVEL;
        this.currentBoardSize = INITIAL_BOARD_SIZE;

        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);

        this.label = new JLabel();
        this.updateInformationLabel();
    }

    private void initializeNewBoard(int fieldSize) {
        this.currentBoard = new Board(fieldSize);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }


    private void updateInformationLabel() {
        this.label.setText(" LEVEL : " + this.level + "  BOARD SIZE : " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateInformationLabel();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("TO DO");
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateInformationLabel();

        this.gameRestart();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("MOVED");
        Component current = this.currentBoard.getComponentAt(e.getPoint());
        if (!(current instanceof Tile)) {
            return;
        }
        ((Tile) current).setHighlight(true);
        System.out.println("Должно рисовать");
        this.currentBoard.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.println("PRESSSED");
        Component current = currentBoard.getComponentAt(e.getPoint());
        if (current instanceof PipeTile) {
            ((PipeTile) current).rotate();
        }
    }
}
