package com.dev.hadra.viewmodel


import androidx.lifecycle.ViewModel
import com.dev.hadra.model.Category
import com.dev.hadra.repository.*

class HomeViewModel(private val categoryRepository: CategoryRepository, private val topicRepository: TopicRepository,
                    private val followRepository: FollowRepository
                    , private val commentRepository: CommentRepository,
                    private val likeRepository: LikeRepository
                    , private val userRepository: UserRepository) : ViewModel() {

    fun categoryAll() = categoryRepository.categoryAll()
    fun categoryGetById(id:String) = categoryRepository.categoryGetById(id)
    fun topicAdd(subject:String,content:String,category:String,user: String) = topicRepository.topicAdd(subject, content, category,user)
    fun topicGetById(id:String) = topicRepository.topicGetById(id)
    fun topicGetByCategory(category: String) = topicRepository.topicGetByCategory(category)
    fun commentAdd(content:String,pcomment:String?,topic:String,user:String) = commentRepository.commentAdd(content, pcomment, topic, user)
    fun commentGetByTopic(topic: String) = commentRepository.commentGetByTopic(topic)
    fun likeAdd(topic:String,comment:String?,user:String,status:Boolean) = likeRepository.likeAdd(topic, comment, user, status)
    fun likeGetByTopic(topic: String) = likeRepository.likeGetByTopic(topic)
    fun likeGetByComment(comment: String) = likeRepository.likeGetByComment(comment)
    fun followAdd(category: String,user: String) = followRepository.followAdd(category, user)
    fun followGetByUser(user: String) = followRepository.followGetByUser(user)
}