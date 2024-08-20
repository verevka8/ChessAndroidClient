package com.example.websockets.logicBoardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.R;

public class AdapterBoardOfRows extends RecyclerView.Adapter<AdapterBoardOfRows.ViewHolderRow>  {
    private ChessCell[][] boardOfRows;


    public AdapterBoardOfRows(ChessCell[][] boardOfRows){
        this.boardOfRows = boardOfRows;
    }

    @NonNull
    @Override
    public ViewHolderRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_of_chess_cells, parent, false);
        return new ViewHolderRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRow holder, int position) {
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.RecycleViewRowOfCells);
        AdapterRowOfChessCells adapter = new AdapterRowOfChessCells(boardOfRows[position],position);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return boardOfRows.length;
    }

    public static class ViewHolderRow extends RecyclerView.ViewHolder {
        public ViewHolderRow(View itemView) {
            super(itemView);
        }
    }
}
