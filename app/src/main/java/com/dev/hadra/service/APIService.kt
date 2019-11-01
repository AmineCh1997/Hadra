package com.dev.hadra.repository

import com.dev.hadra.model.Category
import com.dev.hadra.model.Topic
import com.dev.hadra.model.User

import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @get:GET("user/")
    val allUsers: Call<List<User>>

    @POST("user/authenticate")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password : String): Call<User>

    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String, @Field("email") email: String, @Field("password") password : String): Call<User>

    @PUT("user/{id}")
    @FormUrlEncoded
    fun update(@Field("username") username: String, @Field("password") password : String, @Path("id")id:String): Call<User>

    @GET("user/{id}")
    fun getUserById(@Path("id")id:String): Call<User>

    @get:GET("category/")
    val allCategories : Call<List<Category>>

    @GET("category/{id}")
    fun categoryGetById(@Path("id")id:String): Call<Category>

    @GET("topic/{id}")
    fun topicGetById(@Path("id")id:String): Call<Topic>

    @POST("topic/add")
    @FormUrlEncoded
    fun topicAdd(@Field("subject")subject:String,@Field("content")content:String,@Field("Category")category: Category) : Call<Topic>



}
