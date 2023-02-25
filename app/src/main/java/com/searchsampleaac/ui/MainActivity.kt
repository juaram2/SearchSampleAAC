package com.searchsampleaac.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.searchsampleaac.databinding.ActivityMainBinding
import com.searchsampleaac.ui.adapter.OnUserClickListener
import com.searchsampleaac.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        binding.bindSearch()
    }

    private fun ActivityMainBinding.bindSearch() {
        searchInput.setOnEditorActionListener { _, editorInfo, _ ->
            if (editorInfo == EditorInfo.IME_ACTION_GO) {
                updateList()
                true
            } else {
                false
            }
        }
        searchInput.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateList()
                true
            } else {
                false
            }
        }
    }

    private fun ActivityMainBinding.updateList() {
        searchInput.text.trim().let {
            if (it.isNotEmpty()) {
                bindList(it)
            }
        }
    }

    private fun ActivityMainBinding.bindList(charSequence: CharSequence) {
        val listener = object : OnUserClickListener {
            override fun userClick(user: String) {
                TODO("Not yet implemented")
            }
        }
        val adapter = SearchAdapter(listener)
        list.adapter = adapter

        lifecycleScope.launch {
            viewModel.search(charSequence).collectLatest(adapter::submitData)
        }
    }
}