package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;

    private int currentBoardSize;
    private final JFrame mainGame;
    @Getter
    private Board currentBoard;
    private int level;
    @Getter
    private final JLabel label;

    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        this.level = 1;
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
        updateMainGame();
    }

    private void updateMainGame() {
        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.requestFocus();
    }

    public void gameRestart() {
        this.level = 1;
        resetAndInitializeNewBoard();
    }

    private void resetAndInitializeNewBoard() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateInformationLabel();
        updateMainGame();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                break;
            case KeyEvent.VK_ENTER:
                if (this.currentBoard.checkWin()) {
                    this.level++;
                    resetAndInitializeNewBoard();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int previousBoardSize = this.currentBoardSize;
            this.currentBoardSize = source.getValue();

            if (previousBoardSize != this.currentBoardSize) {
                this.gameRestart();
            }
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getPoint());
        if (!(current instanceof Tile)) {
            this.currentBoard.repaint();
            return;
        }
        ((Tile) current).setHighlight(true);
        this.currentBoard.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Component current = currentBoard.getComponentAt(e.getPoint());
        if (current instanceof Tile) {
            ((Tile) current).rotate();
            ((Tile) current).setHighlight(true);
        }
        this.currentBoard.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.currentBoard.checkWin()) {
            this.level++;
            resetAndInitializeNewBoard();
        }
    }

}


