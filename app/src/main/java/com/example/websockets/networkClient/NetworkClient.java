package com.example.websockets.networkClient;

import android.util.Log;

import com.example.websockets.networkClient.httpClient.RetrofitService;
import com.example.websockets.networkClient.httpClient.entity.response.ResponseSessionCreation;
import com.example.websockets.networkClient.webSocketClient.MyWebSockets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkClient {
    private ResponseSessionCreation sessionInfo;
    private MyWebSockets webSockets;

    public void createSession() throws Exception {
        if (webSockets != null){
            throw new Exception("Session has already been created");
        }
        RetrofitService retrofitService = RetrofitService.getInstance();
        Call<ResponseSessionCreation> call = retrofitService.getApiService().createSession();
        call.enqueue(new Callback<ResponseSessionCreation>() {
            @Override
            public void onResponse(Call<ResponseSessionCreation> call, Response<ResponseSessionCreation> response) {
                if (response.isSuccessful()){
                    sessionInfo = response.body();
                    connectToSession();
                    Log.d("qqq", "NetworkClient: onResponse: " + sessionInfo.getStateOfCreation() + " " + sessionInfo.getSessionId());
                }else{
                    Log.d("qqq", "NetworkClient: onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseSessionCreation> call, Throwable t) {
                Log.d("qqq", "NetworkClient: onFailure: " + t.getMessage());
            }
        });
    }
    private void connectToSession(){
        webSockets = new MyWebSockets(sessionInfo.getSessionId());
    }
    public void sendMessage(String message){
        webSockets.sendMessage(message);
    }
}
