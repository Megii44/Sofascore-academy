package com.example.homework_5.views

import android.content.Context
import android.graphics.drawable.Drawable
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
    private val icon: Drawable

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
                temperature = getInt(R.styleable.BaseCityInfoView_base_city_info_temperature, 0)
                icon = getDrawable(R.styleable.BaseCityInfoView_base_city_info_icon)!!

                setDate(date)
                setTime(time)
                setDescription(description)
                setTemperature(temperature)
                setIcon(icon)
            } finally {
                recycle()
            }
        }
    }

    private fun setDate(date: String) {
        binding.date.text = date
    }

    private fun setTime(time: String) {
        binding.time.text = time
    }

    private fun setDescription(description: String) {
        binding.description.text = description
    }

    private fun setTemperature(temperature: Int) {
        binding.temperature.text = temperature.toString()
    }

    private fun setIcon(icon: Drawable) {
        binding.weatherIcon.setImageDrawable(icon)
    }
}
