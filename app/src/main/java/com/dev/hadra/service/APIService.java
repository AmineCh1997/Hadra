package com.dev.hadra.service;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("api/register")
    Observable<Object> signUpUser(@Query("psudo")  String pseudo, @Query("email") String email, @Query("password") String password, @Query("birth_day") String birthDay);

}
