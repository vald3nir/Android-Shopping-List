package com.vald3nir.toolkit.androidX.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

fun EditText.actionDoneListener(actionDoneListener: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) actionDoneListener.invoke()
        false
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun TextInputEditText?.toInt(): Int? {
    return if (this?.text.isNullOrBlank()) null
    else this?.text.toString().toInt()
}

fun TextInputEditText?.toFloat(): Float? {
    return if (this?.text.isNullOrBlank()) null
    else this?.text.toString().toFloat()
}

fun TextInputEditText?.toIntOrZero(): Int {
    return toInt() ?: 0
}

fun TextInputEditText?.toFloatOrZero(): Float {
    return toFloat() ?: 0.0f
}