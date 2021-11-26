package com.example.hiltcoroutinesflow.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.hiltcoroutinesflow.R
import com.example.hiltcoroutinesflow.databinding.ActivityMainBinding
import com.example.hiltcoroutinesflow.utils.ui.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSampleResponse().observe(this, Observer {
            when (it) {
                is State.Empty -> {
                    customDialog.dismiss()
                    binding.tvContent.text = "empty"
                }
                is State.DataState -> {
                    customDialog.dismiss()
                    binding.tvContent.text = "success ${it.data.total_pages} ${it.data.total}"
                }
                is State.ErrorState -> {
                    customDialog.dismiss()
                    binding.tvContent.text = "error ${it.exception.message}"
                }
                is State.LoadingState -> customDialog.showLoading(this)
            }
        })
    }
}