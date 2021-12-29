package com.mateuslima.dicionarioclean.feature_dicionario.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.mateuslima.dicionarioclean.R
import com.mateuslima.dicionarioclean.core.util.onTextSubmit
import com.mateuslima.dicionarioclean.databinding.ActivityMainBinding
import com.mateuslima.dicionarioclean.feature_dicionario.presentation.adapter.DicionarioAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DicionarioAdapter()
        viewModel.palavraList.observe(this){palavraList ->
            adapter.submitList(palavraList)
        }

        viewModel.state.asLiveData().observe(this){state ->
            binding.progressBar.isVisible = state.isLoading
        }



        binding.recyclerPalavras.apply { //
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
            setHasFixedSize(true)
        }

        binding.searchview.onTextSubmit { search -> viewModel.search.value = search }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}