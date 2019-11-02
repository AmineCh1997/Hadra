package com.dev.hadra.model

import java.io.Serializable

data class User (var id : String="",var email : String="" , var username : String="" ): Serializable {

    override fun toString(): String {
        return "User(id='$id', email='$email', username='$username')"
    }

}