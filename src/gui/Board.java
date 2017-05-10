package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;

public class Board extends JPanel implements ActionListener{

    private int width;
    private int height;

    private Snake snake;
    private Point apple;

    public Board(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(x, y);
        this.apple = getRandApple();
    }

    public Board(Snake snake, int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.drawSnake(g);
        apple.drawPoint(g);
    }

    private Point getRandApple() {
        int x = (int) (Math.random() * (width - 20));
        int y = (int) (Math.random() * (height - 20));
        while (snake.search(x, y)) {
            x = (int) (Math.random() * (width - 20));
            y = (int) (Math.random() * (height - 20));
        }
        return new Point(Point.Part.APPLE, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (snake.direction != Snake.Direction.RIGHT)) {
                snake.direction = Snake.Direction.LEFT;
            }
            if ((key == KeyEvent.VK_RIGHT) && (snake.direction != Snake.Direction.LEFT)) {
                snake.direction = Snake.Direction.RIGHT;
            }
            if ((key == KeyEvent.VK_UP) && (snake.direction != Snake.Direction.DOWN)) {
                snake.direction = Snake.Direction.UP;
            }
            if ((key == KeyEvent.VK_DOWN) && (snake.direction != Snake.Direction.UP)) {
                snake.direction = Snake.Direction.DOWN;
            }

        }
    }
}
