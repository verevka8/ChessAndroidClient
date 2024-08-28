package com.example.websockets.networkClient.httpClient;

import com.example.websockets.networkClient.httpClient.entity.response.ResponseSessionCreation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequests {

    @GET("createSession")
    Call<ResponseSessionCreation> createSession();
}
