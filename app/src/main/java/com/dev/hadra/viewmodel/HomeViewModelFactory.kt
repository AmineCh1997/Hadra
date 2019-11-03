package com.dev.hadra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.hadra.repository.*

class HomeViewModelFactory(private val categoryRepository: CategoryRepository
                           ,private val topicRepository: TopicRepository,
                           private val followRepository: FollowRepository
                           ,private val commentRepository: CommentRepository,
                           private val likeRepository: LikeRepository
                           ,private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(categoryRepository,topicRepository,followRepository, commentRepository, likeRepository, userRepository) as T
    }
}