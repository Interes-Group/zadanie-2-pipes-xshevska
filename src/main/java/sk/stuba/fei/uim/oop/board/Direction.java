package sk.stuba.fei.uim.oop.board;

import java.util.stream.Stream;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;


    public Node move(Node node) {
        int newX = node.getX();
        int newY = node.getY();

        switch (this) {
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


    public Direction opposite() {
        return Stream.of(Direction.values())
                .filter(direction -> direction != this && isOpposite(direction))
                .findFirst()
                .orElse(null);
    }

    private boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN) || (this == DOWN && other == UP)
                || (this == LEFT && other == RIGHT) || (this == RIGHT && other == LEFT);
    }

}