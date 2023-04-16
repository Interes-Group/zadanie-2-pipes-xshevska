package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.BentPipe;
import sk.stuba.fei.uim.oop.tile.StartEnd;
import sk.stuba.fei.uim.oop.tile.StraightPipe;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Tile[][] board;
    private int fieldSize;

    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.initializeBoard(this.fieldSize);
        this.setBackground(new Color(0xE380FF));
    }


    private void initializeBoard(int fieldSize) {
        this.board = new Tile[fieldSize][fieldSize];
        this.setLayout(new GridLayout(fieldSize, fieldSize));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                this.board[i][j] = new Tile();
                this.add(this.board[i][j]);
            }
        }

        this.board[fieldSize / 2 - 1][fieldSize / 2] = new BentPipe();
        this.board[fieldSize / 2][fieldSize / 2] = new StraightPipe();

        this.remove((fieldSize / 2 - 1) * fieldSize + fieldSize / 2);
        this.remove((fieldSize / 2) * fieldSize + fieldSize / 2);
        this.add(this.board[fieldSize / 2 - 1][fieldSize / 2], (fieldSize / 2 - 1) * fieldSize + fieldSize / 2);
        this.add(this.board[fieldSize / 2][fieldSize / 2], (fieldSize / 2) * fieldSize + fieldSize / 2);

        this.board[0][0] = new StartEnd(true);
        this.remove((0) * fieldSize + 0);
        this.add(this.board[0][0], 0);

        this.board[fieldSize / fieldSize][fieldSize - 1] = new StartEnd(false);
        this.remove((fieldSize / fieldSize) * fieldSize + fieldSize - 1);
        this.add(this.board[fieldSize / fieldSize][fieldSize - 1], (fieldSize / fieldSize) * fieldSize + fieldSize - 1);


        //6 7

        this.board[fieldSize - 2][fieldSize - 1] = new StraightPipe();
        this.remove((fieldSize - 2) * fieldSize + fieldSize - 1);
        this.add(this.board[fieldSize - 2][fieldSize - 1], (fieldSize - 2) * fieldSize + fieldSize - 1);

        // Обновить отображение доски после добавления элементов
//        this.revalidate();
//        this.repaint();
    }


}
