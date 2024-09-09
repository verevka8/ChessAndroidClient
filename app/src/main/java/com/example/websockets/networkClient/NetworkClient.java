package com.example.websockets.networkClient;

import android.util.Log;

import com.example.websockets.networkClient.callback.SessionsCallback;
import com.example.websockets.networkClient.webSocketClient.entity.ChessMove;
import com.example.websockets.networkClient.httpClient.entity.response.SessionInfo;
import com.example.websockets.networkClient.httpClient.ApiRequests;
import com.example.websockets.networkClient.httpClient.RetrofitService;
import com.example.websockets.networkClient.httpClient.entity.response.ResponseSessionInfo;
import com.example.websockets.networkClient.webSocketClient.MyWebSockets;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkClient {

    private static NetworkClient instance;
    private ResponseSessionInfo sessionInfo;
    private MyWebSockets webSockets;
    private final ApiRequests apiRequests;
    private final Gson gson = new Gson();

    private NetworkClient(){
        apiRequests = RetrofitService.getInstance().getApiService();
    }

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public void createSession(){
        Call<ResponseSessionInfo> call = apiRequests.createSession();
        call.enqueue(new Callback<ResponseSessionInfo>() {
            @Override
            public void onResponse(Call<ResponseSessionInfo> call, Response<ResponseSessionInfo> response) {
                if (response.isSuccessful()){
                    sessionInfo = response.body();
                    connectToSession();
                    Log.d("qqq", "NetworkClient: onResponse: " + sessionInfo.getStateOfCreation() + " " + sessionInfo.getSessionId());
                }else{
                    Log.d("qqq", "NetworkClient: onResponse: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResponseSessionInfo> call, Throwable t) {
                Log.d("qqq", "NetworkClient: onFailure: " + t.getMessage());
            }
        });
    }

    public void getSessions(SessionsCallback callback){
        Call<List<SessionInfo>> call = apiRequests.getSessions();
        call.enqueue(new Callback<List<SessionInfo>>() {
            @Override
            public void onResponse(Call<List<SessionInfo>> call, Response<List<SessionInfo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onFailure(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<SessionInfo>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private void connectToSession(){
        webSockets = new MyWebSockets(sessionInfo.getSessionId());
    }
    public void sendMessage(String message){
        webSockets.sendMessage(message);
    }
    public void sendMessage(ChessMove move){
        String s = gson.toJson(move, ChessMove.class);
        Log.d("NetworkClient: qqq", "sendMessage: " + s);
        webSockets.sendMessage(s);
    }
}
