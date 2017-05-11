package strategy;

import java.awt.*;
import java.io.Serializable;

public class Point implements Cloneable, Serializable {
    private int x;
    private int y;
    private LoaderStrategy loaderStrategy;

    public Point(int x, int y, LoaderStrategy loaderStrategy) {
        this.x = x;
        this.y = y;
        this.loaderStrategy = loaderStrategy;
    }

    public void drawPoint(Graphics g) {
        g.drawImage(loaderStrategy.loadImage(), x, y, 20, 20, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLoaderStrategy(LoaderStrategy loaderStrategy) {
        this.loaderStrategy = loaderStrategy;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
