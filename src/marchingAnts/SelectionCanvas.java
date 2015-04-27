package marchingAnts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// MarchingAnts http://stackoverflow.com/a/9772978/909169

public class SelectionCanvas extends Canvas {

  private BasicStroke dashedStroke;

  private final static int SIZE = 4;
  private final static int SIZE2 = 6;
  private Component panel;

  public SelectionCanvas() {
    setBounds(0, 0, 200, 200);
    new Timer(200, new ActionListener() {
      private float progress;

      @Override
      public void actionPerformed(ActionEvent e) {
        progress += 9;
        dashedStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, SIZE, new float[]{SIZE, SIZE}, progress);
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
    Stroke old = g.getStroke();

    g.setColor(Color.WHITE);
    g.drawRect(getX(), getY(), getWidth(), getHeight());
    g.setColor(Color.BLACK);
    g.setStroke(dashedStroke);
    g.drawRect(getX(), getY(), getWidth(), getHeight());

    // markers
    g.setStroke(old);
    drawMarker(g, getX(), getY());
    drawMarker(g, getXc(), getY());
    drawMarker(g, getX2(), getY());
    drawMarker(g, getX(), getYc());
    drawMarker(g, getX2(), getYc());
    drawMarker(g, getX(), getY2());
    drawMarker(g, getXc(), getY2());
    drawMarker(g, getX2(), getY2());
  }

  private void drawMarker(Graphics2D g, int x, int y) {
    g.drawRect(x - SIZE2 / 2, y - SIZE2 / 2, SIZE2, SIZE2);
  }

  public final MouseAdapter adapter = new MouseAdapter() {

    @Override
    public void mouseMoved(MouseEvent e) {
      super.mouseMoved(e);
      if (check(e, getX(), getY())) {
        panel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
      } else if (check(e, getXc(), getY())) {
        panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
      } else if (check(e, getX2(), getY())) {
        panel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
      } else if (check(e, getX(), getYc())) {
        panel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
      } else if (check(e, getX2(), getYc())) {
        panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
      } else if (check(e, getX(), getY2())) {
        panel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
      } else if (check(e, getXc(), getY2())) {
        panel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
      } else if (check(e, getX2(), getY2())) {
        panel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
      } else {
        panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      super.mouseDragged(e);
      int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
      if (check(e, getX(), getY())) {
        x1 = e.getX();
        y1 = e.getY();
        x2 = getX2();
        y2 = getY2();
      } else if (check(e, getXc(), getY())) {
        x1 = getX();
        x2 = getX2();
        y1 = e.getY();
        y2 = getY2();
      } else if (check(e, getX2(), getY())) {
        x1 = getX();
        y2 = getY2();
        x2 = e.getX();
        y1 = e.getY();
      } else if (check(e, getX(), getYc())) {
        x1 = e.getX();
        x2 = getX2();
        y1 = getY();
        y2 = getY2();
      } else if (check(e, getX2(), getYc())) {
        x1 = getX();
        x2 = e.getX();
        y1 = getY();
        y2 = getY2();
      } else if (check(e, getX(), getY2())) {
        x1 = e.getX();
        x2 = getX2();
        y1 = getY();
        y2 = e.getY();
      } else if (check(e, getXc(), getY2())) {
        x1 = getX();
        x2 = getX2();
        y1 = getY();
        y2 = e.getY();
      } else if (check(e, getX2(), getY2())) {
        x1 = getX();
        y1 = getY();
        x2 = e.getX();
        y2 = e.getY();
      }
      if (x1 != -1 && y1 != -1 && x2 != -1 && y2 != -1) {
        setBounds(x1, y1, x2 - x1, y2 - y1);
      }
    }

  };

  private boolean check(MouseEvent e, int x, int y) {
    return e.getX() < x + SIZE2 && e.getX() > x - SIZE2 && e.getY() < y + SIZE2 && e.getY() > y - SIZE2;
  }

  public int getXc() {
    return getX() + getWidth() / 2;
  }

  public int getYc() {
    return getY() + getHeight() / 2;
  }

  public int getX2() {
    return getX() + getWidth();
  }

  public int getY2() {
    return getY() + getHeight();
  }

  public void setPanel(JPanel panel) {
    this.panel = panel;
    panel.addMouseMotionListener(adapter);
    panel.addMouseListener(adapter);
  }

}
