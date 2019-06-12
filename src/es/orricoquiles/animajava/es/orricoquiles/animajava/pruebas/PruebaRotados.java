package es.orricoquiles.animajava.es.orricoquiles.animajava.pruebas;

import es.orricoquiles.animajava.*;

import java.awt.*;

public class PruebaRotados {
    public static void main(String[] args) {
        Configuracion.setFps(60);
        Configuracion.setVisualizacion(Visualizacion.YOUTUBELOW);
        Animacion animacion = Animacion.getInstance();

        ClipGrafico texto = new ClipGrafico(new Texto("Línea de texto muy larga"));
        texto.colorize(Color.BLUE).escala(2)
                .setPos(200, 200);
        ClipGrafico texto2 = new ClipGrafico(new Texto("Línea de texto muy larga"));
        texto2.colorize(Color.RED).escala(2)
                .setPos(200, 200);

        ObjetoAnimado textoAnimado = new ObjetoAnimado(texto, 0, 4);
        ObjetoAnimado textoAnimado2 = new ObjetoAnimado(texto2, 0, 4);
        textoAnimado2.addTransformacion(new TransformacionRotacion(15, 0, 4, Interpolacion.LINEAR));
        animacion.anyade(textoAnimado);
        animacion.anyade(textoAnimado2);
//        ClipGrafico[] textos=new ClipGrafico[30];
//        for (int i = 0; i < textos.length; i++) {
//            textos[i]=new ClipGrafico(new Texto("Prueba de texto larga"));
//            textos[i].colorize(Color.green).escala(1.6);
//            textos[i].rota(i*12);
//            textos[i].setPos(-100,(i-5)*20);
//        }
//
//
//
//
//
//        ObjetoAnimado[] textoAnimados=new ObjetoAnimado[textos.length];
//        for (int i = 0; i < textoAnimados.length; i++) {
//            textoAnimados[i]=new ObjetoAnimado(textos[i],i*Configuracion.getFps(),4);
//            textoAnimados[i].fadeOut().fadeIn();
//            animacion.anyade(textoAnimados[i]);
//        }
        animacion.generaFrames();
        System.out.println("Generando el Video");
        animacion.generaVideo();
        //animacion.reproduceVideo();
    }


}
