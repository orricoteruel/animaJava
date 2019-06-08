package es.orricoquiles.animajava;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ClipGrafico {

  private Renderizable renderizable;

  private BufferedImage imagen;

  private int x;
  private int y;
  private Color color=Color.WHITE;

  public ClipGrafico(Renderizable renderizable) {
    this.renderizable=renderizable;
  }

  public void actualizaRender() {
    imagen=renderizable.getBufferedImage(this);
  }

  public ClipGrafico rota(double angulo){
    double rads = Math.toRadians(angulo);
    double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
    int w = imagen.getWidth();
    int h = imagen.getHeight();
    int newWidth = (int) Math.floor(w * cos + h * sin);
    int newHeight = (int) Math.floor(h * cos + w * sin);

    BufferedImage rotated = new BufferedImage(newWidth, newHeight, imagen.getType());
    Graphics2D g2d = rotated.createGraphics();
    AffineTransform at = new AffineTransform();
    at.translate((newWidth - w) / 2, (newHeight - h) / 2);

    int x = w / 2;
    int y = h / 2;

    at.rotate(rads, x, y);
    g2d.setTransform(at);
    g2d.drawImage(imagen, 0, 0, null);
    g2d.setColor(Color.RED);
    g2d.dispose();
    imagen=rotated;
    return this;
  }

  public ClipGrafico escala(double factor){
    if(imagen==null){
      actualizaRender();
    }
    int newWidth = (int)Math.round(imagen.getWidth() * factor);
    int newHeight = (int)Math.round(imagen.getHeight() * factor);
    BufferedImage resized = new BufferedImage(newWidth, newHeight, imagen.getType());
    Graphics2D g = resized.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(imagen, 0, 0, newWidth, newHeight, 0, 0, imagen.getWidth(),
            imagen.getHeight(), null);
    g.dispose();
    imagen=resized;
    return this;
  }




  public ClipGrafico setColor(Color color) {
    this.color = color;
    return this;
  }

  public Color getColor() {
    return color;
  }

  public ClipGrafico setPos(int x, int y) {
    this.x = x;
    this.y=y;
    return this;
  }

  public BufferedImage getImagen() {
    return imagen;
  }

  public Renderizable getRenderizable() {
    return renderizable;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
