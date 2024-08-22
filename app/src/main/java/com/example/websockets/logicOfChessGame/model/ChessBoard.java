package com.example.websockets.logicOfChessGame.model;

import com.example.websockets.logicOfChessGame.entity.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

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
                    board[y][x] = new ChessCell(x,y);
                }
            }
        }
    }
    public ChessCell getCell(int x, int y) {
        assert (x >= 0 && x<= 7 && y >= 0 && y <= 7);
        return board[y][x];
    }

    public ChessCell[][] getBoard() {
        return board;
    }

    public List<ChessCell> getPossibleMoves(ChessCell selectedCell) {
        // TODO: исправить хардкод
        List<ChessCell> possibleMoves = new ArrayList<>();
        possibleMoves.add(board[selectedCell.getY()-1][selectedCell.getX()+1]);
        possibleMoves.add(board[selectedCell.getY()-1][selectedCell.getX()-1]);
        return possibleMoves;
    }
    public void movePiece(ChessCell fromCell, ChessCell toCell){
        toCell.movePiecesFrom(fromCell);
    }
}
