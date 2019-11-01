package com.dev.hadra.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category : Serializable{

    @SerializedName("_id")
    @Expose
    val id: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("color")
    @Expose
    val color: String? = null



}