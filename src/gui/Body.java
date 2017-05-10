package gui;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Body implements Iterable<Point>{

    private int count = 0;
    private Point[] array_of_points;

    public Body() {
        array_of_points = new Point[5];
    }

    public Point getHead() throws Exception {
        if (count == 0)
            throw new Exception("There is no head");
        return array_of_points[0];
    }

    public void add(Point point) {
        if (count == array_of_points.length - 1) {
            resize(array_of_points.length*2);
        }
        array_of_points[count ++] = point;
    }

    private void resize(int newLength) {
        Point[] newArray = new Point[newLength];
        System.arraycopy(array_of_points, 0, newArray, 0, count);
        array_of_points = newArray;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Iterator<Point> iterator() {
        Iterator<Point> it = new Iterator<Point>() {
            private int iter = 0;
            @Override
            public boolean hasNext() {
                return iter < count - 1;
            }

            @Override
            public Point next() {
                return array_of_points[iter++];
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer action) {
        for (Point point : this) {
            action.accept(point);
        }
    }
}
