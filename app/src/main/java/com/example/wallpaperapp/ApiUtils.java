package com.example.wallpaperapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static Retrofit retrofit = null;
    public static final String API = "563492ad6f91700001000001fba87bd630f34f4088f7ec6565d55aca";

    public static ApiInterface getApiInterface(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
