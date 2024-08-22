package com.example.websockets.logicOfChessGame.view;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.websockets.R;
import com.example.websockets.logicOfChessGame.controller.GameController;
import com.example.websockets.logicOfChessGame.entity.ChessCell;


public class AdapterRowOfChessCells extends RecyclerView.Adapter<AdapterRowOfChessCells.ViewHolderCell> {

    private ChessCell[] rowOfCell;
    private TextView textView;

    public AdapterRowOfChessCells(ChessCell[] rowOfCell) {
        this.rowOfCell = rowOfCell;
    }

    @NonNull
    @Override
    public ViewHolderCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chess_cell, parent, false);
        return new ViewHolderCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCell holder, int position) {
        ChessCell cell = rowOfCell[position];
        textView = holder.itemView.findViewById(R.id.textViewTypePieces);
        holder.itemView.setOnClickListener(view -> {
            if (cell.isHighlighted()){
                GameController.getInstance().
            }
            else {GameController.getInstance().highlightPossibleMoves(cell);}
        });

        if (cell.getPieces() != null){
            textView.setText(cell.getPieces().getType());
            textView.setTextColor(cell.getPieces().getIsOwnPieces()?Color.WHITE:Color.BLACK);
        }
        if (cell.isHighlighted()){
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        else {
            if ((cell.getX() + cell.getY()) % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#f0d9b5"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#b58863"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return rowOfCell.length;
    }

    public static class ViewHolderCell extends RecyclerView.ViewHolder {

        public ViewHolderCell(View itemView) {
            super(itemView);
        }
    }
}