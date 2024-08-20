package com.example.websockets.logicBoardView;

import android.graphics.Color;

import com.example.websockets.chessPieces.Pawn;

public class ChessBoard {
    private ChessCell[][] board = new ChessCell[8][8];

    public ChessBoard(){
        for (int i = 0; i < 8;i++){
            for (int j = 0; j < 8;j++){
                if ((i+j)%2 != 0){
                    if (i < 3) {
                        board[i][j] = new ChessCell(new Pawn("P", Color.WHITE));
                    } else if (i > 4) {
                        board[i][j] = new ChessCell(new Pawn("P", Color.BLACK));
                    } else  {
                        board[i][j] = new ChessCell(new Pawn("",-1));
                    }
                }
                else{
                    board[i][j] = new ChessCell(new Pawn("",-1));
                }

            }
        }
    }

    public ChessCell[][] getBoard() {
        return board;
    }
}
