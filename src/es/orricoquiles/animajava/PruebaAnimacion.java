package es.orricoquiles.animajava;

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
                .rota(45);

        ClipGrafico imagen = new ClipGrafico(new PNG("img\\computer.png"));
        imagen.setPos(800, 300)
                .escala(0.30)
                .rota(-20);



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
