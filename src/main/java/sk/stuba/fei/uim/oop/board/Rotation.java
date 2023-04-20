package sk.stuba.fei.uim.oop.board;

public enum Rotation {
    DEGREES_0,
    DEGREES_90,
    DEGREES_180,
    DEGREES_270;

    public static Rotation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
