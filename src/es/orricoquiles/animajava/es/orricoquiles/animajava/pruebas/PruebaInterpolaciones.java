package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

public class PruebaInterpolaciones {
    public static void main(String[] args) {
        Configuracion.setVisualizacion(Visualizacion.SCRATCH);
        Configuracion.setFps(60);
        Animacion animacion = Animacion.getInstance();

        Interpolacion[] interpolaciones = Interpolacion.values();
        ClipGrafico[] ordenadores = new ClipGrafico[interpolaciones.length];
        for (int i = 0; i < ordenadores.length; i++) {
            ordenadores[i] = new ClipGrafico(new DibujoPNG("img\\computer.png"));
            ordenadores[i]
                    .escala(0.1)
                    .setPos(100, 100 + 200 * i);
        }

        ObjetoAnimado[] ordenadoresAnimados = new ObjetoAnimado[ordenadores.length];
        for (int i = 0; i < ordenadoresAnimados.length; i++) {
            ordenadoresAnimados[i] = new ObjetoAnimado(ordenadores[i], 0, 7);
            ordenadoresAnimados[i]
                    .addTransformacion(new TransformacionDesplazamiento(1, 2, 1200, 0, interpolaciones[i]))
                    .addTransformacion(new TransformacionDesplazamiento(4, 2, -1200, 0, interpolaciones[i]));
            animacion.anyade(ordenadoresAnimados[i]);
        }

        animacion.generaFrames();
        animacion.generaVideo();
    }
}
