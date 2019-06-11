package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TransformacionRotacion extends Transformacion {
    private final double anguloInicial = 0;
    private final double anguloFinal;

    boolean establecidoOriginal = false;
    BufferedImage original;


    public TransformacionRotacion(double anguloFinal, double inicio, double duracion, Interpolacion interpolacion) {
        super(inicio, duracion, interpolacion);
        this.anguloFinal = anguloFinal;
    }

    @Override
    public void transforma(Sprite sprite, int frameActual) {
        if (!establecidoOriginal) {
            original = sprite.getImagen();
            establecidoOriginal = true;
        }

        double angulo = interpola(anguloInicial, anguloFinal, frameActual);
        System.out.println("Ángulo:" + angulo);

        double rads = Math.toRadians(-1 * angulo);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = sprite.getImagen().getWidth();
        int h = sprite.getImagen().getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        System.out.println("Rotando: " + newWidth + ", " + newHeight);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, sprite.getImagen().getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(sprite.getImagen(), 0, 0, null);
        g2d.dispose();
        sprite.setImagen(rotated);
    }
}
