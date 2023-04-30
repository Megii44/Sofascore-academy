package com.example.homework_5.views

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.ButtonViewBinding

class ButtonView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: ButtonViewBinding

    private val text: String
    private val backgroundColor: Int
    private val borderColor: Int
    private val disabled: Boolean

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.button_view, this, true)
        binding = ButtonViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ButtonView, 0, 0
        ).apply {
            try {
                text = getString(R.styleable.ButtonView_text) ?: ""
                backgroundColor = getColor(R.styleable.ButtonView_background_color, 0)
                borderColor = getColor(R.styleable.ButtonView_border_color, 0)
                disabled = getBoolean(R.styleable.ButtonView_disabled, false)

                setText(text)
                setBorderDrawable(borderColor, backgroundColor)
                setDisabled(disabled)
            } finally {
                recycle()
            }
        }
    }

    private fun setText(text: String) {
        binding.customButton.text = text
    }

    private fun setBorderDrawable(borderColor: Int, backgroundColor: Int) {
        val drawable = GradientDrawable().apply {
            setColor(backgroundColor)
            setStroke(2, borderColor)
            cornerRadius = 4f
        }
        //binding.customButton.background = drawable
    }

    private fun setDisabled(disabled: Boolean) {
        //binding.customButton.isEnabled = !disabled
    }
}
