package com.example.mememvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mememvvm.databinding.FragmentMemeBinding
import com.example.mememvvm.network.Meme
import com.example.mememvvm.network.MemeApi


class MemeFragment : Fragment() {

    private lateinit var binding: FragmentMemeBinding
    private lateinit var viewModel: MemeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(MemeViewModel::class.java)
        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        return binding.root
    }


}