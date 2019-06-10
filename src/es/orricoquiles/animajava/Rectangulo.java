package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangulo implements Renderizable {
    int ancho;
    int alto;
    boolean relleno=true;
    int grosor=20;
    int curva=60;

    public Rectangulo(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public BufferedImage getBufferedImage(ClipGrafico clipGrafico) {
        BufferedImage bi=new BufferedImage(ancho+grosor*2,alto+grosor*2,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=bi.createGraphics();
//        g.colorize(Color.ORANGE);
//        g.drawRect(0,0,bi.getWidth()-1,bi.getHeight()-1);
        g.setPaint(Color.WHITE);
        BasicStroke stroke = new BasicStroke(grosor);
        g.setStroke(stroke);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.drawRoundRect(grosor,grosor,ancho-grosor,alto-grosor,curva,curva);
        return bi;
    }
}
