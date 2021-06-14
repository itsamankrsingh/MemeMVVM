package com.example.mememvvm.memes

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mememvvm.network.Memes
import com.example.mememvvm.network.MemesApi
import kotlinx.coroutines.launch


enum class MemeApiStatus { LOADING, ERROR, DONE }

class MemeViewModel() : ViewModel() {
    private val _response = MutableLiveData<Memes>()
    val response: LiveData<Memes>
        get() = _response

    private val _status = MutableLiveData<MemeApiStatus>()
    val status: LiveData<MemeApiStatus>
        get() = _status

    init {
        getMemes()
    }

    private fun getMemes() {
        viewModelScope.launch {
            try {
                _response.value = MemesApi.retrofitService.getMemes()
                _status.value = MemeApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MemeApiStatus.ERROR
            }
        }
    }

    fun nextMemes() {
        getMemes()
    }

    fun shareMemes(context: Context?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey checkout this cool meme I got from Reddit ${response.value?.url}"
        )
        val chooser = Intent.createChooser(intent, "Share this meme using...")
        context?.startActivity(chooser)
    }
}