package com.example.hiltcoroutinesflow.utils.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.example.hiltcoroutinesflow.R
import com.example.hiltcoroutinesflow.databinding.DlgAlertBinding
import com.example.hiltcoroutinesflow.databinding.DlgLoadingBinding
import com.example.hiltcoroutinesflow.databinding.DlgSuccessBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomDialog @Inject constructor() {

    private var dlg: Dialog? = null

    private fun isShowing(): Boolean = dlg?.isShowing ?: false

    fun dismiss() {
        dlg?.dismiss()
        dlg = null
    }

    private fun showDialog(
        context: Context,
        view: View,
        dismissListener: DialogInterface.OnDismissListener = DialogInterface.OnDismissListener { }
    ) {
        if (isShowing()) return
        dlg = Dialog(context, R.style.AlertDialogTheme)
        dlg?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setOnDismissListener(dismissListener)
            setContentView(view)
            show()
        }
    }

    fun showOneButtonDialog(
        context: Context,
        title: String,
        desc: String,
        dismissListener: DialogInterface.OnDismissListener
    ) {
        val binding: DlgSuccessBinding = DlgSuccessBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            dlgTitle.text = title
            dlgDesc.text = desc
            dlgBtn.setOnClickListener { dismiss() }
        }
        showDialog(context, binding.root, dismissListener)
    }

    fun showAlertDialog(
        context: Context,
        title: String,
        desc: String,
        showBtnPositive: Boolean = true,
        btnNegativeTitle: String = "Kembali",
        btnNegativeListener: () -> Unit = {},
        btnPositiveTitle: String = "OK",
        btnPositiveListener: () -> Unit = {}
    ) {

        val binding: DlgAlertBinding = DlgAlertBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            dlgTitle.text = title
            dlgDesc.text = desc
            dlgBtnNegative.apply {
                text = btnNegativeTitle
                setOnClickListener {
                    dismiss()
                    btnNegativeListener()
                }
            }
            dlgBtnPositive.apply {
                if (!showBtnPositive) visibility = View.GONE
                text = btnPositiveTitle
                setOnClickListener {
                    dismiss()
                    btnPositiveListener()
                }
            }
        }
        showDialog(context, binding.root)
    }

    fun showLoading(context: Context) {
        val binding: DlgLoadingBinding =
            DlgLoadingBinding.inflate(LayoutInflater.from(context))
        showDialog(context, binding.root)
    }
}