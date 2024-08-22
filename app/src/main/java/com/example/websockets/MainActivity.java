package com.example.websockets;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.logicOfChessGame.view.AdapterBoardOfRows;
import com.example.websockets.logicOfChessGame.viewModel.BoardViewModel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BoardViewModel viewModel = new BoardViewModel();
        RecyclerView recyclerView = findViewById(R.id.test1);
        AdapterBoardOfRows adapter = new AdapterBoardOfRows(this,viewModel);
        recyclerView.setAdapter(adapter);
    }
}