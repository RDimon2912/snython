package gui;

import javax.swing.*;
import java.awt.*;

public class Point implements Cloneable {
    private int x;
    private int y;

    private Image img;
    public enum Part { BODY, HEAD, APPLE};
    private Part whoAmI;

    public Point(Part whoAmI, int x, int y) {
        this.x = x;
        this.y = y;
        this.whoAmI = whoAmI;
        loadImage();
    }

    public void loadImage() {
        if (whoAmI == Part.BODY) {
            img = (new ImageIcon("body.png")).getImage();
        }
        if (whoAmI == Part.HEAD){
            img = (new ImageIcon("head.png")).getImage();
        }
        if (whoAmI == Part.APPLE) {
            img = (new ImageIcon("apple.png")).getImage();
        }
    }

    public void drawPoint(Graphics g) {
        g.drawImage(img, x, y, 20, 20, null);
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

    public void setWhoAmI(Part part) {
        whoAmI = part;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
