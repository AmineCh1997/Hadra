package com.dev.hadra.viewmodel

import androidx.lifecycle.ViewModel
import com.dev.hadra.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getAllUsers() = userRepository.getAllUsers()
    fun login(username:String,password:String) = userRepository.login(username,password)
    fun register(username:String,email:String, password:String) = userRepository.register(username,email, password)
    fun update(username:String,password:String,id:String) = userRepository.update(username,password,id)
    fun getUserById(id:String) = userRepository.getUserById(id)

}