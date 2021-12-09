package be.ecam.chess;

public class Config {
    private final boolean isUtf8;

    public Config(boolean isUtf8) {
        this.isUtf8 = isUtf8;
    }

    public boolean isUtf8() {
        return isUtf8;
    }
}
