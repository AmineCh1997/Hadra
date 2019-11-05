package com.dev.hadra.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Topic : Serializable {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("subject")
    @Expose
    val subject: String? = null

    @SerializedName("content")
    @Expose
    val content: String? = null

    @SerializedName("createdAt")
    @Expose
    val createdAt: String? = null

    @SerializedName("category")
    @Expose
    val category: Category? = null

    @SerializedName("user")
    @Expose
    val user: User? = null

}