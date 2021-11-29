package com.example.hiltcoroutinesflow.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hiltcoroutinesflow.databinding.ActivityMainBinding
import com.example.hiltcoroutinesflow.utils.ui.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    private val REGIST_IMAGE = 1

    private var isRegist: Boolean = false

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnRegist.setOnClickListener {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(cameraIntent, REGIST_IMAGE)
                    isRegist = true
                }

            }

            btnVerification.setOnClickListener {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(cameraIntent, REGIST_IMAGE)
                    isRegist = false
                }
            }
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                try {
                    val photo: Bitmap = data.extras?.get("data") as Bitmap
                    val baos = ByteArrayOutputStream()
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val b: ByteArray = baos.toByteArray()
                    val encImage: String = Base64.encodeToString(b, Base64.DEFAULT)
                    if (requestCode == REGIST_IMAGE) {
                        when {
                            isRegist -> submitRegist(encImage)
                            else -> submitVerif(encImage)
                        }
                    }
                } catch (e: java.lang.Exception) {
                    Log.e(
                        "CDDAct",
                        "onActivityResult: Data Null because it is saved to file. ",
                        e
                    )
                }
            } else {
                val imgUserProfilesUri = data?.data
            }
        }
    }

    private fun submitVerif(encImage: String) {
        viewModel.submitVerifyBiometric(binding.inputCifId.text.toString(), encImage)
            .observe(this, Observer {
                when (it) {
                    is State.Empty -> {
                        customDialog.dismiss()
                        binding.verifResult.text = "empty"
                    }
                    is State.DataState -> {
                        customDialog.dismiss()
                        binding.verifResult.text = "success Verif ${it.data.toString()}"
                    }
                    is State.ErrorState -> {
                        customDialog.dismiss()
                        binding.verifResult.text = "error ${it.exception.message}"
                    }
                    is State.LoadingState -> customDialog.showLoading(this)
                }
            })

    }

    private fun submitRegist(encImage: String) {
        var cifId: Int
        var cifCode: String
        var cifName: String
        var coCode: String
        binding.apply {
            cifId = inputCifId.text.toString().toInt()
            cifCode = inputCifCode.text.toString()
            cifName = inputCifName.text.toString()
            coCode = inputCoCode.text.toString()
        }
        viewModel.submitRegistBiometric(cifId, cifCode, cifName, coCode, encImage)
            .observe(this, Observer {
                when (it) {
                    is State.Empty -> {
                        customDialog.dismiss()
                        binding.verifRegist.text = "empty"
                    }
                    is State.DataState -> {
                        customDialog.dismiss()
                        binding.verifRegist.text =
                            "success Register ${it.data.personId} for CifId: ${it.data.cifId}"
                    }
                    is State.ErrorState -> {
                        customDialog.dismiss()
                        binding.verifRegist.text = "error ${it.exception.message}"
                    }
                    is State.LoadingState -> customDialog.showLoading(this)
                }
            })
    }
}