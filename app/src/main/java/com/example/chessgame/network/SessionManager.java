
package com.example.chessgame.network;

import android.util.Log;

import com.example.chessgame.network.httpClient.callback.MyCallBack;
import com.example.chessgame.network.httpClient.entity.response.SessionInfo;
import com.example.chessgame.network.webSocketClient.entity.ChessMove;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SessionManager {

    private final NetworkClient client;
    private SessionInfo sessionInfo;
    private ChessMoveMessageListener listener;
    private final Gson gson;
    private final String TAG = "SessionManager";

    public SessionManager(){
        client = NetworkClient.getInstance();
        gson = new Gson();
    }

    public void createSession(){
        MyCallBack<SessionInfo> callBack = new MyCallBack<SessionInfo>() {
            @Override
            public void onSuccess(SessionInfo response) {
                Log.d(TAG, "createSession: onSuccess: " + response.getSessionId());
                sessionInfo = response;
                connectToSession();

            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "createSession: onError: " + t); // выброска ошибок
            }
        };
        client.sendGetRequest("createSession",callBack,new TypeToken<SessionInfo>(){}.getType());
    }

    public void getSessions(MyCallBack<List<SessionInfo>> callBack){
        client.sendGetRequest("sessions",callBack,new TypeToken<List<SessionInfo>>(){}.getType());
    }
    private void connectToSession(){
        client.connectToSession(sessionInfo.getSessionId());
    }

    public void sendMove(ChessMove move){
        client.sendMessage(move,sessionInfo.getSessionId());
    }

    public void setListener(ChessMoveMessageListener listener) {
        this.listener = listener;
        client.registerListener("ChessMove", new NetworkClient.Listener() {
            @Override
            public void onDataReceived(String data) {
                listener.onMessageReceived(gson.fromJson(data,ChessMove.class));
            }
        });
    }


    public interface ChessMoveMessageListener {
        void onMessageReceived(ChessMove move);
    }
}
