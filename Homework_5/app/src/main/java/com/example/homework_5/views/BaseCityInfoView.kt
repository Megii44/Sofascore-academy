package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.BaseCityInfoViewBinding


class BaseCityInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: BaseCityInfoViewBinding

    private val info: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.base_city_info_view, this, true)
        binding = BaseCityInfoViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.BaseCityInfoView, 0, 0
        ).apply {
            try {
                info = getString(R.styleable.BaseCityInfoView_info)!!
            } finally {
                recycle()
            }
        }
    }
}
