package com.vald3nir.toolkit.androidX.components.base

import android.app.AlertDialog
import android.content.Context

class CustomYesNoDialog(context: Context) : AlertDialog.Builder(context) {

    private var alertDialog: AlertDialog? = null
    fun showDialog(
        title: String? = null,
        message: String? = null,
        icon: Int? = null,
        textConfirmButton: String? = null,
        textCancelButton: String? = null,
        onClickConfirm: () -> Unit,
        onClickCancel: (() -> Unit)? = null,
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        icon?.let { builder.setIcon(it) }
        textConfirmButton?.let {
            builder.setPositiveButton(it) { _, _ ->
                alertDialog?.dismiss()
                onClickConfirm.invoke()
            }
        }
        textCancelButton?.let {
            builder.setNegativeButton(it) { _, _ ->
                alertDialog?.dismiss()
                onClickCancel?.invoke()
            }
        }
        alertDialog?.dismiss()
        alertDialog = builder.create().apply {
            setCancelable(false)
            show()
        }
    }
}