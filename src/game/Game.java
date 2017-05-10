package game;

import gui.Board;
import gui.Body;
import gui.Point;
import gui.Snake;

import javax.swing.*;
import java.awt.*;

public class Game {

    private JFrame frm;
    private Board board;


    public static void main(String[] args) {
        Game game = new Game();
        game.start_new_game();

    }

    public void start_new_game() {
        frm = new JFrame("Snython");
        board = new Board(300, 200, 600, 400);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add(BorderLayout.CENTER, board);
        frm.pack();
        frm.setSize(600, 400);
        frm.setResizable(false);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    public void load_game_from_file(String path){

    }

}
