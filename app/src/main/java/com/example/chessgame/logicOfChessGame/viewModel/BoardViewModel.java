package com.example.chessgame.logicOfChessGame.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.chessgame.logicOfChessGame.model.ChessBoard;
import com.example.chessgame.logicOfChessGame.model.ChessCell;
import com.example.chessgame.network.SessionManager;
import com.example.chessgame.network.webSocketClient.entity.ChessMove;

import java.util.List;


public class BoardViewModel extends ViewModel implements SessionManager.ChessMoveMessageListener {

    private final MutableLiveData<ChessBoard> boardLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ChessCell>> possibleMovesLiveData = new MutableLiveData<>(); // TODO: оптимизировать possibleMovesLiveData так, чтобы в нем хранились координаты ячекйки, а не ее экземляр
    private ChessCell selectedCell = null;
    private final boolean isOpponentFirst;
    private boolean isUserMove;
    private final SessionManager sessionManager;

    public BoardViewModel(boolean isOpponentFirst) {
        boardLiveData.setValue(new ChessBoard());
        this.isOpponentFirst = isOpponentFirst;
        isUserMove = !isOpponentFirst;
        // инициализация сессии
        sessionManager = new SessionManager();
        sessionManager.createSession();
        sessionManager.setListener(this);
    }

    public LiveData<ChessBoard> getBoardLiveData() {
        return boardLiveData;
    }

    public LiveData<List<ChessCell>> getPossibleMovesLiveData() {
        return possibleMovesLiveData;
    }

    public boolean isOpponentFirst() {
        return isOpponentFirst;
    }

    public boolean isUserMove() {
        return isUserMove;
    }

    public void onCellClicked(ChessCell clickedCell) {
        ChessBoard board = boardLiveData.getValue();
        if (selectedCell != null && possibleMovesLiveData.getValue().contains(clickedCell)) {
            movePiece(selectedCell,clickedCell);
            selectedCell = null;
            boardLiveData.setValue(board);
            possibleMovesLiveData.setValue(null);
        } else {
            selectedCell = clickedCell;
            possibleMovesLiveData.setValue(board.getPossibleMoves(clickedCell)); //Todo: метод getPossibleMoves - HardCode
        }
        Log.d("qqq BoardViewModel", "onCellClicked: " + clickedCell);
    }
    private void movePiece(ChessCell fromCell, ChessCell toCell) {
        sessionManager.sendMove(new ChessMove(fromCell,toCell));
        boardLiveData.getValue().movePiece(fromCell, toCell);
        isUserMove = !isUserMove;
    }

    @Override
    public void onMessageReceived(ChessMove move) {
        boardLiveData.getValue().movePiece(move);
        boardLiveData.postValue(boardLiveData.getValue());
        isUserMove = !isUserMove;
    }
}
