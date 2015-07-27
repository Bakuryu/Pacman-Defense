package Math;

import java.awt.Point;

/**
 *
 * @author coleman
 */
public class CoordinateTranslator 
{
    private final double screenWidth;
    private final double screenHeight;
    private double viewWidth;
    private double viewHeight;
    
    // the horizontal and vertical offset from the lower left corner to (0, 0)
    private double hortizontalOffset;
    private double verticalOffset;

    // Ratios used in the computations. 
    // vw = window width
    // sw = screen width
    // vh = window height
    // sh = screen height
    private double vw2sw;
    private double vh2sh;
    private double sw2vw;
    private double sh2vh;
    
    public CoordinateTranslator(int screenWidth, int screenHeight,
                                double viewWidth, double viewHeight,
                                Point2D viewLowerLeft)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        
        this.hortizontalOffset = viewLowerLeft.getX();
        this.verticalOffset = viewLowerLeft.getY();

        computeRatios();
    }
    
    public boolean isOnScreen(double x, double y)
    {
        Point p = worldToScreen(x, y);
        
        if(p.x < 0 || p.x > screenWidth)
            return false;
        if(p.y < 0 || p.y > screenHeight)
            return false;
        return true;
    }
    
    public boolean isOnScreen(Point2D p)
    {
        return isOnScreen(p.getX(), p.getY());
    }
    
    public Point2D screenToWorld(int x, int y)
    {
        double wx = vw2sw * x + hortizontalOffset;
        double wy = viewHeight -vh2sh * y + verticalOffset;
        
        return new Point2D(wx, wy);
    }
    
    public Point2D screenToWorld(Point p)
    {
        return screenToWorld(p.x, p.y);
    }
    
    public Point worldToScreen(double x, double y)
    {
        int sx = (int)(sw2vw * (x - hortizontalOffset));
        int sy = (int)(sh2vh * (y + verticalOffset));
        
        return new Point(sx, sy);
    }
    
    public Point worldToScreen(Point2D p)
    {
        return worldToScreen(p.getX(), p.getY());
    }
    
    public void setView(double viewWidth, double viewHeight, Point2D viewLowerLeft)
    {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.hortizontalOffset = viewLowerLeft.getX();
        this.verticalOffset = viewLowerLeft.getY();
        
        computeRatios();
    }
    
    public void setView(Point2D viewLowerLeft)
    {
        this.hortizontalOffset = viewLowerLeft.getX();
        this.verticalOffset = viewLowerLeft.getY();
    }
    
    private void computeRatios()
    {
        vw2sw = viewWidth / screenWidth;
        vh2sh = viewHeight / screenHeight;
        sw2vw = screenWidth / viewWidth;
        sh2vh = screenHeight / viewHeight;
    }
}

