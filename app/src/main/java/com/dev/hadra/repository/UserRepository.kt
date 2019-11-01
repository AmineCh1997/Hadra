package com.dev.hadra.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(private val apiService: APIService){
    val TAG = "USER_REPOSITORY"

    companion object {
        @Volatile private var instnace : UserRepository? = null
        fun getInstance(apiService: APIService) : UserRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: UserRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }

    fun getAllUsers() : LiveData<List<User>>{
        val allUsers = MutableLiveData<List<User>>()
        apiService.allUsers.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                allUsers.value=response.body()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e(TAG,"onfailure : "+t.message)
            }
        })
        return allUsers
    }

    fun login(username:String,password:String) : LiveData<User>{
        val user = MutableLiveData<User>()
        apiService.login(username, password).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user.value=response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG,"onfailure : "+t.message)
            }
        })
        return user
    }

    fun register(username:String,email:String,password:String) : LiveData<User>{
        val user = MutableLiveData<User>()
        apiService.register(username,email,password).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user.value=response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG,"onfailure : "+t.message)
            }
        })
        return user
    }

    fun update(username:String,password:String,id:String) : LiveData<User>{
        val user = MutableLiveData<User>()
        apiService.update(username, password,id).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user.value=response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG,"onfailure : "+t.message)
            }
        })
        return user
    }
    fun getUserById(id:String) : LiveData<User>{
        val user = MutableLiveData<User>()
        apiService.getUserById(id).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user.value=response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG,"onfailure : "+t.message)
            }
        })
        return user
    }

}