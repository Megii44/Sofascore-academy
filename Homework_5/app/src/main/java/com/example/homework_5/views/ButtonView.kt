package com.example.homework_5.views

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.ButtonViewBinding


@RequiresApi(Build.VERSION_CODES.P)
class ButtonView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ButtonViewBinding

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
                text = R.styleable.ButtonView_text.toString()
                backgroundColor = R.styleable.ButtonView_background_color
                borderColor = R.styleable.ButtonView_border_color
                disabled = false

                setText(text)
                setBackgroundColor(backgroundColor)
                setBorderColor(borderColor)
                setDisabled(disabled)
            } finally {
                recycle()
            }
        }
    }

    private fun setText(text: String) {
        binding.button.text = text
    }

    override fun setBackgroundColor(color: Int) {
        binding.button.backgroundTintList = ColorStateList.valueOf(color)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun setBorderColor(color: Int) {
        binding.button.outlineAmbientShadowColor = color
    }

    private fun setDisabled(disabled: Boolean) {
        binding.button.isActivated = disabled
    }
}
