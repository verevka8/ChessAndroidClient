package com.example.websockets.logicOfChessGame.entity;

import com.example.websockets.logicOfChessGame.entity.pieces.Pieces;

public class ChessCell {
    private Pieces pieces = null;
    private int x;
    private int y;
    private boolean isHighlighted = false;


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

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }
}
