package com.example.homework_5.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColor
import com.example.homework_5.R
import com.example.homework_5.databinding.ListedInfoViewBinding


class ButtonView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ListedInfoViewBinding

    private val text: String
    private val backgroundColor: Color
    private val borderColor: Color
    private val disabled: Boolean

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.button_view, this, true)
        binding = ListedInfoViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ButtonView, 0, 0
        ).apply {
            try {
                text = R.styleable.ButtonView_text.toString()
                backgroundColor = R.styleable.ButtonView_background_color.toColor()
                borderColor = R.styleable.ButtonView_border_color.toColor()
                disabled = false
            } finally {
                recycle()
            }
        }
    }
}
