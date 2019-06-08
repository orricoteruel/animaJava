package es.orricoquiles.animajava;

public enum Visualizacion {
  YOUTUBE(3840,2160),
  YOUTUBELOW(1280,760),
  SCRATCH(426,240),
  MEDIUM(854,480);

  private final int ancho;
  private final int alto;

  public int getAncho() {
    return ancho;
  }

  public int getAlto() {
    return alto;
  }

  Visualizacion(int ancho, int alto) {
    this.ancho=ancho;
    this.alto=alto;
  }
}
