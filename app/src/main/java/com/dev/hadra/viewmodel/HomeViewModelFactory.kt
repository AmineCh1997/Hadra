package com.dev.hadra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.hadra.repository.CategoryRepository
import com.dev.hadra.repository.TopicRepository

class HomeViewModelFactory(private val categoryRepository: CategoryRepository,private val topicRepository: TopicRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(categoryRepository,topicRepository) as T
    }
}