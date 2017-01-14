package hypercard.paint.tools.base;

import hypercard.paint.model.PaintToolType;
import hypercard.paint.utils.Geometry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPolylineTool extends AbstractPaintTool {

    private int snapToDegrees = 15;
    private List<Point> points = new ArrayList<>();
    private Point currentPoint = null;

    protected abstract void drawPolyline(Graphics2D g, Stroke stroke, Paint paint, int[] xPoints, int[] yPoints);

    protected abstract void drawPolygon(Graphics2D g, Stroke stroke, Paint strokePaint, int[] xPoints, int[] yPoints);

    protected abstract void fillPolygon(Graphics2D g, Paint fillPaint, int[] xPoints, int[] yPoints);

    public AbstractPolylineTool(PaintToolType type) {
        super(type);
    }

    @Override
    public void mouseMoved(MouseEvent e, int scaleX, int scaleY) {

        // Nothing to do if initial point is not yet established
        if (points.size() == 0) {
            return;
        }

        if (e.isShiftDown()) {
            Point lastPoint = points.get(points.size() - 1);
            currentPoint = Geometry.snapLineToNearestAngle(lastPoint, e.getPoint(), snapToDegrees);
            points.add(currentPoint);
        } else {
            currentPoint = new Point(scaleX, scaleY);
            points.add(currentPoint);
        }

        int[] xs = points.stream().mapToInt(i -> i.x).toArray();
        int[] ys = points.stream().mapToInt(i -> i.y).toArray();

        getCanvas().clearScratch();

        Graphics2D g2d = (Graphics2D) getCanvas().getScratchImage().getGraphics();
        drawPolyline(g2d, getStroke(), getStrokePaint(), xs, ys);
        g2d.dispose();

        getCanvas().invalidateCanvas();

        points.remove(points.size() - 1);
    }

    @Override
    public void mousePressed(MouseEvent e, int scaleX, int scaleY) {

        // User double-clicked; complete the polygon
        if (e.getClickCount() > 1 && points.size() > 1) {
            points.add(currentPoint);
            commitPolygon();
        }

        // First click (creating initial point)
        else if (currentPoint == null) {
            points.add(new Point(scaleX, scaleY));
        }

        // Single click with initial point established
        else {
            points.add(currentPoint);
        }
    }

    private void commitPolygon() {
        getCanvas().clearScratch();

        int[] xs = points.stream().mapToInt(i -> i.x).toArray();
        int[] ys = points.stream().mapToInt(i -> i.y).toArray();

        points.clear();
        currentPoint = null;

        Graphics2D g2d = (Graphics2D) getCanvas().getScratchImage().getGraphics();

        if (getFillPaint() != null) {
            fillPolygon(g2d, getFillPaint(), xs, ys);
        }

        drawPolygon(g2d, getStroke(), getStrokePaint(), xs, ys);
        g2d.dispose();

        getCanvas().commit();
    }

    private void commitPolyline() {
        getCanvas().clearScratch();

        int[] xs = points.stream().mapToInt(i -> i.x).toArray();
        int[] ys = points.stream().mapToInt(i -> i.y).toArray();

        points.clear();
        currentPoint = null;

        Graphics2D g2d = (Graphics2D) getCanvas().getScratchImage().getGraphics();
        drawPolyline(g2d, getStroke(), getStrokePaint(), xs, ys);
        g2d.dispose();

        getCanvas().commit();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Ignore escape unless at least one point has been defined
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && points.size() > 0) {
            points.add(currentPoint);
            commitPolyline();
        }
    }

    public int getSnapToDegrees() {
        return snapToDegrees;
    }

    public void setSnapToDegrees(int snapToDegrees) {
        this.snapToDegrees = snapToDegrees;
    }
}
