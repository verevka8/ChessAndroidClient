package com.example.websockets;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.logicBoardView.AdapterBoardOfRows;
import com.example.websockets.logicBoardView.ChessBoard;


public class MainActivity extends AppCompatActivity {
    private MyWebSockets myWebSockets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ChessBoard board = new ChessBoard();
        RecyclerView recyclerView = findViewById(R.id.test1);
        AdapterBoardOfRows adapter = new AdapterBoardOfRows(board.getBoard());
        recyclerView.setAdapter(adapter);
    }
}