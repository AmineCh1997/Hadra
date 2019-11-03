package com.dev.hadra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentRepository(private val apiService: APIService) {

    val TAG="CommentRepo"

    companion object {
        @Volatile private var instnace : CommentRepository? = null
        fun getInstance(apiService: APIService) : CommentRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: CommentRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }

    fun commentAdd(content:String,pcomment:String?,topic:String,user:String) : LiveData<Comment> {
        val comment = MutableLiveData<Comment>()
        apiService.commentAdd(content, pcomment , topic,user).enqueue(object : Callback<Comment> {
            override fun onFailure(call: Call<Comment>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                comment.value = response.body()
            }

        })
        return comment
    }

    fun commentGetByTopic(topic:String) : LiveData<List<Comment>> {
        val commentList = MutableLiveData<List<Comment>>()
        apiService.commentGetByTopic(topic).enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                commentList.value = response.body()
            }

        })
        return commentList
    }
}