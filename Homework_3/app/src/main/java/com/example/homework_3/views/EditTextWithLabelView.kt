package com.example.homework_3.views

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_3.R
import com.example.homework_3.databinding.EditTextWithLabelViewBinding

class EditTextWithLabelView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: EditTextWithLabelViewBinding

    private val label: String
    private val error: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.edit_text_with_label_view, this, true)
        binding = EditTextWithLabelViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextWithLabelView, 0, 0
        ).apply {
            try {
                label = getString(R.styleable.EditTextWithLabelView_label)!!
                error = getString(R.styleable.EditTextWithLabelView_errorMsg)!!

                setLabelText(label)
            } finally {
                recycle()
            }
        }
    }

    private fun setLabelText(labelText: String) {
        binding.customTextView.text = labelText
    }

    private fun setErrorText(errorText: String) {
        binding.customEditText.error = errorText
    }

    fun getEditTextValue(): String {
        return binding.customEditText.text.toString()
    }

    fun setEditTextValue(str: String) {
        val editableStr = SpannableStringBuilder(str)
        binding.customEditText.text = editableStr
    }

    fun validate(): Boolean {
        return if(getEditTextValue().isEmpty()) {
            setErrorText(this.error)
            false
        } else {
            true
        }
    }
}
