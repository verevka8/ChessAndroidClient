package com.example.chessgame.network.httpClient.callback;

public  interface MyCallBack<T> {
    void onSuccess(T response);
    void onError(Throwable t);
}
