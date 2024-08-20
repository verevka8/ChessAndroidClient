package com.example.websockets.logicBoardView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.R;

import java.util.ArrayList;

public class AdapterRowOfChessCells extends RecyclerView.Adapter<AdapterRowOfChessCells.ViewHolderCell> {

    private ChessCell[] rowOfCell;
    private int column;
    private TextView textView;

    public AdapterRowOfChessCells(ChessCell[] rowOfCell, int column){
        this.rowOfCell = rowOfCell;
        this.column = column;
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
        textView.setText(cell.getPieces().getType());
        textView.setTextColor(cell.getPieces().getTeamColor());

        if ((position + column)%2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#f0d9b5"));
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#b58863"));
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
