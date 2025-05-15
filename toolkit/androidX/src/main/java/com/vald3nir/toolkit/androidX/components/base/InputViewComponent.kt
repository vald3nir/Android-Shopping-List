package com.vald3nir.toolkit.androidX.components.base

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.vald3nir.toolkit.androidX.extensions.afterTextChanged
import com.vald3nir.toolkit.androidx.R
import com.vald3nir.toolkit.androidx.databinding.CustomInputViewBinding

class InputViewComponent(context: Context, attr: AttributeSet?) : LinearLayoutCompat(context, attr) {

    private val binding by lazy {
        CustomInputViewBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        orientation = VERTICAL
    }

    fun value() = binding.edtField.text.toString()

    fun showErrorMessage(validationErrorMessage: String?) {
        binding.inputCover.error = validationErrorMessage
    }

    fun setText(textValue: String?) {
        binding.edtField.setText(textValue)
    }

    fun setup(
        textValue: String? = null,
        hintText: String? = null,
        useSingleLine: Boolean = true,
        textColor: Int = R.color.black,
        textInputType: Int = InputType.TYPE_CLASS_TEXT,
        useEditMode: Boolean = true,
        clickListener: (() -> Unit)? = null,
        onTextChanged: ((String) -> Unit)? = null,
    ) = with(binding) {
        inputCover.hint = hintText
        edtField.apply {
            setText(textValue)
            isSingleLine = useSingleLine
            setTextColor(ContextCompat.getColor(context, textColor))
            inputType = textInputType
            setOnClickListener { clickListener?.invoke() }
            isEnabled = useEditMode
            afterTextChanged { onTextChanged?.invoke(it) }
        }
    }
}