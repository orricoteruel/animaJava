package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransformacionEscala extends Transformacion {
    private final double escalaInicialx = 1;
    private final double escalaInicialy = 1;
    private final double escalaFinalx;
    private final double escalaFinaly;
    boolean establecidoOriginal = false;
    BufferedImage original;

    public TransformacionEscala(double escalaFinal, double inicio, double duracion, Interpolacion interpolacion) {
        this(escalaFinal, escalaFinal, inicio, duracion, interpolacion);
    }

    public TransformacionEscala(double escalaFinalx, double escalaFinaly, double inicio, double duracion, Interpolacion interpolacion) {
        super(inicio, duracion, interpolacion);
        this.escalaFinalx = escalaFinalx;
        this.escalaFinaly = escalaFinaly;
    }

    @Override
    public void transforma(Sprite sprite, int frameActual) {
        if (!establecidoOriginal) {
            original = sprite.getImagen();
            establecidoOriginal = true;
        }

        double escalaX = interpola(escalaInicialx, escalaFinalx, frameActual);
        int newWidth = (int) Math.round(original.getWidth() * escalaX);
        double escalaY = interpola(escalaInicialy, escalaFinaly, frameActual);
        int newHeight = (int) Math.round(original.getHeight() * escalaY);

        //System.out.println(escalaX + ", " + escalaY);

        BufferedImage resized = new BufferedImage(newWidth, newHeight, original.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(sprite.getImagen(), 0, 0, newWidth, newHeight, 0, 0, sprite.getImagen().getWidth(),
                sprite.getImagen().getHeight(), null);
        g.dispose();
        //sprite.setImagen(resized);
    }
}
