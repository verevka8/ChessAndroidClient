package com.example.websockets.networkClient.webSocketClient;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompCommand;
import ua.naiksoftware.stomp.dto.StompHeader;
import ua.naiksoftware.stomp.dto.StompMessage;

public class MyWebSockets {
    private StompClient client;
    private final String TAG = "MyWebSockets: qqq";
    private final String BASE_URL = "ws://192.168.1.246:8080/gs";
    private String sessionId;


    public MyWebSockets(String sessionId) {
        this.sessionId = sessionId;
        connectToSession();
    }

    @SuppressLint("CheckResult")
    private void connectToSession(){
        client = Stomp.over(Stomp.ConnectionProvider.JWS, BASE_URL);
        client.connect();
        client.topic("/topic/session/" + sessionId).subscribe(topicMessage -> {
            Log.d(TAG, "Received: " + topicMessage.getPayload());
        }, throwable -> {
            Log.d(TAG, "Error on subscribe", throwable);
        });

        client.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {

                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
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
    public void sendMessage(String message) {
        if (client.isConnected()) {

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

    public void disconnect() {
        if (client != null) {
            client.disconnect();
        }
    }
}