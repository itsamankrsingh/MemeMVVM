package com.example.mememvvm.memes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.R
import com.example.mememvvm.database.MemesDatabase
import com.example.mememvvm.databinding.FragmentMemeBinding


class MemesFragment : Fragment() {

    private lateinit var binding: FragmentMemeBinding
    private lateinit var viewModel: MemeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemeBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val datasource = MemesDatabase.getInstance(application).memesDao
        val memeViewModelFactory = MemesViewModelFactory(datasource, application)
        viewModel = ViewModelProvider(this, memeViewModelFactory).get(MemeViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_menu -> viewModel.shareMemes(context)
        }
        return true
    }
}