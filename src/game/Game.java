package game;

import gui.Board;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Game {

    private JFrame frm;
    public Board board;

    public static void main(String[] args) {

        Game game = new Game();
        game.board = new Board(300, 200, 600, 400);
        game.startGame();
    }

    public void startGame() {
        frm = new JFrame("Snython");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add(BorderLayout.CENTER, board);
        frm.pack();
        frm.setSize(600, 400);
        frm.setResizable(false);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

}
