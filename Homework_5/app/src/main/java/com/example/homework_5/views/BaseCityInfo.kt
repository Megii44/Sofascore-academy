package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.BaseCityInfoBinding


class BaseCityInfo(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: BaseCityInfoBinding

    private val label: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.base_city_info, this, true)
        binding = BaseCityInfoBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.BaseCityInfo, 0, 0
        ).apply {
            try {
                label = getString(R.styleable.BaseCityInfo_exampleColor)!!

            } finally {
                recycle()
            }
        }
    }
}
