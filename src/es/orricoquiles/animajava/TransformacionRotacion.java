package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TransformacionRotacion extends Transformacion {
    private final double anguloInicial = 0;
    private final double anguloFinal;
    private double radsAnterior = 0;

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
        original = sprite.getImagen();

        double anguloAbsoluto = interpola(anguloInicial, anguloFinal, frameActual);
        System.out.println("Ángulo:" + anguloAbsoluto);

        double rads = Math.toRadians(-1 * anguloAbsoluto);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = original.getWidth();
        int h = original.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, original.getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(original, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.dispose();

        /*
        double radsAbsolutos = Math.toRadians(-1 * anguloAbsoluto);
        double sin = Math.sin(radsAbsolutos), cos = Math.cos(radsAbsolutos);
        int anchoOriginal = original.getWidth();
        int altoOriginal = original.getHeight();
        double anchoDestino = Math.abs(anchoOriginal * cos) + Math.abs(altoOriginal * sin);
        double altoDestino = Math.abs(altoOriginal * cos) + Math.abs(anchoOriginal * sin);
        System.out.println("Rotando, Destino: " + anchoDestino + ", " + altoDestino);

        BufferedImage rotated = new BufferedImage((int) Math.floor(anchoDestino), (int) Math.floor(altoDestino), sprite.getImagen().getType());
        Graphics2D g2d = rotated.createGraphics();
        g2d.drawRect(0,0,(int)Math.floor(anchoDestino-1),(int)Math.floor(altoDestino-1));
        AffineTransform at = new AffineTransform();
       // at.translate(-20, 30);

        int x = (int)Math.floor(anchoDestino/ 2);
        int y = (int)Math.floor(altoDestino / 2);
        //at.translate(x, y);
        System.out.println("La x y :" + x + ", " + y);

        at.rotate(radsAbsolutos-radsAnterior, 0, 0);
        radsAnterior=radsAbsolutos;
        g2d.setTransform(at);
        g2d.drawImage(sprite.getImagen(), x, y, null);
        g2d.dispose();*/
        sprite.setImagen(rotated);
    }
}
