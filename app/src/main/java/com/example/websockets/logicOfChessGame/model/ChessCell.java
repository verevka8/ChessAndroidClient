package com.example.websockets.logicOfChessGame.model;

import androidx.annotation.NonNull;

import com.example.websockets.logicOfChessGame.entity.pieces.Pieces;

public class ChessCell {
    private Pieces pieces = null;
    private int x;
    private int y;


    public ChessCell(Pieces pieces, int x, int y) {
        this.pieces = pieces;
        this.x = x;
        this.y = y;
    }

    public ChessCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pieces getPieces() {
        return pieces;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void movePiecesFrom(ChessCell fromCell){
        this.pieces = fromCell.getPieces();
        fromCell.clearPieces();
    }
    void clearPieces(){
        this.pieces = null;
    }
}
