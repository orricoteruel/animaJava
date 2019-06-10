package es.orricoquiles.animajava;

public enum FrameRate {
    SUAVE(60),
    PREVIEW(30);

    private final int fps;

    FrameRate(int i) {
        this.fps = i;
    }

    public int getFps() {
        return fps;
    }
}
