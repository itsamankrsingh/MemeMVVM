package com.example.mememvvm.details

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mememvvm.database.MemesDao
import com.example.mememvvm.database.MemesEntity
import kotlinx.coroutines.launch


class DetailsViewModel(
    private val memes: MemesEntity,
    private val dataSource: MemesDao,
    application: Application
) :
    AndroidViewModel(application) {

   /* private val context by lazy {
        getApplication<Application>().applicationContext
    }*/

    private val _response = MutableLiveData<MemesEntity>()
    val response: LiveData<MemesEntity>
        get() = _response

    private val _navigateToMemesFragment = MutableLiveData<Boolean>()
    val navigateToMemesFragment: LiveData<Boolean>
        get() = _navigateToMemesFragment

    init {
        _response.value = memes
    }

    private fun delete() {
        viewModelScope.launch {
            dataSource.delete(memes)
        }
    }

    fun showDeleteDialogBox(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Do you want to delete this meme?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->

                    Toast.makeText(context, "Memes deleted successfully", Toast.LENGTH_LONG)
                        .show()
                    delete()
                    _navigateToMemesFragment.value = true

                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    //Do Nothing
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
    }

    fun shareMemes(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey checkout this cool meme I got from Reddit ${response.value?.url}"
        )
        val chooser = Intent.createChooser(intent, "Share this meme using...")
        context.startActivity(chooser)
    }

}