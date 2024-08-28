package com.example.websockets;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.logicOfChessGame.view.AdapterBoardOfRows;
import com.example.websockets.logicOfChessGame.viewModel.BoardViewModel;
import com.example.websockets.networkClient.NetworkClient;
import com.example.websockets.networkClient.httpClient.RetrofitService;
import com.example.websockets.networkClient.httpClient.entity.response.ResponseSessionCreation;
import com.example.websockets.networkClient.webSocketClient.MyWebSockets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BoardViewModel viewModel = new BoardViewModel();
        RecyclerView recyclerView = findViewById(R.id.test1);
        AdapterBoardOfRows adapter = new AdapterBoardOfRows(this,viewModel);
        recyclerView.setAdapter(adapter);

        NetworkClient client = new NetworkClient();
        try {
            client.createSession();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        findViewById(R.id.button).setOnClickListener(view -> {
            client.sendMessage("{\"fromCellX\":1,\"fromCellY\":1,\"fromCellType\":\"P\",\"toCellX\":2,\"toCellY\":2}\n");

        });
    }
}

