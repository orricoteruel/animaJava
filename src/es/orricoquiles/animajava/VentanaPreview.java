package es.orricoquiles.animajava;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VentanaPreview extends JDialog {
    ImagePanel imagePanel = new ImagePanel();

    public VentanaPreview() {
        add(imagePanel);
        setBounds(10, 10, 854, 480);

        setVisible(true);

    }

    public void termina() {
        this.dispose();
    }
}

class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {

    }

    public ImagePanel setImage(BufferedImage image) {
        this.image = image;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 854, 480, null); // see javadoc for more info on the parameters
    }
}