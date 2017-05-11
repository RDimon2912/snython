package strategy;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by dmitry on 11.05.17.
 */
public class AppleLoader implements LoaderStrategy, Serializable{
    @Override
    public Image loadImage() {
        return (new ImageIcon("apple.png")).getImage();
    }
}
