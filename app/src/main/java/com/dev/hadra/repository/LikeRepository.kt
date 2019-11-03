package com.dev.hadra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.Like
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeRepository(private val apiService: APIService) {
    val TAG="LikeRepo"

    companion object {
        @Volatile private var instnace : LikeRepository? = null
        fun getInstance(apiService: APIService) : LikeRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: LikeRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }

    fun likeAdd(topic:String,comment:String?,user:String,status:Boolean) : LiveData<Like> {
        val like = MutableLiveData<Like>()
        apiService.likeAdd(topic, comment, user, status).enqueue(object : Callback<Like> {
            override fun onFailure(call: Call<Like>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<Like>, response: Response<Like>) {
                like.value = response.body()
            }

        })
        return like
    }

    fun likeGetByTopic(topic:String) : LiveData<List<Like>> {
        val likeList = MutableLiveData<List<Like>>()
        apiService.likeGetByTopic(topic).enqueue(object : Callback<List<Like>> {
            override fun onFailure(call: Call<List<Like>>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<List<Like>>, response: Response<List<Like>>) {
                likeList.value = response.body()
            }

        })
        return likeList
    }

    fun likeGetByComment(comment:String) : LiveData<List<Like>> {
        val likeList = MutableLiveData<List<Like>>()
        apiService.likeGetByComment(comment).enqueue(object : Callback<List<Like>> {
            override fun onFailure(call: Call<List<Like>>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<List<Like>>, response: Response<List<Like>>) {
                likeList.value = response.body()
            }

        })
        return likeList
    }
}