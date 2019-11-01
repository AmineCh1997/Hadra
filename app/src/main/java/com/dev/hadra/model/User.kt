package com.dev.hadra.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class User {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("username")
    @Expose
    val username: String? = null

    @SerializedName("createdAt")
    @Expose
    val createdAt: String? = null

    @SerializedName("token")
    @Expose
    val token: String? = null



    override fun toString(): String {
        return username!!
    }

}