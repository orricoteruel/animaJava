package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaDesplazamiento {
    public static void main(String[] args) {
        Configuracion.setFps(60);
        Configuracion.setResolucion(Visualizacion.SCRATCH);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico texto = new ClipGrafico(new DibujoPNG("img\\computer.png"));
        texto.colorize(Color.ORANGE)
                .setPos(600, 600);

        ObjetoAnimado textoAnimado = new ObjetoAnimado(texto, 0, 20);
        textoAnimado.addTransformacion(new TransformacionDesplazamiento(2, 4, 300, 300, Interpolacion.LINEAR))
                .addTransformacion(new TransformacionDesplazamiento(5, 2, 20, -300, Interpolacion.ACELERAFRENA))
                .addTransformacion(new TransformacionDesplazamiento(8, 3, -300, 300, Interpolacion.MUELLE))
                .addTransformacion(new TransformacionDesplazamiento(12, 2, 300, 300, Interpolacion.PASOSUAVE));
        animacion.anyade(textoAnimado);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();

    }
}
