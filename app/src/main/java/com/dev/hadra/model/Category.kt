package com.dev.hadra.model

import java.io.Serializable

class Category : Serializable{

    var id: String = ""
    var name: String = ""
    var color: String = ""

    

    constructor(id: String,name: String ,color: String) {
        this.id = id
        this.name = name
        this.color= color
    }
}