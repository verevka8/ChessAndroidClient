package com.example.websockets.networkClient.callback;

import com.example.websockets.networkClient.httpClient.entity.response.SessionInfo;

import java.util.List;

public interface SessionsCallback {
    void onSuccess(List<SessionInfo> sessions);
    void onFailure(Throwable t);
}
