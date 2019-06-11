package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaEscaladoYRotado {
    public static void main(String[] args) {
        Configuracion.setFps(60);
        Configuracion.setResolucion(Visualizacion.MEDIUM);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico texto = new ClipGrafico(new Texto("Texto rotado y para escalar"));
        texto.colorize(Color.ORANGE)
                .setPosCentro()
                .escala(1);

        ObjetoAnimado textoAnimado = new ObjetoAnimado(texto, 0, 20);
        textoAnimado.fadeIn()
                .fadeOut();
        textoAnimado.addTransformacion(new TransformacionEscala(5, 0, 4, Interpolacion.FRENAR));
        textoAnimado.addTransformacion(new TransformacionRotacion(90, 3, 2, Interpolacion.LINEAR));
        animacion.anyade(textoAnimado);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();

    }
}
