package gui;

import java.awt.Graphics;
import java.io.Serializable;

import strategy.*;

public class Snake implements Serializable {

    private Body body;
    public enum Direction { UP, DOWN, RIGHT, LEFT };
    public Direction direction;

    public Snake(int x, int y) {
        this.body = new Body();
        direction = Direction.LEFT;
        for (int i = 3; i >= 0; i --) {
            if (i == 0)
                body.add(new Point(x, y, new HeadLoader()));
            else
                body.add(new Point(x + 20 * i, y, new BodyLoader()));
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

    public int getScore() {
        return body.getCount() - 4;
    }

    public int move(Point apple) {
        int ans = 0;
        try {
            Point head = getNewHead();
            if (this.search(head.getX(), head.getY())) {
                ans = -1;
                return ans;
            }
            body.getHead().setLoaderStrategy(new BodyLoader());
            body.add(head);
            if (head.getX() != apple.getX() || head.getY() != apple.getY()) {
                body.pop();
                ans = 0;
            } else ans = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    private Point getNewHead() throws Exception {
        try {
            Point oldHead = body.getHead();
            Point newHead = (Point) oldHead.clone();
            if (direction == Direction.LEFT) {
                newHead.setX(oldHead.getX() - 20);
                if (newHead.getX() < 0)
                    newHead.setX(newHead.getX() + 600);
            }
            if (direction == Direction.DOWN) {
                newHead.setY(oldHead.getY() + 20);
                if (newHead.getY() >= 400)
                    newHead.setY(newHead.getY() - 400);
            }
            if (direction == Direction.RIGHT) {
                newHead.setX(oldHead.getX() + 20);
                if (newHead.getX() >= 600)
                    newHead.setX(newHead.getX() - 600);
            }
            if (direction == Direction.UP) {
                newHead.setY(oldHead.getY() - 20);
                if (newHead.getY() < 0)
                    newHead.setY(newHead.getY() + 400);
            }
            return newHead;
        } catch (Exception e) {
            throw e;
        }
    }
}
