package com.example.chessgame.network;

import android.util.Log;

import com.example.chessgame.network.httpClient.ApiRequests;
import com.example.chessgame.network.httpClient.RetrofitService;
import com.example.chessgame.network.httpClient.callback.MyCallBack;
import com.example.chessgame.network.webSocketClient.MyWebSockets;
import com.example.chessgame.network.webSocketClient.entity.ChessMove;
import com.example.chessgame.network.webSocketClient.entity.MessageWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkClient implements MyWebSockets.WebSocketMessageCallback {
    private static NetworkClient instance;
    private final MyWebSockets webSockets;
    private final ApiRequests apiRequests;
    private Map<String, Listener> listeners = new HashMap<>();

    private final Gson gson;

    private NetworkClient(){
        apiRequests = RetrofitService.getInstance().getApiService();
        webSockets = new MyWebSockets(this);
        gson = new Gson();
    }

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public <T> void sendGetRequest(String url, MyCallBack<T> callBack, Type type) {
        Call<ResponseBody> call = apiRequests.getRequest(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        T result = new Gson().fromJson(response.body().string(), type);
                        callBack.onSuccess(result);
                    } catch (IOException e) {
                        callBack.onError(e);
                    }
                } else {
                    callBack.onError(new IOException("Response not successful: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onError(t);
            }
        });

    }
    public void connectToSession(String sessionId){
        webSockets.connectToSession(sessionId);
    }

    public <T> void sendMessage(T object, String typeJson, String sessionId){
        String jsonObject = gson.toJson(object);
        String jsonMessageWrapper = gson.toJson(new MessageWrapper(jsonObject,typeJson));
        webSockets.sendMessage(jsonMessageWrapper,sessionId);
    }

    @Override
    public void onMessageReceived(String message) {
        // JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
        // добавить разделение типов json  https://t.me/c/2187416232/2/36
        notifyListener("ChessMove",message);
    }


    public void registerListener(String idListener, Listener listener) {
        listeners.put(idListener, listener);
    }

    private void notifyListener(String id, String message) {
        Listener listener = listeners.get(id);
        if (listener != null) {
            listener.onDataReceived(message);
        } else {
            Log.d("NetworkClient", "No listener registered for: " + message);
        }
    }

    public interface Listener{
        void onDataReceived(String data); // поменять реализацию на более общую. идея с индификаторами класса и дессереализацией в этом классе на основе списка индификаторо и листенера
    }
}
