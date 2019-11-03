package com.dev.hadra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.Follow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowRepository(private val apiService: APIService) {

    val TAG="FollowRepo"

    companion object {
        @Volatile private var instnace : FollowRepository? = null
        fun getInstance(apiService: APIService) : FollowRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: FollowRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }
    fun followAdd(category:String,user:String) : LiveData<Follow> {
        val follow = MutableLiveData<Follow>()
        apiService.followAdd(category,user).enqueue(object : Callback<Follow> {
            override fun onFailure(call: Call<Follow>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<Follow>, response: Response<Follow>) {
                follow.value = response.body()
            }

        })
        return follow
    }

    fun followGetByUser(user:String) : LiveData<List<Follow>> {
        val followList = MutableLiveData<List<Follow>>()
        apiService.followGetByUser(user).enqueue(object : Callback<List<Follow>> {
            override fun onFailure(call: Call<List<Follow>>, t: Throwable) {
                Log.e(TAG,t.message)
            }

            override fun onResponse(call: Call<List<Follow>>, response: Response<List<Follow>>) {
                followList.value = response.body()
            }

        })
        return followList
    }
    

}