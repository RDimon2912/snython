package gui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import strategy.*;
import strategy.Point;

public class Board extends JPanel implements ActionListener, Serializable{

    private int width;
    private int height;

    private Snake snake;
    private Point apple;

    private Timer timer;

    private enum StateOfGame { PAUSE, OVER, PLAY };
    private StateOfGame stateOfGame;

    public Board(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(x, y);
        this.apple = getRandApple();

        addKeyListener(new TAdapter());
        setFocusable(true); //чтобы слушала листенер ,обработку клавиатуры
        setPreferredSize(new Dimension(width, height));
        timer = new Timer(200, this);
        timer.start();
        stateOfGame = StateOfGame.PLAY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        snake.drawSnake(g);
        apple.drawPoint(g);
        String score = "Score: " + String.valueOf(snake.getScore());
        Font small = new Font("Helvetica", Font.BOLD, 25);
        g.setColor(Color.blue);
        g.drawString(score, 10, 10);

        if (stateOfGame == StateOfGame.PAUSE || stateOfGame == StateOfGame.OVER) {
            String msg = stateOfGame == StateOfGame.PAUSE ? "PAUSE" : "GAVE OVER";
            FontMetrics metr = getFontMetrics(small);
            g.setColor(Color.red);
            g.drawString(msg, (width - metr.stringWidth(msg)) / 2, height / 2);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private Point getRandApple() {
        int x = (int) (Math.random() * 29) * 20;
        int y = (int) (Math.random() * 19) * 20;
        while (snake.search(x, y)) {
            x = (int) (Math.random() * 29) * 20;
            y = (int) (Math.random() * 19) * 20;
        }
        return new Point(x, y, new AppleLoader());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (stateOfGame == StateOfGame.PLAY) {
            int moveResult = snake.move(apple);
            if (moveResult == 1)
                apple = getRandApple();
            else if (moveResult == -1) {
                stateOfGame = StateOfGame.OVER;
            }
        }

        repaint();
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
            if (key == KeyEvent.VK_ESCAPE && stateOfGame != StateOfGame.OVER) {
                if (stateOfGame == StateOfGame.PAUSE)
                    stateOfGame = StateOfGame.PLAY;
                else stateOfGame = StateOfGame.PAUSE;
            }
            if (key == KeyEvent.VK_S && stateOfGame == StateOfGame.PAUSE) {
                saveToFile();
            }
            if (key == KeyEvent.VK_L && (stateOfGame == StateOfGame.OVER || stateOfGame == StateOfGame.PAUSE)) {
                loadGameFromFile();
                stateOfGame = StateOfGame.PAUSE;
            }
        }
    }

    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("temp.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameFromFile(){
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            Board board = (Board) oin.readObject();
            this.snake = board.snake;
            this.apple = board.apple;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
