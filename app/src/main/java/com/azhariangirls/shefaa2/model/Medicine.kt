package com.azhariangirls.shefaa2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(val name: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}