package com.example.chessgame.network.webSocketClient;

import android.annotation.SuppressLint;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompCommand;
import ua.naiksoftware.stomp.dto.StompHeader;
import ua.naiksoftware.stomp.dto.StompMessage;

public class MyWebSockets {
    private StompClient client;
    private final String TAG = "MyWebSockets";
    private final String BASE_URL = "ws://192.168.1.78:8080/gs"; // сделать так, чтобы в конфиге было
    private WebSocketMessageCallback messageCallback;

    public MyWebSockets(WebSocketMessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    @SuppressLint("CheckResult")
    public void connectToSession(String sessionId){
        client = Stomp.over(Stomp.ConnectionProvider.JWS,BASE_URL);
        client.connect();

        client.topic("/topic/session/" + sessionId).subscribe(topicMessage -> {
            Log.d(TAG, "Received: " + topicMessage.getPayload());
            messageCallback.onMessageReceived(topicMessage.getPayload());
        }, throwable -> {
            Log.d(TAG, "Error on subscribe", throwable);
        });

        client.topic("/user/queue/message/").subscribe(topicMessage -> {
            Log.d(TAG, "Received: " + topicMessage.getPayload());
        }, throwable -> {
            Log.d(TAG, "Error on subscribe", throwable);
        });


        client.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    sendMessage("{\"jsonObject\":\"yep!\",\"jsonType\":\"String\"}", sessionId);
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });
    }

    @SuppressLint("CheckResult")
    public void sendMessage(String message,String sessionId) {
        if (true) {
            List<StompHeader> headers = new ArrayList<>();
            headers.add(new StompHeader(StompHeader.DESTINATION,"/app/game"));
            headers.add(new StompHeader("sessionId",sessionId));
            StompMessage stompMessage = new StompMessage(StompCommand.SEND, headers,message);
            client.send(stompMessage).subscribe(() -> {
                Log.d(TAG, "Message sent successfully");
            }, throwable -> {
                Log.d(TAG, "Error sending message", throwable);
            });
        } else {
            Log.d(TAG, "Client is not connected");
        }
    }

    public interface WebSocketMessageCallback{
        void onMessageReceived(String message);
    }
}
