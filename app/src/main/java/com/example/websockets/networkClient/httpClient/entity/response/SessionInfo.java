package com.example.websockets.networkClient.httpClient.entity.response;

public class SessionInfo {
    private String sessionId;

    public SessionInfo() {}

    public SessionInfo(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
