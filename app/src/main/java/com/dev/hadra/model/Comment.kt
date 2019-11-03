package com.dev.hadra.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Comment : Serializable {
    @SerializedName("_id")
    @Expose
     val id: String? = null
    @SerializedName("content")
    @Expose
     val content: String? = null
    @SerializedName("createdAt")
    @Expose
     val createdAt: String? = null
    @SerializedName("topic")
    @Expose
     val topic: String? = null
    @SerializedName("user")
    @Expose
     val user: User? = null

}
