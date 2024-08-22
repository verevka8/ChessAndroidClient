package com.example.websockets.logicOfChessGame.model;

import com.example.websockets.logicOfChessGame.entity.ChessCell;
import com.example.websockets.logicOfChessGame.entity.pieces.Pawn;

public class ChessBoard {
    private ChessCell[][] board = new ChessCell[8][8];

    public ChessBoard(){
        for (int y = 0; y < 8;y++){
            for (int x = 0; x < 8;x++){
                if ((y+x)%2 != 0){
                    if (y < 3) {
                        board[y][x] = new ChessCell(new Pawn(false),x,y);
                    } else if (y > 4) {
                        board[y][x] = new ChessCell(new Pawn(true),x,y);
                    } else  {
                        board[y][x] = new ChessCell(x,y);
                    }
                }
                else{
                    board[x][y] = new ChessCell(x,y);
                }
            }
        }
    }
    public ChessCell getCell(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return board[y][x];
        }
        return null;
    }

    public ChessCell[][] getBoard() {
        return board;
    }

    public void highlightMoves(ChessCell cell) {
        clearHighlights();
        // TODO: исправить хардкод
        board[cell.getY()-1][cell.getX()+1].setHighlighted(true);
        board[cell.getY()-1][cell.getX()-1].setHighlighted(true);
    }

    private void clearHighlights() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x].setHighlighted(false);
            }
        }
    }
}
