package com.azhariangirls.shefaa2.model

data class Disease(val id: Int,
                   val title: String,
                   val icon: String,
                   val details: String) {

    val uri: String
        get() = "drawable/$icon"
}