package com.example.mememvvm.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.R
import com.example.mememvvm.database.MemesDatabase
import com.example.mememvvm.databinding.FragmentFavouritesBinding


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: FavouritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val datasource = MemesDatabase.getInstance(application).memesDao
        val viewModelFactory = FavouritesViewModelFactory(datasource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavouritesViewModel::class.java)


        val adapter = FavouritesAdapter()
        binding.favRecyclerView.adapter = adapter



        viewModel.memesList.observe(viewLifecycleOwner, Observer { memesList ->
            memesList.let {
                adapter.submitList(memesList)
            }
        })

        binding.lifecycleOwner = this



        return binding.root
    }


}