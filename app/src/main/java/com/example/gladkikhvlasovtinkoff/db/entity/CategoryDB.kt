package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "category",
    primaryKeys = ["user_name","stringId"])
data class CategoryDB(
    @ColumnInfo(name = "user_name")
    val userName: String,
    val name: String,
    val stringId: String,
    val description: String,
    val colorRed: Int,
    val colorBlue: Int,
    val colorGreen: Int,
    val income: Boolean
)