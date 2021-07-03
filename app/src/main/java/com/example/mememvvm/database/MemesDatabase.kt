package com.example.mememvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemesEntity::class], version = 2, exportSchema = false)
abstract class MemesDatabase : RoomDatabase() {
    abstract val memesDao: MemesDao

    companion object {

        @Volatile
        private var INSTANCE: MemesDatabase? = null
        fun getInstance(context: Context): MemesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemesDatabase::class.java,
                        "memes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}