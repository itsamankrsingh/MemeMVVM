package com.example.mememvvm.memes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.database.MemesDao
import java.lang.IllegalArgumentException


class MemesViewModelFactory(
    private val datasource: MemesDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemeViewModel::class.java)) {
            return MemeViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}