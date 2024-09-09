package com.example.websockets;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.logicOfChessGame.view.AdapterBoardOfRows;
import com.example.websockets.logicOfChessGame.viewModel.BoardViewModel;
import com.example.websockets.networkClient.NetworkClient;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        BoardViewModel viewModel = new BoardViewModel();
        RecyclerView recyclerView = findViewById(R.id.test1);
        AdapterBoardOfRows adapter = new AdapterBoardOfRows(this,viewModel);
        recyclerView.setAdapter(adapter);

//        NetworkClient client = new NetworkClient();
//        client.createSession();

//        findViewById(R.id.button).setOnClickListener(view -> {
//            client.sendMessage("{\"fromCellType\":\"P\",\"fromCellX\":4,\"fromCellY\":5,\"toCellX\":3,\"toCellY\":4}");
//
//        });
    }
}
