package com.dev.hadra.di

import com.dev.hadra.repository.UserRepository
import com.dev.hadra.utils.APIUtils
import com.dev.hadra.viewmodel.UserViewModelFactory

object InjectorUtils {
    fun provideUserViewModelFactory() : UserViewModelFactory{
        val userRepository = UserRepository.getInstance(APIUtils.webService())
        return UserViewModelFactory(userRepository)
    }
}