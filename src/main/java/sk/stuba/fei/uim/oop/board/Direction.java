package sk.stuba.fei.uim.oop.board;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;


    public Node move(Node node) {
        int newX = node.x;
        int newY = node.y;

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

}
