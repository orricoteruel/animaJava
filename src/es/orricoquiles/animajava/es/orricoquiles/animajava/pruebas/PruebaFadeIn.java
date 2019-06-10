package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaFadeIn {
    public static void main(String[] args) {
        Configuracion.setFps(15);
        Configuracion.setResolucion(Visualizacion.SCRATCH);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico texto = new ClipGrafico(new Texto("Direccionamiento IP"));
        texto.colorize(Color.ORANGE)
                .escala(4)
                .setPosCentro();

        ObjetoAnimado textoAnimado = new ObjetoAnimado(texto, 0, 8);
        ObjetoAnimado textoAnimado2 = new ObjetoAnimado(texto, 0, 6);
        textoAnimado.fadeIn()
                .addTransformacion(new TransformacionDesplazamiento(0, 6, 0, -600, Interpolacion.MUELLE));
        textoAnimado2.addTransformacion(new TransformacionDesplazamiento(0, 2, 300, -300, Interpolacion.ACELERAFRENA))
                .addTransformacion(new TransformacionDesplazamiento(2, 4, -300, 0, Interpolacion.ANTICIPAR))
                .fadeOut();
        animacion.anyade(textoAnimado);
        animacion.anyade(textoAnimado2);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
    }
}
