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
    private final Random random;
    private final Set<Node> visitedNodes;
    private final List<Node> correctPath;
    private Node startNode;
    private Node finishNode;
    private Tile[][] board;


    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.random = new Random();
        this.visitedNodes = new HashSet<>();
        this.correctPath = new ArrayList<>();
        this.initializeBoard(this.fieldSize);
        this.setBackground(new Color(0xE380FF));
    }


    private void initializeBoard(int fieldSize) {
        this.board = createBoard(fieldSize);
        this.setLayout(new GridLayout(fieldSize, fieldSize));
        createStartAndFinish(fieldSize);
        generatePath(startNode, finishNode);
        printPath();
        fillBoardWithPath();
    }


    private Tile[][] createBoard(int fieldSize) {
        Tile[][] board = new Tile[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                board[i][j] = new Tile();
            }
        }
        return board;
    }

    public boolean checkWin() {
        boolean isPathFound = explorePath(startNode, finishNode);
        if (!isPathFound) {
            System.out.println("МЫ ОСТАНОВИЛИСЬ НА НОДЕ");
            return false;
        }
        return true;
    }


    public boolean explorePath(Node startNode, Node finishNode) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode.equals(finishNode)) {
                System.out.println("НАШЛИ ПУТЬ");
                return true;
            }

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                Tile currentTile = board[currentNode.x][currentNode.y];
                List<Direction> currentTileDirections = currentTile.getOpenDirections();
                List<Node> neighbors = getNeighbors(currentNode);

                for (Node neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        Tile neighborTile = board[neighbor.x][neighbor.y];
                        List<Direction> neighborTileDirections = neighborTile.getOpenDirections();

                        Direction directionToNeighbor = null;
                        for (Direction direction : Direction.values()) {
                            Node nodeInDirection = direction.move(currentNode);
                            if (nodeInDirection.equals(neighbor)) {
                                directionToNeighbor = direction;
                                break;
                            }
                        }


                        if (currentTileDirections.contains(directionToNeighbor) && neighborTileDirections.contains(directionToNeighbor.opposite())) {
                            if (neighborTile instanceof BentPipe) {
                                ((BentPipe) neighborTile).setState(State.WATER_PRESENT);
                            } else if (neighborTile instanceof StraightPipe) {
                                ((StraightPipe) neighborTile).setState(State.WATER_PRESENT);
                            } else if (neighborTile instanceof StartEnd) {
                                ((StartEnd) neighborTile).setState(State.WATER_PRESENT);
                            }
                            stack.push(neighbor);
                            neighborTile.setHighlight(true);
                            neighborTile.repaint();
                        }


                    }
                }
            }
        }
        return false;
    }


    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Node neighbor = direction.move(node);
            // Проверяем, что соседний узел в пределах допустимых координат
            if (isValidMove(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }


    private void printPath() {
        System.out.println("Put: ");
        this.correctPath.forEach(e -> System.out.print(e.x + " " + e.y + ", "));
        System.out.println();
    }

    private void fillBoardWithPath() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Node currentNode = new Node(i, j);
                boolean isOnPath = this.correctPath.contains(currentNode);
                if (isOnPath) {
                    Tile tile = createTileForPathNode(currentNode);
                    this.board[i][j] = tile;
                } else {
                    this.board[i][j] = new Tile();
                }
                this.add(this.board[i][j]);
            }
        }
    }

    private Tile createTileForPathNode(Node currentNode) {
        int index = this.correctPath.indexOf(currentNode);
        if (currentNode.equals(startNode)) {
            return new StartEnd(State.WATER_PRESENT);
        } else if (currentNode.equals(finishNode)) {
            return new StartEnd(State.NO_WATER);
        } else if (isBendNode(currentNode, index)) {
            return new BentPipe(State.NO_WATER);
        } else {
            return new StraightPipe(State.NO_WATER);
        }
    }

    private boolean isBendNode(Node currentNode, int index) {
        if (index > 0 && index < this.correctPath.size() - 1) {
            Node prevNode = this.correctPath.get(index - 1);
            Node nextNode = this.correctPath.get(index + 1);
            return (prevNode.x == currentNode.x && nextNode.y == currentNode.y)
                    || (prevNode.y == currentNode.y && nextNode.x == currentNode.x);
        }
        return false;
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
            Node next = currentDirection.move(current);

            if (isValidMove(next) && !this.visitedNodes.contains(next)) {
                if (generatePath(next, finishNode)) {
                    connectNodes(current);
                    return true;
                }
            }
        }



        return false;
    }


    private boolean isValidMove(Node node) {
        return node.x >= 0 && node.x < fieldSize && node.y >= 0 && node.y < fieldSize;
    }

    private void connectNodes(Node current) {
        this.correctPath.add(current);
    }

}