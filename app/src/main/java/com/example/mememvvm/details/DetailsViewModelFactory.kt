package com.example.mememvvm.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.database.MemesDao
import com.example.mememvvm.database.MemesEntity

class DetailsViewModelFactory(
    private val memes: MemesEntity,
    private val datasource: MemesDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(memes, datasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}