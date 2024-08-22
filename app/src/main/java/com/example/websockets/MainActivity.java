package com.example.websockets;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.logicOfChessGame.controller.GameController;
import com.example.websockets.logicOfChessGame.model.ChessBoard;
import com.example.websockets.logicOfChessGame.view.AdapterBoardOfRows;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GameController controller = GameController.getInstance();

        RecyclerView recyclerView = findViewById(R.id.test1);
        AdapterBoardOfRows adapter = new AdapterBoardOfRows(controller.getChessBoard().getBoard());
        controller.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }
}