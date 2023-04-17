package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.BentPipe;
import sk.stuba.fei.uim.oop.tile.StartEnd;
import sk.stuba.fei.uim.oop.tile.StraightPipe;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

public class Board extends JPanel {
    Random random;
    private Tile[][] board;
    private int fieldSize;

    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.random = new Random();
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


        creatingStart(fieldSize);
        creatingFinish(fieldSize);

    }

    private void creatingStart(int fieldSize) {
        int randomStartRow = random.nextInt(fieldSize);
        this.board[randomStartRow][0] = new StartEnd(true);
        this.remove(randomStartRow * fieldSize);
        this.add(this.board[randomStartRow][0], randomStartRow * fieldSize);
    }


    private void creatingFinish(int fieldSize) {
        int randomFinishRow = random.nextInt(fieldSize);
        this.board[randomFinishRow][fieldSize - 1] = new StartEnd(false);

//        this.board[randomRow][fieldSize - 1].rotate();
//        this.board[randomRow][fieldSize - 1].rotate();

        IntStream.range(0, 2)
                .forEach(i -> this.board[randomFinishRow][fieldSize - 1].rotate());

        this.remove(randomFinishRow * fieldSize + (fieldSize - 1));
        this.add(this.board[randomFinishRow][fieldSize - 1], randomFinishRow * fieldSize + (fieldSize - 1));

    }


}
