package com.example.mememvvm.database

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface MemesDao {
    @Insert
    suspend fun insert(memesEntity: MemesEntity)
}