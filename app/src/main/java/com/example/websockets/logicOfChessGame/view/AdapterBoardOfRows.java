package com.example.websockets.logicOfChessGame.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.R;
import com.example.websockets.logicOfChessGame.viewModel.BoardViewModel;

public class AdapterBoardOfRows extends RecyclerView.Adapter<AdapterBoardOfRows.ViewHolderRow>  {
    private BoardViewModel viewModel;
    private final int COUNTOFROW = 8;
    private LifecycleOwner lifecycleOwner;


    public AdapterBoardOfRows(LifecycleOwner lifecycleOwner,BoardViewModel viewModel){
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolderRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_of_chess_cells, parent, false);
        return new ViewHolderRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRow holder, int position) {
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.RecycleViewRowOfCells);
        AdapterRowOfChessCells adapter = new AdapterRowOfChessCells(lifecycleOwner,viewModel,position);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return COUNTOFROW;
    }

    public static class ViewHolderRow extends RecyclerView.ViewHolder {
        public ViewHolderRow(View itemView) {
            super(itemView);
        }
    }
}