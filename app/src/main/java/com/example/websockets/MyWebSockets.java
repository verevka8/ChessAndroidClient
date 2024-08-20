package com.example.websockets;

import android.annotation.SuppressLint;
import android.util.Log;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;


import java.util.Collections;

public class MyWebSockets {
    private StompClient client;
    private final String TAG = "qqq";

    @SuppressLint("CheckResult")
    public MyWebSockets() {

        client = Stomp.over(Stomp.ConnectionProvider.JWS, "ws://192.168.1.245:8080/gs");
        client.connect();
        client.topic("/topic/greetings").subscribe(topicMessage -> {
            Log.d(TAG, "Received: " + topicMessage.getPayload());
        }, throwable -> {
            Log.d(TAG, "Error on subscribe", throwable);
        });

        // Логируем статус подключения
        client.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.d(TAG, "Error", lifecycleEvent.getException());
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });
    }

    // Метод для отправки сообщений
    @SuppressLint("CheckResult")
    public void sendMessage(String message) {
        if (client.isConnected()) {
            client.send("/app/hello", message).subscribe(() -> {
                Log.d(TAG, "Message sent successfully");
            }, throwable -> {
                Log.d(TAG, "Error sending message", throwable);
            });
        } else {
            Log.d(TAG, "Client is not connected");
        }
    }

    // Закрытие соединения при завершении работы
    public void disconnect() {
        if (client != null) {
            client.disconnect();
        }
    }
}