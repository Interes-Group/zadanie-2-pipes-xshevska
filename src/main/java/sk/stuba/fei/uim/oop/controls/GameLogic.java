package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

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

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
//        this.currentBoard.addMouseMotionListener(this);
//        this.currentBoard.addMouseListener(this);
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
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateInformationLabel();

        this.gameRestart();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }


}
