package com.example.websockets.logicOfChessGame.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.websockets.logicOfChessGame.model.ChessBoard;
import com.example.websockets.logicOfChessGame.model.ChessCell;

import java.util.List;

public class BoardViewModel extends ViewModel {
    private final MutableLiveData<ChessBoard> boardLiveData = new MutableLiveData<>();
    // TODO: оптимизировать possibleMovesLiveData так, чтобы в нем хранились координаты ячекйки, а не ее экземляр
    private final MutableLiveData<List<ChessCell>> possibleMovesLiveData = new MutableLiveData<>();
    private ChessCell selectedCell = null;

    public BoardViewModel() {
        boardLiveData.setValue(new ChessBoard()); // Инициализация доски 8x8
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
            board.movePiece(selectedCell, clickedCell);
            selectedCell = null;
            boardLiveData.setValue(board);
            possibleMovesLiveData.setValue(null);
        } else {
            selectedCell = clickedCell;
            possibleMovesLiveData.setValue(board.getPossibleMoves(clickedCell)); //Todo: метод getPossibleMoves - HardCode
        }
    }
}
