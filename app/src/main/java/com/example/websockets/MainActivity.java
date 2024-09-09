package com.example.websockets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.networkClient.NetworkClient;
import com.example.websockets.networkClient.callback.SessionsCallback;
import com.example.websockets.ui.adapter.AdapterSessionsList;
import com.example.websockets.networkClient.httpClient.entity.response.SessionInfo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.sessionsRecyclerView);
        NetworkClient.getInstance().getSessions(new SessionsCallback() {
            @Override
            public void onSuccess(List<SessionInfo> sessions) {
                AdapterSessionsList adapter = new AdapterSessionsList(sessions);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("qqq MainActivity", "onFailure: " + t);
                AdapterSessionsList adapter = new AdapterSessionsList(new ArrayList<SessionInfo>());
                recyclerView.setAdapter(adapter);
            }
        });

        findViewById(R.id.createGameButton).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,GameActivity.class));
        });
    }
}

