package gui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {

    private Body body;
    public enum Direction { UP, DOWN, RIGHT, LEFT };
    public Direction direction;

    public Snake(int x, int y) {
        this.body = new Body();
        direction = Direction.LEFT;
        for (int i = 0; i < 4; i ++) {
            if (i == 0)
                body.add(new Point(Point.Part.HEAD, x, y));
            else
                body.add(new Point(Point.Part.BODY, x + 20 * i, y));
        }
    }

    public Snake(Body body, Direction direction) {
        this.direction = direction;
        this.body = body;
    }

    public void drawSnake(Graphics g) {
        for (Point point : body) {
            point.drawPoint(g);
        }
    }

    public boolean search(int x, int y) {
        for (Point point: body) {
            if (point.getX() == x && point.getY() == y)
                return true;
        }
        return false;
    }

    public boolean move() {
        try {
            Point head = getNewHead();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Point getNewHead() throws Exception {
        try {
            Point oldHead = body.getHead();
            Point newHead = (Point) oldHead.clone();
            if (direction == Direction.LEFT) {
                newHead.setX(oldHead.getX() - 20);
            }
            if (direction == Direction.DOWN) {
                newHead.setY(oldHead.getY() + 20);
            }
            if (direction == Direction.RIGHT) {
                newHead.setX(oldHead.getX() + 20);
            }
            if (direction == Direction.UP) {
                newHead.setY(oldHead.getY() - 20);
            }
            return newHead;
        } catch (Exception e) {
            throw e;
        }
    }
}
