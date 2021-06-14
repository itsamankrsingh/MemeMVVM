package com.example.mememvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mememvvm.network.Meme
import com.example.mememvvm.network.MemeApi
import kotlinx.coroutines.launch

class MemeViewModel() : ViewModel() {
    private val _response = MutableLiveData<Meme>()
    val response: LiveData<Meme>
        get() = _response

    init {
        getMeme()
    }

   private fun getMeme() {
        viewModelScope.launch {
            _response.value = MemeApi.retrofitService.getMeme()
        }
    }

    fun nextMeme(){
        getMeme()
    }
}