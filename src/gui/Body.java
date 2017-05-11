package gui;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

import strategy.*;

public class Body implements Iterable<Point>, Serializable {

    private int count = 0;
    private PointBox head;
    private PointBox tail;

    public Body() {
        head = null;
        tail = null;
        count = 0;
    }

    public Point getHead() throws Exception {
        if (count == 0)
            throw new Exception("There is no head");
        return head.getObject();
    }

    public void add(Point point) {
        count ++;
        PointBox newHead = new PointBox(point);
        if (head == null) {
            head = newHead;
            tail = newHead;
        }
        else {
            head.setObjectNext(newHead);
            head = newHead;
        }
    }

    public Point pop() {
        if (head == null)
            return null;
        count --;
        Point res = tail.getObject();
        tail = tail.getObjectNext();
        return res;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Iterator<Point> iterator() {
        Iterator<Point> it = new Iterator<Point>() {
            private PointBox iter = tail;
            @Override
            public boolean hasNext() {
                return iter != null;
            }

            @Override
            public Point next() {
                Point res = iter.getObject();
                iter = iter.getObjectNext();
                return res;
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

    private class PointBox implements Serializable {
        private Point object;
        private PointBox objectNext;

        public PointBox(Point point) {
            object = point;
            objectNext = null;
        }

        public Point getObject() {
            return object;
        }

        public void setObject(Point point) {
            object = point;
        }

        public PointBox getObjectNext() {
            return objectNext;
        }

        public void setObjectNext(PointBox objectNext) {
            this.objectNext = objectNext;
        }
    }
}
