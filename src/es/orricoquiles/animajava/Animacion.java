package es.orricoquiles.animajava;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static es.orricoquiles.animajava.Constantes.ALTO_YOUTUBE;
import static es.orricoquiles.animajava.Constantes.ANCHO_YOUTUBE;

public class Animacion {
    private static final String DIRECTORIO_VIDEO = "video";
    private int ancho = ANCHO_YOUTUBE;
    private int alto = ALTO_YOUTUBE;


    private static Animacion animacion = null;
    private Graphics2D graficos;

    public Graphics2D getGraficos() {
        return graficos;
    }

    private int frameActual = 0;
    private BufferedImage imagen;

    private BarraDialogo miBarra;
    private VentanaPreview preview;



    List<ObjetoAnimado> objetosActuales = new ArrayList<>();
    List<ObjetoAnimado> objetosEnCola=new ArrayList<>();



    public Animacion anyade(ObjetoAnimado objetoAnimado) {
        this.objetosEnCola.add(objetoAnimado);
        return this;
    }

    private Animacion() {
        this.ancho = Configuracion.getVisualizacion().getAncho();
        this.alto = Configuracion.getVisualizacion().getAlto();
        imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        System.out.println(this.info());
    }

    public static Animacion getInstance() {
        if (animacion == null) {
            System.out.println("Nueva es.orricoquiles.videoframework.Animacion");
            animacion = new Animacion();
            return animacion;
        } else {
            return animacion;
        }
    }

    public void generaFrames() {
        int maxFrame=0;
        for (ObjetoAnimado o: objetosEnCola) {
            if(o.frameFinal>maxFrame){
                maxFrame=o.frameFinal;
            }
        }
        System.out.println(maxFrame);
        miBarra = new BarraDialogo(maxFrame, objetosEnCola.size(), objetosEnCola.size());
        preview = new VentanaPreview();
        borraImagenesPrevias();

        while (!objetosActuales.isEmpty() || !objetosEnCola.isEmpty()) {
            //System.out.println("En Actual:" + objetosActuales.size() + " En cola:" + objetosEnCola.size());
            miBarra.actualiza(frameActual, objetosEnCola.size(), objetosActuales.size());

            //anyadeobjetosque toquen por el frame
            List<ObjetoAnimado> pasanAActuales=nuevosObjetos(objetosEnCola,frameActual);
            objetosActuales.addAll(pasanAActuales);
            objetosEnCola.removeAll(pasanAActuales);

            borraImagen();
            pintaFrameActual();
            muestraImagenEnVentana();
            guardaImagen();

            //borra objetosActuales que toquen por el frame
            objetosActuales.removeAll(objetosTerminados(objetosActuales,frameActual));
            frameActual++;
        }
        miBarra.termina();
        preview.termina();
    }

    private List<ObjetoAnimado> objetosTerminados(List<ObjetoAnimado> objetosActuales, int frameActual) {
        List<ObjetoAnimado> salida=new ArrayList<>();
        for(ObjetoAnimado o:objetosActuales){
            if(frameActual+1>o.getFrameFinal()){
                salida.add(o);
            }
        }
        return salida;
    }


    private List< ObjetoAnimado> nuevosObjetos(List<ObjetoAnimado> objetosEnCola, int frameActual) {
        List<ObjetoAnimado> salida=new ArrayList<>();
        for(ObjetoAnimado o:objetosEnCola){
            if(frameActual>=o.getFrameInicial()){
                salida.add(o);
            }
        }
        return salida;
    }

    private void pintaFrameActual() {
        for (ObjetoAnimado o :
                objetosActuales) {
            o.pintate(this,frameActual);
            //System.out.println(o.getXActual(frameActual) + ", " + o.getYActual(frameActual));
        }
    }

    private void borraImagenesPrevias() {
        File f = new File(DIRECTORIO_VIDEO);
        File[] ficheros = f.listFiles();
        System.out.println("Hay " + ficheros.length + " ficheros que serán borrados");
        for (File fich : ficheros) {
            fich.delete();
        }
    }

    private void borraImagen() {
        graficos = imagen.createGraphics();
        graficos.clearRect(0, 0, imagen.getWidth(), imagen.getHeight());
    }

    private void muestraImagenEnVentana() {

        preview.imagePanel.setImage(imagen);
        preview.imagePanel.repaint();
    }


    public int getFrameActual() {
        return frameActual;
    }

    public double getRelacion() {
        return this.alto / (double) ALTO_YOUTUBE;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    private void guardaImagen() {
        try {
//            if (animacion.getAncho() != ANCHO_YOUTUBE) {
//                BufferedImage imagenFinal = new BufferedImage(animacion.ancho, animacion.alto, BufferedImage.TYPE_INT_ARGB);
//                Graphics2D temporal = imagenFinal.createGraphics();
//                temporal.drawImage(imagen, 0, 0, ANCHO_YOUTUBE, ALTO_YOUTUBE, null);
//                ImageIO.write(imagenFinal, "DibujoPNG", new File("video\\im" + String.format("%04d", frameActual) + ".DibujoPNG"));
//            } else {
            ImageIO.write(imagen, "PNG", new File("video\\im" + String.format("%04d", frameActual) + ".png"));
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String info() {
        return "ANIMACION:" +
                "\n  ANCHO: " + this.ancho +
                "\n   ALTO: " + this.alto +
                "\n    FPS: " + Configuracion.getFps();
    }

    public void generaVideo() {
        String filePath = "output";
        File fileP = new File(filePath);
        String commands = "C:\\ffmpeg-4.1.3-win64-static\\bin\\ffmpeg -r "
                + Configuracion.getFps() + " -f image2 -i "
                + DIRECTORIO_VIDEO + "\\im%4d.png " + "video.mp4 -y";
        System.out.println(commands);
        List<String> args = new ArrayList<String>();
        args.add ("C:\\ffmpeg-4.1.3-win64-static\\bin\\ffmpeg"); // command name
        args.add ("-r"); // optional args added as separate list items
        args.add("" + Configuracion.getFps());
        args.add ("-f");
        args.add ("image2");
        args.add ("-i");
        args.add ("video\\im%4d.png");
        args.add ("video.mp4");
        args.add ("-y");
        EjecutaComando(args);

    }

    public void reproduceVideo() {
        String filePath = "output";
        File fileP = new File(filePath);
        String commands = "C:\\ffmpeg-4.1.3-win64-static\\bin\\ffplay video.mp4";
        System.out.println(commands);
        List<String> args = new ArrayList<String>();
        args.add ("C:\\ffmpeg-4.1.3-win64-static\\bin\\ffplay"); // command name
        args.add ("-video_size");
        args.add ("854x480");
        args.add ("video.mp4");
        EjecutaComando(args);

    }

    private void EjecutaComando(List<String> args) {
        ProcessBuilder pb = new ProcessBuilder (args);
        Process p = null;
        try {
            p = pb.start();

            InputStream is = p.getErrorStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            System.out.printf("Output of running %s is:\n", args);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

