package com.dev.hadra.viewmodel


import androidx.lifecycle.ViewModel
import com.dev.hadra.model.Category
import com.dev.hadra.repository.CategoryRepository
import com.dev.hadra.repository.TopicRepository

class HomeViewModel(private val categoryRepository: CategoryRepository,private val topicRepository: TopicRepository) : ViewModel() {

    fun categoryAll() = categoryRepository.categoryAll()
    fun categoryGetById(id:String) = categoryRepository.categoryGetById(id)
    fun topicAdd(subject:String,content:String,category:Category) = topicRepository.topicAdd(subject, content, category)
}