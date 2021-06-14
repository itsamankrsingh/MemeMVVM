package com.example.mememvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mememvvm.network.Meme
import com.example.mememvvm.network.MemeApi
import kotlinx.coroutines.launch


enum class MemeApiStatus { LOADING, ERROR, DONE }

class MemeViewModel() : ViewModel() {
    private val _response = MutableLiveData<Meme>()
    val response: LiveData<Meme>
        get() = _response

    private val _status = MutableLiveData<MemeApiStatus>()
    val status: LiveData<MemeApiStatus>
        get() = _status

    init {
        getMeme()
    }

    private fun getMeme() {
        viewModelScope.launch {
            try {
                _response.value = MemeApi.retrofitService.getMeme()
                _status.value = MemeApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MemeApiStatus.ERROR
            }
        }
    }

    fun nextMeme() {
        getMeme()
    }
}