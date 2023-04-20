package sk.stuba.fei.uim.oop.board;

import java.util.Random;

public enum Rotation {
    DEGREES_0,
    DEGREES_90,
    DEGREES_180,
    DEGREES_270;

    public static Rotation getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
