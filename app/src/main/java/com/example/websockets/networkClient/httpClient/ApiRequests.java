package com.example.websockets.networkClient.httpClient;

import com.example.websockets.networkClient.httpClient.entity.response.SessionInfo;
import com.example.websockets.networkClient.httpClient.entity.response.ResponseSessionInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequests {

    @GET("createSession")
    Call<ResponseSessionInfo> createSession();

    @GET("sessions")
    Call<List<SessionInfo>> getSessions();
}
