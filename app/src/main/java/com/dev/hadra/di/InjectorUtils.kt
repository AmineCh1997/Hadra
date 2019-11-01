package com.dev.hadra.di

import com.dev.hadra.repository.CategoryRepository
import com.dev.hadra.repository.TopicRepository
import com.dev.hadra.repository.UserRepository
import com.dev.hadra.utils.APIUtils
import com.dev.hadra.viewmodel.HomeViewModelFactory
import com.dev.hadra.viewmodel.UserViewModelFactory

object InjectorUtils {
    fun provideUserViewModelFactory() : UserViewModelFactory{
        val userRepository = UserRepository.getInstance(APIUtils.webService())
        return UserViewModelFactory(userRepository)
    }
    fun provideUHomeViewModelFactory() : HomeViewModelFactory{
        val categoryRepository = CategoryRepository.getInstance(APIUtils.webService())
        val topicRepository = TopicRepository.getInstance(APIUtils.webService())
        return HomeViewModelFactory(categoryRepository,topicRepository)
    }

}