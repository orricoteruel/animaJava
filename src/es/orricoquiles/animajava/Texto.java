package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Texto implements Renderizable {

    private static final int TAMANYO_FUENTE = 64;
    private final String texto;
    private Font fuente=new Font("Georgia", Font.PLAIN, TAMANYO_FUENTE);


    public Texto(String texto) {
        this.texto=texto;
    }

    public Texto(String texto,Font fuente){
        this.texto=texto;
        this.fuente=fuente;
    }

    @Override
    public BufferedImage getBufferedImage(ClipGrafico clipGrafico) {
        FontRenderContext frc3 = new FontRenderContext(fuente.getTransform(), true, true);
        Rectangle2D bounds=fuente.getStringBounds(this.texto,frc3);
        BufferedImage bi=new BufferedImage((int)bounds.getWidth(),(int)bounds.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=bi.createGraphics();
//        g.setColor(Color.ORANGE);
//        g.drawRect(0,0,bi.getWidth()-1,bi.getHeight()-1);
        g.setPaint(clipGrafico.getColor());
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.setFont(fuente);

        FontRenderContext frc = g.getFontRenderContext();
        LineMetrics lm = fuente.getLineMetrics(this.texto, frc);
        int width = bi.getWidth();
        int height = bi.getHeight();
        float textWidth = (float)fuente.getStringBounds(this.texto, frc).getWidth();
        float x = (width - textWidth)/2;
        float y = (height + lm.getHeight())/2 - lm.getDescent();
        g.drawString(this.texto, x, y);
        return bi;
    }
}
