package com;

import com.engine.board.Board;
import com.gui.Table;

public class Jchess {
    public static void main(String[] args) {

        Board board = Board.createStandardBoard();
        System.out.println(board);

        Table table = new Table();
    }
}
