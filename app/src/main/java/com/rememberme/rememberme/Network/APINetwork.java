package com.rememberme.rememberme.Network;

import com.rememberme.rememberme.User.Results.LoginResult;
import com.rememberme.rememberme.User.Results.SignUpResult;
import com.rememberme.rememberme.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by JW on 2017-12-09.
 */

public interface APINetwork {
    //라우터 부분

    @POST("/users/signin")
    Call<LoginResult> getLoginResult(@Body User user);

    @POST("/users/signup")
    Call<SignUpResult> getSignUpResult(@Body User user);
}
