package es.orricoquiles.animajava;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DibujoPNG implements Renderizable {
    String nombreFichero;

    public DibujoPNG(String nombreFichero) {
        this.nombreFichero=nombreFichero;
    }

    @Override
    public BufferedImage getBufferedImage(ClipGrafico clipGrafico) {
        BufferedImage img=null;
        try {
            img = ImageIO.read(new File(nombreFichero));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
