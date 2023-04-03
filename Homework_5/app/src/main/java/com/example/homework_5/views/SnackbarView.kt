package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.SnackbarViewBinding


class SnackbarView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: SnackbarViewBinding

    private val text: String
    private val backgroundColor: Int

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.snackbar_view, this, true)
        binding = SnackbarViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.SnackbarView, 0, 0
        ).apply {
            try {
                text = getString(R.styleable.SnackbarView_backgroundColor)!!
                backgroundColor = getString(R.styleable.SnackbarView_backgroundColor)!!.toInt()
            } finally {
                recycle()
            }
        }
    }
}
