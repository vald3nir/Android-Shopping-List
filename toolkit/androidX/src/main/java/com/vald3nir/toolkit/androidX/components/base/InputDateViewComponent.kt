package com.vald3nir.toolkit.androidX.components.base

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.vald3nir.toolkit.helpers.extensions.formatDecimal
import com.vald3nir.toolkit.androidx.R
import com.vald3nir.toolkit.androidx.databinding.CustomInputDateBinding
import java.util.Calendar


class InputDateViewComponent(context: Context, attr: AttributeSet?) : LinearLayoutCompat(context, attr) {

    private val binding by lazy {
        CustomInputDateBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        orientation = VERTICAL
    }

    fun value() = binding.edtField.text.toString()

    @SuppressLint("SetTextI18n")
    fun setup(
        textValue: String? = null,
        hintText: String? = null,
        textColor: Int = R.color.black,
        useEditMode: Boolean = true,
    ) = with(binding) {
        inputCover.hint = hintText
        edtField.apply {
            setText(textValue)
            setTextColor(ContextCompat.getColor(context, textColor))
            isEnabled = useEditMode
            setOnClickListener {
                showDatePickerDialog { _, year, monthOfYear, dayOfMonth ->
                    setText("${dayOfMonth.formatDecimal()}/${(monthOfYear + 1).formatDecimal()}/${year}")
                }
            }
        }
    }

    private fun showDatePickerDialog(listener: DatePickerDialog.OnDateSetListener) {
        val cldr: Calendar = Calendar.getInstance()
        val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month: Int = cldr.get(Calendar.MONTH)
        val year: Int = cldr.get(Calendar.YEAR)
        val picker = DatePickerDialog(context, listener, year, month, day)
        picker.show()
    }
}