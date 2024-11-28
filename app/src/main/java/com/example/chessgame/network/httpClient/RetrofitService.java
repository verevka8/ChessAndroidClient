package com.example.chessgame.network.httpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitService instance;
    private static final String BASE_URL = "http://192.168.1.78:8080/api/"; // заменить на парс из конфига
    private static Retrofit retrofit;
    private static ApiRequests apiRequests;

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    private RetrofitService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public ApiRequests getApiService(){
        if (apiRequests == null){
            apiRequests = retrofit.create(ApiRequests.class);
        }
        return apiRequests;
    }
}
