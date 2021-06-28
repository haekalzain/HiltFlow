package com.example.hiltcoroutinesflow.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hiltcoroutinesflow.R
import com.example.hiltcoroutinesflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(500)
            viewModel.getSampleResponse()
                .collect {
                    when (it) {
                        is State.DataState -> binding.tvContent.text = "success ${it.data}"
                        is State.ErrorState -> binding.tvContent.text = "error ${it.exception}"
                        is State.LoadingState -> binding.tvContent.text = "loading"
                    }
                }
        }
    }
}