package com.searchsampleaac.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.searchsampleaac.databinding.ActivityRepoBinding
import com.searchsampleaac.ui.adapter.ReposAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        val username = intent.getStringExtra("user")
        username?.let {
            binding.bindList(username)
        }
    }

    private fun ActivityRepoBinding.bindList(username: String) {
        val adapter = ReposAdapter()
        repos.adapter = adapter

        lifecycleScope.launch {
            viewModel.getRepo(username).collectLatest(adapter::submitData)
        }
    }
}