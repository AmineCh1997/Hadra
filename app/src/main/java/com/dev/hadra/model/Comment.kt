package com.dev.hadra.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.*

class Comment : Serializable {
    var id : String = ""
    var content : String = ""
    @ServerTimestamp
    var created_at : Date? = null
    var user : User? = null

    constructor(){}


    constructor(id: String, content: String, created_at: Date, user: User) {
        this.id = id
        this.content = content
        this.created_at = created_at
        this.user = user
    }
}
