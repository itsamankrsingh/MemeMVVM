package com.example.mememvvm.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mememvvm.database.MemesDao
import com.example.mememvvm.database.MemesEntity
import kotlinx.coroutines.launch

class FavouritesViewModel(private val dataSource: MemesDao) : ViewModel() {

    private val _memesList = MutableLiveData<List<MemesEntity>>()
    val memesList: LiveData<List<MemesEntity>>
        get() = _memesList

    init {
        getAllMemes()
    }

    fun getAllMemes() {
        viewModelScope.launch {
            _memesList.value = dataSource.getAllMemes()
        }
    }

    fun delete(memes: MemesEntity) {
        viewModelScope.launch {
            dataSource.delete(memes)
        }
    }
}