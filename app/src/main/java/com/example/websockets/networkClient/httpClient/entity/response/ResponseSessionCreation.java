package com.example.websockets.networkClient.httpClient.entity.response;

public class ResponseSessionCreation {
    private String stateOfCreation;
    private String sessionId;

    public ResponseSessionCreation(String stateOfCreation, String sessionId) {
        this.stateOfCreation = stateOfCreation;
        this.sessionId = sessionId;
    }

    public String getStateOfCreation() {
        return stateOfCreation;
    }
    public String getSessionId() {
        return sessionId;
    }
}

