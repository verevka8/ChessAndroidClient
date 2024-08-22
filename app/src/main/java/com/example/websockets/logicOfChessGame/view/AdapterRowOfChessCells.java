package com.example.websockets.logicOfChessGame.view;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.example.websockets.R;
import com.example.websockets.logicOfChessGame.model.ChessBoard;
import com.example.websockets.logicOfChessGame.model.ChessCell;
import com.example.websockets.logicOfChessGame.viewModel.BoardViewModel;

import java.util.Arrays;
import java.util.List;


public class AdapterRowOfChessCells extends RecyclerView.Adapter<AdapterRowOfChessCells.ViewHolderCell> {

    private BoardViewModel viewModel;
    private List<ChessCell> highlightedCells;
    private int yСoordinate;
    private final int COUNTOFCOLUMN = 8;

    public AdapterRowOfChessCells(LifecycleOwner lifecycleOwner, BoardViewModel viewModel,int yCoordinate) {
        this.viewModel = viewModel;
        this.yСoordinate = yCoordinate;
        viewModel.getPossibleMovesLiveData().observe(lifecycleOwner, new Observer<List<ChessCell>>() {
            @Override
            public void onChanged(List<ChessCell> possibleMoves) {
                highlightedCells = possibleMoves;
                notifyDataSetChanged();// TODO: оптимизировать
            }
        });

        viewModel.getBoardLiveData().observe(lifecycleOwner, new Observer<ChessBoard>() {
            @Override
            public void onChanged(ChessBoard chessBoard) {
                notifyDataSetChanged(); // TODO: оптимизировать
            }
        });
    }

    @NonNull
    @Override
    public ViewHolderCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chess_cell, parent, false);
        return new ViewHolderCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCell holder, int position) {
        ChessCell cell = viewModel.getBoardLiveData().getValue().getCell(position,yСoordinate);
        TextView textView = holder.itemView.findViewById(R.id.textViewTypePieces);

        if (cell.getPieces() != null){
            textView.setText(cell.getPieces().getType());
            textView.setTextColor(cell.getPieces().getIsOwnPieces()?Color.WHITE:Color.BLACK);
        }else{
            textView.setText("") ;
        }
        if (highlightedCells != null && highlightedCells.contains(cell)){
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        else {
            if ((cell.getX() + cell.getY()) % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#f0d9b5"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#b58863"));
            }
        }
        if (cell.getPieces() != null && cell.getPieces().getIsOwnPieces() || cell.getPieces() == null && highlightedCells != null &&  highlightedCells.contains(cell))
            holder.itemView.setOnClickListener(view -> {
                viewModel.onCellClicked(cell);
            });
    }

    @Override
    public int getItemCount() {
        return COUNTOFCOLUMN;
    }

    public static class ViewHolderCell extends RecyclerView.ViewHolder {

        public ViewHolderCell(View itemView) {
            super(itemView);
        }
    }
}