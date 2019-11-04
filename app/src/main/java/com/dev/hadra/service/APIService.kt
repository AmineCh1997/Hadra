package com.dev.hadra.repository

import com.dev.hadra.model.*

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

    @GET("topic/getByCategory/{id}")
    fun topicGetByCategory(@Path("id")category:String): Call<List<Topic>>

    @POST("topic/add")
    @FormUrlEncoded
    fun topicAdd(@Field("subject")subject:String,@Field("content")content:String,@Field("category")category: String,@Field("user")user: String) : Call<Topic>

    @GET("comment/{id}")
    fun commentGetById(@Path("id")id:String): Call<Comment>

    @POST("comment/add")
    @FormUrlEncoded
    fun commentAdd(@Field("content")content:String,@Field("comment")comment:String?,@Field("topic")topic: String,@Field("user")user:String) : Call<Comment>

    @GET("comment/getByTopic/{id}")
    fun commentGetByTopic(@Path("id")topic:String): Call<List<Comment>>

    @POST("like/add")
    fun likeAdd(@Field("topic")topic:String?,@Field("comment")comment: String?,@Field("user")user:String,@Field("status")status:Boolean): Call<Like>

    @GET("like/getByTopic/{id}")
    fun likeGetByTopic(@Path("id")topic:String): Call<List<Like>>

    @GET("like/getByComment/{id}")
    fun likeGetByComment(@Path("id")comment:String): Call<List<Like>>

    @POST("follow/add")
    fun followAdd(@Field("category")category:String,@Field("user")user: String): Call<Follow>

    @GET("follow/getByUser/{id}")
    fun followGetByUser(@Path("id")user: String): Call<List<Follow>>

}
