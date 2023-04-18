package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.StartEnd;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

public class Board extends JPanel {
    private final int fieldSize;
    private Random random;
    private Node startNode;
    private Node finishNode;
    private Tile[][] board;
    private Set<Node> visitedNodes;

    private List<Node> path;
    private List<Node> correctPath;


    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.random = new Random();
        this.visitedNodes = new HashSet<>();
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


        creatingStart(fieldSize);
        creatingFinish(fieldSize);
        this.path = new ArrayList<>();
        this.correctPath = new ArrayList<>();
        generatePath(startNode, finishNode);

        System.out.println("Konceny put: " );
        this.correctPath.stream().forEach((e) -> {
            System.out.print(e.x + " " + e.y + ", ");

        });
    }


// аботающий
    private boolean generatePath(Node current, Node finishNode) {
        if (!isValidMove(current) || this.visitedNodes.contains(current)) {
            return false;
        }
        this.visitedNodes.add(current);

        if (current.x == finishNode.x && current.y == finishNode.y) {
            return true;
        }

        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction currentDirection : directions) {
            Node next = move(current, currentDirection);

            if (isValidMove(next) && !this.visitedNodes.contains(next)) {
//                connectNodes(current, next);

                if (generatePath(next, finishNode)) {
                    connectNodes(current, next);
                    return true;
                }
            }
        }

        return false;
    }

    //старый первый
//    private boolean generatePath(Node current, Node finishNode) {
//
//
//        if (isValidMove(current) && this.visitedNodes.contains(current)) {
//            return false;
//        }
//        this.visitedNodes.add(current);
//
//        List<Direction> directions = Arrays.asList(Direction.values());
//        Collections.shuffle(directions);
//
//        for (Direction currentDirection : directions) {
//            Node next = move(current, currentDirection);
//
//            if (isValidMove(next) && !this.visitedNodes.contains(next)) {
//
//                connectNodes(current, next);
//
//                if (next.x == finishNode.x && next.y == finishNode.y) {
//                    return true;
//                }
//                generatePath(next, finishNode);
//                return true;
//
//            }
//
//        }
//
//        return false;
//    }


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


//    private void connectNodes(Node current, Node next) {
//        this.path.add(current);
//        this.path.add(next);
//        if (this.correctPath != null && this.path.contains(next)) {
//            this.correctPath.add(current);
//            this.correctPath.add(next);
//        }
//    }

    public void drawPath(Graphics g) {
        if (this.correctPath == null) {
            return;
        }

        g.setColor(Color.YELLOW);
        for (int i = 0; i < this.correctPath.size() - 1; i += 2) {
            Node node1 = this.correctPath.get(i);
            Node node2 = this.correctPath.get(i + 1);
            g.drawLine(node1.x, node1.y, node2.x, node2.y);
        }
    }

    private void connectNodes(Node current, Node next) {
        System.out.println(current.x + " " + current.y);
        this.correctPath.add(current);
        board[current.x][current.y].setBackground(Color.RED);
        board[next.x][next.y].setBackground(Color.MAGENTA);
    }


    private void creatingStart(int fieldSize) {
        int randomStartRow = random.nextInt(fieldSize);
        this.board[randomStartRow][0] = new StartEnd(true);
        this.remove(randomStartRow * fieldSize);
        this.add(this.board[randomStartRow][0], randomStartRow * fieldSize);
        this.startNode = new Node(randomStartRow, 0);
    }


    private void creatingFinish(int fieldSize) {
        int randomFinishRow = random.nextInt(fieldSize);
        this.board[randomFinishRow][fieldSize - 1] = new StartEnd(false);

        IntStream.range(0, 2)
                .forEach(i -> this.board[randomFinishRow][fieldSize - 1].rotate());

        this.remove(randomFinishRow * fieldSize + (fieldSize - 1));
        this.add(this.board[randomFinishRow][fieldSize - 1], randomFinishRow * fieldSize + (fieldSize - 1));

        this.finishNode = new Node(randomFinishRow, fieldSize - 1);

    }

}
