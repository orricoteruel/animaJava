package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaFadePNG {
    public static void main(String[] args) {
        Configuracion.setFps(15);
        Configuracion.setResolucion(Visualizacion.MEDIUM);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico PNG = new ClipGrafico(new DibujoPNG("img\\computer.png"))
                .colorize(Color.PINK)
                .setPosCentro()
                .escala(2);

        ObjetoAnimado ObjetoPNG = new ObjetoAnimado(PNG, 0, 4);
        ObjetoPNG.fadeIn();
        animacion.anyade(ObjetoPNG);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
    }
}
