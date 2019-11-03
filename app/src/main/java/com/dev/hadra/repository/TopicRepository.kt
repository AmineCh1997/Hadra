package com.dev.hadra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.Category
import com.dev.hadra.model.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicRepository(private var apiService: APIService) {
    val TAG="TopicRepo"

    companion object {
        @Volatile private var instnace : TopicRepository? = null
        fun getInstance(apiService: APIService) : TopicRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: TopicRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }

    fun topicAdd(subject:String,content:String,category:String) : LiveData<Topic> {
        val topic = MutableLiveData<Topic>()
        apiService.topicAdd(subject, content, category).enqueue(object : Callback<Topic>{
            override fun onFailure(call: Call<Topic>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<Topic>, response: Response<Topic>) {
                topic.value = response.body()
            }

        })
        return topic
    }

    fun topicGetById(id:String) : LiveData<Topic> {
        val topic = MutableLiveData<Topic>()
        apiService.topicGetById(id).enqueue(object : Callback<Topic>{
            override fun onFailure(call: Call<Topic>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<Topic>, response: Response<Topic>) {
                topic.value = response.body()
            }

        })
        return topic
    }
}