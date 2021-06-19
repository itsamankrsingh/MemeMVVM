package com.example.mememvvm.memes

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mememvvm.database.MemesDao
import com.example.mememvvm.database.MemesEntity
import com.example.mememvvm.network.MemesApi
import com.example.mememvvm.network.Memes
import kotlinx.coroutines.launch


enum class MemeApiStatus { LOADING, ERROR, DONE }

class MemeViewModel(private val databaseSource: MemesDao, application: Application) :
    AndroidViewModel(application) {

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

    fun addToFavourites() {
        val memesEntity: MemesEntity = MemesEntity(
            _response.value!!.author,
            _response.value!!.title,
            _response.value!!.ups,
            _response.value!!.url
        )
        viewModelScope.launch {
            databaseSource.insert(memesEntity)
            Toast.makeText(getApplication(), "Added To Favourites", Toast.LENGTH_SHORT).show()
        }

    }
}