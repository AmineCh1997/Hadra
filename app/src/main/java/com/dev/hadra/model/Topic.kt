package com.dev.hadra.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Topic : Serializable {

    @SerializedName("subject")
    @Expose
    val subject: String? = null
    @SerializedName("content")
    @Expose
    val content: String? = null
    @SerializedName("category")
    @Expose
    val category: Category? = null


}