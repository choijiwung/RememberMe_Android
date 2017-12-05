package com.rememberme.rememberme;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by JW on 2017-12-05.
 */

public interface ApiInterface {
    @Headers({"Accept: application/json"})
    @POST("/users/signin")
    Call<User> login(@Query("email") String email,
                    @Query("password") String password );


//    @POST("/users/signup")
//    Call<User> getrepo(@Path("email") String email, @Body User user);

    @FormUrlEncoded
    @POST("/users/signup")
    Call<User> signup(@Field("email") String email,
                @Field("password") String password,
                @Field("name") String name,
                Callback<JSONObject> callback);


}
