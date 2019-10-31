package com.dev.hadra.model

import java.io.Serializable
import java.sql.Timestamp

class Topic : Serializable {
    var id : String = ""
    var subject : String = ""
    var content : String = ""
    lateinit var created_at : com.google.firebase.Timestamp
    var cat = Category()
    var user = User()

    constructor(){}

    constructor(
        id: String,
        subject: String,
        content: String,
        created_at: com.google.firebase.Timestamp,
        cat: Category,
        user: User
    ) {
        this.id = id
        this.subject = subject
        this.content = content
        this.created_at = created_at
        this.cat = cat
        this.user = user
    }

    override fun toString(): String {
        return "Topic(id='$id', subject='$subject', content='$content', created_at='$created_at', cat=$cat, user=$user)"
    }


}