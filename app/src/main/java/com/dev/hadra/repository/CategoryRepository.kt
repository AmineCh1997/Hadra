package com.dev.hadra.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.hadra.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository private constructor( private val apiService: APIService){
    val TAG="CategoryRepo"

    companion object {
        @Volatile private var instnace : CategoryRepository? = null
        fun getInstance(apiService: APIService) : CategoryRepository{
            instnace ?: synchronized(lock = this){
                instnace ?: CategoryRepository(apiService).also { instnace = it }
            }
            return instnace!!
        }
    }

    fun categoryAll() : LiveData<List<Category>> {
        val allCategories = MutableLiveData<List<Category>>()
        apiService.allCategories.enqueue(object : Callback<List<Category>>{
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                allCategories.value = response.body()
            }

        })
        return allCategories
    }

    fun categoryGetById(id:String) : LiveData<Category>{
        val category = MutableLiveData<Category>()
        apiService.categoryGetById(id).enqueue(object : Callback<Category>{
            override fun onFailure(call: Call<Category>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                category.value = response.body()
            }

        })
        return category
    }
}