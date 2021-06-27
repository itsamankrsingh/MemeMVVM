package com.example.mememvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MemesDao {
    @Insert
    suspend fun insert(memesEntity: MemesEntity)

    @Query("SELECT * FROM memes_table ")
    suspend fun getAllMemes(): List<MemesEntity>

    @Delete
    suspend fun delete(memesEntity: MemesEntity)
}