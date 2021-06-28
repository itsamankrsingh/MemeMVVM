package com.example.mememvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "memes_table")
data class MemesEntity constructor(
    @PrimaryKey
    //val id: Long,
    val author: String,
    val title: String,
    val ups: Int,
    val url: String
):Serializable

