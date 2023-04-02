package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.CityItemViewBinding


class CityItemView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: CityItemViewBinding

    private val label: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item_view, this, true)
        binding = CityItemViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.CityItemView, 0, 0
        ).apply {
            try {
                label = getString(R.styleable.CityItemView_exampleColor)!!

            } finally {
                recycle()
            }
        }
    }
}
