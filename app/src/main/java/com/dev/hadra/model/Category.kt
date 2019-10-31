package com.dev.hadra.model

import java.io.Serializable

data class Category(val id : String = "" ,  val name : String = "" , val color : String = "") : Serializable{
    override fun toString(): String {
        return "Category(id='$id', name='$name', color='$color')"
    }
}