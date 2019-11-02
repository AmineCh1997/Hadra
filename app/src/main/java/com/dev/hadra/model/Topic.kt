package com.dev.hadra.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.sql.Timestamp
import java.util.*

data class Topic (var id : String = "",
                 var subject : String = "",
                 var content : String = "",
                 @ServerTimestamp var created_at : Date? = null,
                 var cat : Category? = null,
                 var user : User? = null) : Serializable  {

    override fun toString(): String {
        return "Topic(id='$id', subject='$subject', content='$content', created_at='$created_at', cat=$cat, user=$user)"
    }




}