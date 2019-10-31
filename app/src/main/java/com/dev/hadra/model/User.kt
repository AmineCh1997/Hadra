package com.dev.hadra.model

import java.io.Serializable

data class User (val id : String="",val email : String="" , val username : String="" ): Serializable {

    override fun toString(): String {
        return "User(id='$id', email='$email', username='$username')"
    }
}