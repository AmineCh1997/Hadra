package com.dev.hadra.model

import java.io.Serializable

data class Category(var id : String = "" ,  var name : String = "" , var color : String = "") : Serializable{
    override fun toString(): String {
        return "Category(id='$id', name='$name', color='$color')"
    }
}