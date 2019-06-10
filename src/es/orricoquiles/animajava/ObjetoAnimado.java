package es.orricoquiles.animajava;


import java.util.ArrayList;
import java.util.List;

import static es.orricoquiles.animajava.Constantes.*;


public class ObjetoAnimado {


    Sprite sprite;

    int frameInicial;
    int frameFinal;

    private List<Transformacion> transformaciones = new ArrayList<>();

    public ObjetoAnimado(ClipGrafico clipGrafico, int frameInicial, double duracion) {
        if(clipGrafico.getImagen()==null) {
            clipGrafico.actualizaRender();
        }
        this.sprite = new Sprite(clipGrafico.getImagen(), clipGrafico.getX(), clipGrafico.getY());
        this.frameInicial = frameInicial;
        this.frameFinal = (int) Math.round(frameInicial + duracion * Configuracion.getFps() - 1);
    }

    public void pintate(Animacion animacion, int frameActual) {
        for (Transformacion t : transformaciones) {
            if ((this.frameInicial + t.frameInicial) <= frameActual && frameActual <= (this.frameInicial + t.frameFinal)) {
                //System.out.println(this.frameInicial + " " + t.frameInicial);
                t.transforma(sprite, frameActual - this.frameInicial);
            }
        }

        if (animacion.getRelacion() == 1) {
            animacion.getGraficos().drawImage(sprite.getImagen(), sprite.getX(), sprite.getY(), null);
        } else {

            int xReal = (int) Math.round(sprite.getX() * animacion.getRelacion());
            int yReal = (int) Math.round(sprite.getY() * animacion.getRelacion());
            int anchoReal = (int) Math.round(sprite.getImagen().getWidth() * animacion.getRelacion());
            int altoReal = (int) Math.round(sprite.getImagen().getHeight() * animacion.getRelacion());
            //System.out.println(xReal + "," + yReal + "   " + anchoReal + "x" + altoReal);
            animacion.getGraficos().drawImage(sprite.getImagen(), xReal, yReal, anchoReal, altoReal, null);
        }
    }


    /////////////////////////////////////////////
    // ANIMACIONES PREDEFINIDAS //
    //////////////////////////////

    public ObjetoAnimado fadeIn() {
        this.addTransformacion(new TransformacionAlfa(0, 255, 0, DURACION_FADE, INTERPOLACION_FADEIN));
        return this;
    }

    public ObjetoAnimado fadeOut() {
        int duracion = Math.round((frameFinal - frameInicial + 1) / Configuracion.getFps());
        System.out.println("Duracion total " + duracion);
        this.addTransformacion(new TransformacionAlfa(255, 0, duracion - DURACION_FADE, DURACION_FADE, INTERPOLACION_FADEOUT));
        return this;
    }


    public ObjetoAnimado addTransformacion(Transformacion transformacion) {
        this.transformaciones.add(transformacion);
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
