package com.dev.hadra.di

import com.dev.hadra.repository.*
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
        val commentRepository = CommentRepository.getInstance(APIUtils.webService())
        val likeRepository = LikeRepository.getInstance(APIUtils.webService())
        val followRepository = FollowRepository.getInstance(APIUtils.webService())
        val userRepository = UserRepository.getInstance(APIUtils.webService())
        return HomeViewModelFactory(categoryRepository,topicRepository,followRepository, commentRepository, likeRepository, userRepository)
    }

}