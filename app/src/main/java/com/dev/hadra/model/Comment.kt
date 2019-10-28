package com.dev.hadra.model

import java.io.Serializable

class Comment : Serializable {
    var id : String = ""
    var content : String = ""
    var created_at : String = ""
    var topic : String = ""
    var user : String = ""

    constructor(){}



    constructor(id: String, content: String, created_at: String, topic: String, user: String) {
        this.id = id
        this.content = content
        this.created_at = created_at
        this.topic = topic
        this.user = user
    }
}
