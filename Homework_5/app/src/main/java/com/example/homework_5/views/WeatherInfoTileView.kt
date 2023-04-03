package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.WeatherInfoTileViewBinding


class WeatherInfoTileView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: WeatherInfoTileViewBinding

    private val icon: String
    private val title: String
    private val value: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.weather_info_tile_view, this, true)
        binding = WeatherInfoTileViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.WeatherInfoTileView, 0, 0
        ).apply {
            try {
                icon = getString(R.styleable.WeatherInfoTileView_icon)!!
                title = getString(R.styleable.WeatherInfoTileView_title)!!
                value = getString(R.styleable.WeatherInfoTileView_value)!!
            } finally {
                recycle()
            }
        }
    }
}
