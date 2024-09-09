package com.example.websockets.logicOfChessGame.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.websockets.logicOfChessGame.model.ChessBoard;
import com.example.websockets.logicOfChessGame.model.ChessCell;
import com.example.websockets.networkClient.webSocketClient.entity.ChessMove;
import com.example.websockets.networkClient.NetworkClient;

import java.util.List;

public class BoardViewModel extends ViewModel {

    private final MutableLiveData<ChessBoard> boardLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ChessCell>> possibleMovesLiveData = new MutableLiveData<>(); // TODO: оптимизировать possibleMovesLiveData так, чтобы в нем хранились координаты ячекйки, а не ее экземляр
    private ChessCell selectedCell = null;
    private final NetworkClient client;

    public BoardViewModel() {
        boardLiveData.setValue(new ChessBoard()); // Инициализация доски 8x8
        client = NetworkClient.getInstance();
        client.createSession();
    }

    public LiveData<ChessBoard> getBoardLiveData() {
        return boardLiveData;
    }

    public LiveData<List<ChessCell>> getPossibleMovesLiveData() {
        return possibleMovesLiveData;
    }

    public void onCellClicked(ChessCell clickedCell) {
        ChessBoard board = boardLiveData.getValue();
        if (selectedCell != null && possibleMovesLiveData.getValue().contains(clickedCell)) {
            movePiece(selectedCell,clickedCell);
            board.movePiece(selectedCell, clickedCell);
            selectedCell = null;
            boardLiveData.setValue(board);
            possibleMovesLiveData.setValue(null);
        } else {
            selectedCell = clickedCell;
            possibleMovesLiveData.setValue(board.getPossibleMoves(clickedCell)); //Todo: метод getPossibleMoves - HardCode
        }
    }
    private void movePiece(ChessCell fromCell, ChessCell toCell) {
        client.sendMessage(new ChessMove(fromCell,toCell));
    }
}
