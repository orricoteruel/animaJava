package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaAnimacion {
    public static void main(String[] args) {
        Configuracion.setFps(60);
        Configuracion.setVisualizacion(Visualizacion.MEDIUM);
        Animacion animacion = Animacion.getInstance();
        ClipGrafico texto=new ClipGrafico(new Texto("Prueba con Renderizable"));
        texto.colorize(Color.ORANGE)
                .setPos(600,600);
        ClipGrafico texto2=new ClipGrafico(new Texto("Una segunda prueba, Escalado"));
        texto2.colorize(Color.BLUE)
                .setPos(600,1200)
                .rota(45);

        ClipGrafico imagen = new ClipGrafico(new DibujoPNG("img\\computer.png"));
        imagen.setPos(800, 300)
                .escala(0.30)
                .rota(65);



        ObjetoAnimado textoAnimado=new ObjetoAnimado(texto,0,1);
        ObjetoAnimado textoAnimado2=new ObjetoAnimado(texto2,0,1);
        ObjetoAnimado comp = new ObjetoAnimado(imagen, 0, 1.5);
        animacion.anyade(textoAnimado);
        animacion.anyade(textoAnimado2);
        animacion.anyade(comp);
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();
    }



}
