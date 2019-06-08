package es.orricoquiles.animajava;


import java.awt.image.BufferedImage;

import static es.orricoquiles.animajava.Constantes.FRAMESxSEGUNDO;

public class ObjetoAnimado {
    ClipGrafico clipGrafico;
    int frameInicial;
    int frameFinal;

    public ObjetoAnimado(ClipGrafico clipGrafico, int frameInicial, double duracion) {
        if(clipGrafico.getImagen()==null) {
            clipGrafico.actualizaRender();
        }
        this.clipGrafico = clipGrafico;
        this.frameInicial = frameInicial;
        this.frameFinal = (int) Math.round(frameInicial + duracion * FRAMESxSEGUNDO - 1);
    }

    public void pintate(Animacion animacion, int frameActual) {
        BufferedImage bi = this.clipGrafico.getImagen();


        if (animacion.getRelacion() == 1) {
            animacion.getGraficos().drawImage(bi, clipGrafico.getX(), clipGrafico.getY(), null);
        } else {

            int xReal = (int) Math.round((clipGrafico.getX()) * animacion.getRelacion());
            int yReal = (int) Math.round((clipGrafico.getY()) * animacion.getRelacion());
            int anchoReal = (int) Math.round(bi.getWidth() * animacion.getRelacion());
            int altoReal = (int) Math.round(bi.getHeight() * animacion.getRelacion());
            //System.out.println(xReal + "," + yReal + "   " + anchoReal + "x" + altoReal);
            animacion.getGraficos().drawImage(bi, xReal, yReal, anchoReal, altoReal, null);
        }
    }

    public ClipGrafico getClipGrafico() {
        return clipGrafico;
    }

    public ObjetoAnimado setClipGrafico(ClipGrafico clipGrafico) {
        this.clipGrafico = clipGrafico;
        return this;
    }

    public int getFrameInicial() {
        return frameInicial;
    }

    public ObjetoAnimado setFrameInicial(int frameInicial) {
        this.frameInicial = frameInicial;
        return this;
    }

    public int getFrameFinal() {
        return frameFinal;
    }

    public ObjetoAnimado setFrameFinal(int frameFinal) {
        this.frameFinal = frameFinal;
        return this;
    }

    public int despues() {
        return this.frameFinal+1;
    }
}
