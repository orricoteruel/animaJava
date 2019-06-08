package es.orricoquiles.animajava;

import javax.sound.sampled.Clip;
import java.awt.*;

public class PruebaAnimacion {
    public static void main(String[] args) {
        Animacion animacion=Animacion.getInstance(Visualizacion.YOUTUBE);
        ClipGrafico texto=new ClipGrafico(new Texto("Prueba con Renderizable"));
        texto.setColor(Color.ORANGE)
                .setPos(600,600);
        ClipGrafico texto2=new ClipGrafico(new Texto("Una segunda prueba, Escalado"));
        texto2.setColor(Color.BLUE)
                .setPos(600,1200)
                .escala(1.85)
                .rota(30);


        ObjetoAnimado textoAnimado=new ObjetoAnimado(texto,0,1);
        ObjetoAnimado textoAnimado2=new ObjetoAnimado(texto2,0,1);
        animacion.anyade(textoAnimado);
        animacion.anyade(textoAnimado2);
        animacion.generaFrames();
        System.out.println("Generando Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();
    }



}
