package com.example.websockets.logicBoardView;

import com.example.websockets.chessPieces.Pieces;

public class ChessCell {
    private Pieces pieces;

    public ChessCell(Pieces pieces) {
        this.pieces = pieces;
    }


    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }
}
