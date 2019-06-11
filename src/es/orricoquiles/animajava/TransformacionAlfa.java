package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransformacionAlfa extends Transformacion {
    boolean establecidoOriginal = false;
    BufferedImage original;
    int alfaInicial;
    int alfaFinal;

    public TransformacionAlfa(int alfaInicial, int alfaFinal, double inicio, double duracion, Interpolacion interpolacion) {
        super(inicio, duracion, interpolacion);
        this.alfaInicial = alfaInicial;
        this.alfaFinal = alfaFinal;
    }

    @Override
    public void transforma(Sprite sprite, int frameActual) {
        if (!establecidoOriginal) {
            original = sprite.getImagen();
            establecidoOriginal = true;
        }
        double alfa = interpola(alfaInicial, alfaFinal, frameActual);
        //System.out.println(alfa);
        alfa = Math.max(0, alfa);
        alfa = Math.min(255, alfa);
        BufferedImage conAlfa = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics2D g2d = conAlfa.createGraphics();
        alfa = alfa / 255;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float) alfa));
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();
        sprite.setImagen(conAlfa);
    }
}
