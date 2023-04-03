package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.TimeItemViewBinding


class TimeItemView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: TimeItemViewBinding

    private val hour: String
    private val icon: String
    private val temperature: String
    private val backgroundColor: Int

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.time_item_view, this, true)
        binding = TimeItemViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.TimeItemView, 0, 0
        ).apply {
            try {
                hour = getString(R.styleable.TimeItemView_time_item_hour)!!
                icon = getString(R.styleable.TimeItemView_time_item_icon)!!
                temperature = getString(R.styleable.TimeItemView_time_item_temperature)!!
                backgroundColor = getString(R.styleable.TimeItemView_time_item_background_color)!!.toInt()
            } finally {
                recycle()
            }
        }
    }
}
