package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.BentPipe;
import sk.stuba.fei.uim.oop.tile.StartEnd;
import sk.stuba.fei.uim.oop.tile.StraightPipe;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Board extends JPanel {
    private final int fieldSize;
    private Random random;
    private Node startNode;
    private Node finishNode;
    private Tile[][] board;
    private Set<Node> visitedNodes;
    private List<Node> correctPath;


    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.random = new Random();
        this.visitedNodes = new HashSet<>();
        this.correctPath = new ArrayList<>();
        this.initializeBoard(this.fieldSize);
        this.setBackground(new Color(0xE380FF));
    }


    private void initializeBoard(int fieldSize) {
        this.board = new Tile[fieldSize][fieldSize];
        this.setLayout(new GridLayout(fieldSize, fieldSize));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                this.board[i][j] = new Tile();
            }
        }

        createStartAndFinish(fieldSize);
        generatePath(startNode, finishNode);


        System.out.println("Konceny put: ");
        this.correctPath.stream().forEach((e) -> {
            System.out.print(e.x + " " + e.y + ", ");
        });
        System.out.println();
        
        Node startNode = correctPath.get(correctPath.size() - 1);
        Node finishNode = correctPath.get(0);


        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                boolean isBentPipe = false;

                // Проверяем, находится ли текущая ячейка на пути
                Node currentNode = new Node(i, j);
                boolean isOnPath = this.correctPath.contains(currentNode);
                if (isOnPath) {
                    // Проверяем, является ли текущая ячейка точкой поворота пути
                    int index = this.correctPath.indexOf(currentNode);
                    boolean isBend = false;
                    if (index > 0 && index < this.correctPath.size() - 1) {
                        Node prevNode = this.correctPath.get(index - 1);
                        Node nextNode = this.correctPath.get(index + 1);
                        if ((prevNode.x == currentNode.x && nextNode.y == currentNode.y)
                                || (prevNode.y == currentNode.y && nextNode.x == currentNode.x)) {
                            isBend = true;
                        }
                    }

                    // Создаем соответствующий объект трубы в зависимости от того, находится ли точка на повороте
                    if (currentNode.equals(startNode)) {
                        this.board[i][j] = new StartEnd(true);
                    } else if (currentNode.equals(finishNode)) {
                        this.board[i][j] = new StartEnd(false);
                    } else if (isBend) {
                        this.board[i][j] = new BentPipe();
                    } else {
                        this.board[i][j] = new StraightPipe();
                    }

                    isBentPipe = true;
                }
                if (!isBentPipe) {
                    this.board[i][j] = new Tile();
                }
                this.add(this.board[i][j]);
            }
        }
    }

    private void createStartAndFinish(int fieldSize) {
        int randomStartRow = random.nextInt(fieldSize);
        int randomFinishRow = random.nextInt(fieldSize);
        this.startNode = new Node(randomStartRow, 0);
        this.finishNode = new Node(randomFinishRow, fieldSize - 1);
    }


    private boolean generatePath(Node current, Node finishNode) {
        if (!isValidMove(current) || this.visitedNodes.contains(current)) {
            return false;
        }
        this.visitedNodes.add(current);

        if (current.x == finishNode.x && current.y == finishNode.y) {
            connectNodes(current);
            return true;
        }

        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction currentDirection : directions) {
            Node next = move(current, currentDirection);

            if (isValidMove(next) && !this.visitedNodes.contains(next)) {
                if (generatePath(next, finishNode)) {
                    connectNodes(current);
                    return true;
                }
            }
        }

        return false;
    }


    private Node move(Node current, Direction direction) {
        int newX = current.x;
        int newY = current.y;

        switch (direction) {
            case LEFT:
                newY -= 1;
                break;
            case RIGHT:
                newY += 1;
                break;
            case UP:
                newX -= 1;
                break;
            case DOWN:
                newX += 1;
                break;
        }

        return new Node(newX, newY);
    }

    private boolean isValidMove(Node node) {
        return node.x >= 0 && node.x < fieldSize && node.y >= 0 && node.y < fieldSize;
    }

    private void connectNodes(Node current) {
        this.correctPath.add(current);
    }

}
