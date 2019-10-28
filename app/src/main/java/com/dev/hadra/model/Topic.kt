package com.dev.hadra.model

import java.io.Serializable

class Topic : Serializable {
    var id : String = ""
    var subject : String = ""
    var content : String = ""
    var created_at : String = ""

    constructor(){}

    constructor(id : String , hashtag : String ,description : String , created_at : String,watchs : Int){
        this.id = id
        this.subject = hashtag
        this.content = description
        this.created_at = created_at

    }


}