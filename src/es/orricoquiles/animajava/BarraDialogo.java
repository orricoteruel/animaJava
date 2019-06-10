package es.orricoquiles.animajava;

import javax.swing.*;
import java.awt.*;

public class BarraDialogo extends JDialog {
    JProgressBar barraTotal;
    JLabel barraActuales;
    JLabel barraEnCola;

    public BarraDialogo(int frames, int encola, int actuales) {
        setLayout(new GridLayout(0, 2));
        add(new JLabel("Progreso Total:"));
        barraTotal = new JProgressBar(0, frames);
        add(barraTotal);
        add(new JLabel("Objetos En Cola:"));
        barraEnCola = new JLabel("" + encola);
        add(barraEnCola);
        add(new JLabel("Objetos Actuales:"));
        barraActuales = new JLabel("" + actuales);
        add(barraActuales);
        setBounds(600, 600, 30, 30);
    pack();
    setVisible(true);
  }

    public void actualiza(int nuevo, int encola, int actuales) {
        barraTotal.setValue(nuevo);
        barraEnCola.setText("" + encola);
        barraActuales.setText("" + actuales);
  }

  public void termina() {
    this.dispose();
  }
}
