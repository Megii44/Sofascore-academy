package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.BaseCityInfoViewBinding


class BaseCityInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: BaseCityInfoViewBinding

    private val date: String
    private val time: String
    private val description: String
    private val temperature: Int
    private val icon: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.base_city_info_view, this, true)
        binding = BaseCityInfoViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.BaseCityInfoView, 0, 0
        ).apply {
            try {
                date = getString(R.styleable.BaseCityInfoView_base_city_info_date)!!
                time = getString(R.styleable.BaseCityInfoView_base_city_info_time)!!
                description = getString(R.styleable.BaseCityInfoView_base_city_info_description)!!
                temperature = R.styleable.BaseCityInfoView_base_city_info_temperature
                icon = getString(R.styleable.BaseCityInfoView_base_city_info_icon)!!
            } finally {
                recycle()
            }
        }
    }
}
