package sk.stuba.fei.uim.oop.board;

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
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }


}
