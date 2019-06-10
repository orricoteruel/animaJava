package es.orricoquiles.animajava;

import java.awt.image.BufferedImage;

public class Sprite {
    BufferedImage imagen;
    int x;
    int y;

    public Sprite(BufferedImage imagen, int x, int y) {
        this.imagen = imagen;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public Sprite setImagen(BufferedImage imagen) {
        this.imagen = imagen;
        return this;
    }

    public int getX() {
        return x;
    }

    public Sprite setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Sprite setY(int y) {
        this.y = y;
        return this;
    }
}
