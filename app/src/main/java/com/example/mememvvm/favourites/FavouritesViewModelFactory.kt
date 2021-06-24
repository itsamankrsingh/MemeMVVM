package com.example.mememvvm.favourites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.database.MemesDao
import java.lang.IllegalArgumentException


class FavouritesViewModelFactory(
    private val datasource: MemesDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            return FavouritesViewModel(datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}