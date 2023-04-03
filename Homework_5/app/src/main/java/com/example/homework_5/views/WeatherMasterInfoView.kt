package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.WeatherMasterInfoViewBinding


class WeatherMasterInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: WeatherMasterInfoViewBinding

    private val title: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.weather_master_info_view, this, true)
        binding = WeatherMasterInfoViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.WeatherMasterInfoView, 0, 0
        ).apply {
            try {
                title = getString(R.styleable.WeatherMasterInfoView_weather_master_info_title)!!
            } finally {
                recycle()
            }
        }
    }
}
