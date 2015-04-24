package marchingAnts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JPanel {

  private final SelectionCanvas selectionCanvas = new SelectionCanvas();

  public Main() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        selectionCanvas.setBounds(e.getX(), e.getY(), 200, 200);
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    selectionCanvas.paint(g); // IMPORTANT
    repaint();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("TestSelectionCanvas");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Main comp = new Main();
    comp.setPreferredSize(new Dimension(400, 400));
    frame.getContentPane().add(comp);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

}
