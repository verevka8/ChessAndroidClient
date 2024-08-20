package com.example.websockets;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChessCellAdapter extends RecyclerView.Adapter<ChessCellAdapter.ViewHolderCell> {

    private ChessCell[] gameBoard;


    public ChessCellAdapter(ChessCell[] gameBoard){
        this.gameBoard = gameBoard;
    }


    @NonNull
    @Override
    public ViewHolderCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chess_cell, parent, false);
        return new ViewHolderCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCell holder, int position) {
        if (gameBoard[position].getFlag()){
            holder.itemView.setBackgroundColor(Color.parseColor("#f5f5dc"));
        }
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#55392e"));
        }
    }

    @Override
    public int getItemCount() {
        return gameBoard.length;
    }

    public static class ViewHolderCell extends RecyclerView.ViewHolder {
        ViewHolderCell(View view){
            super(view);
        }
    }
}
