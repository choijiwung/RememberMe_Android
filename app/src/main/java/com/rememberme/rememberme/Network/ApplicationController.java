package com.rememberme.rememberme.Network;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JW on 2017-12-09.
 */

public class ApplicationController extends Application {
    // 나중에 만약 포트 뺀다면, 수정.. 아직은 건들지 말것
    private static ApplicationController instance;
    private final int PORT = 3000;
    private final String URL = "http://70.12.50.58".concat(":"+PORT);

    public static ApplicationController getInstance() {
        return instance;
    }
    public APINetwork getApiNetwork() {
        return network;
    }
    private APINetwork network;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        buildNetwork();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    private void buildNetwork() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        network = retrofit.create(APINetwork.class);
    }

}
