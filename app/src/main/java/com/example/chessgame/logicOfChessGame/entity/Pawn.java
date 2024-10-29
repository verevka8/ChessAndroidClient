package com.example.chessgame.logicOfChessGame.entity;

public class Pawn implements Pieces {
    String type = "P";
    boolean isOwnPieces;

    public Pawn(boolean isOwnPieces){
        this.isOwnPieces = isOwnPieces;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean getIsOwnPieces() {
        return isOwnPieces;
    }
}
