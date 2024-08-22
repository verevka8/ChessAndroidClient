package com.example.websockets.logicOfChessGame.controller;

import com.example.websockets.logicOfChessGame.entity.ChessCell;
import com.example.websockets.logicOfChessGame.model.ChessBoard;
import com.example.websockets.logicOfChessGame.view.AdapterBoardOfRows;

public class GameController {
    private static GameController instance = new GameController();

    private ChessBoard chessBoard = new ChessBoard();
    private AdapterBoardOfRows adapter;

    private GameController(){}

    public static GameController getInstance(){
        return instance;
    }

    public void setAdapter(AdapterBoardOfRows adapter) {
        this.adapter = adapter;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void highlightPossibleMoves(ChessCell cell){
        chessBoard.highlightMoves(cell);
        adapter.notifyDataSetChanged();
    }
}
