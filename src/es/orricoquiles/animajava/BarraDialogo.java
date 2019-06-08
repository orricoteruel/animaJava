package es.orricoquiles.animajava;

import javax.swing.*;
import java.awt.*;

public class BarraDialogo extends JDialog {
  JProgressBar barra;
  public BarraDialogo(int frames) {
    setLayout(new FlowLayout());
    add(new JLabel("Progreso:"));
    barra=new JProgressBar(0,frames);
    add(barra);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  public void actualiza(int nuevo){
    barra.setValue(nuevo);
  }

  public void termina() {
    this.dispose();
  }
}
