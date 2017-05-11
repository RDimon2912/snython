package strategy;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by dmitry on 11.05.17.
 */
public class HeadLoader implements LoaderStrategy, Serializable{
    @Override
    public Image loadImage() {
        return (new ImageIcon("head.png")).getImage();
    }
}
