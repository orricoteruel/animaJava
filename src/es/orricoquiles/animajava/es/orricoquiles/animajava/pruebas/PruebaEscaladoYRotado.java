package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaEscaladoYRotado {
    public static void main(String[] args) {
        Configuracion.setFps(60);
        Configuracion.setResolucion(Visualizacion.MEDIUM);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico texto = new ClipGrafico(new Rectangulo(400, 20));
        //ClipGrafico texto = new ClipGrafico(new Texto("Texto rotado y para escalar"));
        texto.colorize(Color.ORANGE)
                .setPosCentro()
                .escala(3);

        ObjetoAnimado textoAnimado = new ObjetoAnimado(texto, 0, 6);
        textoAnimado.addTransformacion(new TransformacionRotacion(90, 0, 6, Interpolacion.LINEAR));
        //textoAnimado.fadeIn();
        textoAnimado.addTransformacion(new TransformacionEscala(5, 0, 6, Interpolacion.FRENAR));
        //textoAnimado.fadeOut();
        animacion.anyade(textoAnimado);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();

    }
}
