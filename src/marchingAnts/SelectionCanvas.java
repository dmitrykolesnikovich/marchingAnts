package marchingAnts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// MarchingAnts http://stackoverflow.com/a/9772978/909169

public class SelectionCanvas extends Canvas {

  private BasicStroke dashedStroke;

  public SelectionCanvas() {
    new Timer(40, new ActionListener() {
      private float progress;

      @Override
      public void actionPerformed(ActionEvent e) {
        progress += 9;
        dashedStroke = new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.5f, new float[]{5.0f, 5.0f}, progress);
      }
    }).start();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    if (dashedStroke == null) {
      return;
    }
    Graphics2D g = (Graphics2D) graphics;
    g.setColor(Color.BLACK);
    g.setStroke(dashedStroke);
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }

}
