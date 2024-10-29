package com.example.chessgame;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.network.SessionManager;
import com.example.chessgame.network.httpClient.callback.MyCallBack;
import com.example.chessgame.network.httpClient.entity.response.SessionInfo;
import com.example.chessgame.ui.adapter.AdapterSessionsList;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.sessionsRecyclerView);
        SessionManager sessionManager = new SessionManager();


        sessionManager.getSessions(new MyCallBack<List<SessionInfo>>() {
            @Override
            public void onSuccess(List<SessionInfo> sessions) {
                AdapterSessionsList adapter = new AdapterSessionsList(sessions);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("qqq MainActivity", "onFailure: " + t);
                AdapterSessionsList adapter = new AdapterSessionsList(new ArrayList<SessionInfo>());
                recyclerView.setAdapter(adapter);
            }
        });

//        NetworkClient.getInstance().getSessions(new SessionsCallback() {
//            @Override
//            public void onSuccess(List<SessionInfo> sessions) {
//                AdapterSessionsList adapter = new AdapterSessionsList(sessions);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("qqq MainActivity", "onFailure: " + t);
//                AdapterSessionsList adapter = new AdapterSessionsList(new ArrayList<SessionInfo>());
//                recyclerView.setAdapter(adapter);
//            }
//        });

        findViewById(R.id.createGameButton).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,GameActivity.class).putExtra("isOpponentFirst",false));
        });
    }

}


