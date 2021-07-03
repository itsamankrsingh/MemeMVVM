package com.example.mememvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "memes_table")
data class MemesEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val title: String,
    val ups: Int,
    val url: String
):Serializable

