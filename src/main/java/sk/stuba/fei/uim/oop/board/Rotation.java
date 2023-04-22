package sk.stuba.fei.uim.oop.board;

public enum Rotation {
    DEGREES_0,
    DEGREES_90,
    DEGREES_180,
    DEGREES_270;

    public Rotation randomRotation() {
        int randomIndex = (int) (Math.random() * values().length);
        return (values()[randomIndex]);
    }

    public Rotation next() {
        return (values()[(this.ordinal() + 1) % values().length]);
    }

    public int getAngle() {
        return (this.ordinal() * 90);
    }

}
