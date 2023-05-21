package com.example.homework_5.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.WeatherInfoTileViewBinding

class WeatherInfoTileView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: WeatherInfoTileViewBinding

    private val title: String
    private val value: String
    private val icon: Drawable

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.weather_info_tile_view, this, true)
        binding = WeatherInfoTileViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.WeatherInfoTileView, 0, 0
        ).apply {
            try {
                title = getString(R.styleable.WeatherInfoTileView_weather_info_tile_title)!!
                value = getString(R.styleable.WeatherInfoTileView_weather_info_tile_value)!!
                icon = getDrawable(R.styleable.WeatherInfoTileView_weather_info_tile_icon)!!

                setTitle(title)
                setValue(value)
                setIcon(icon)
            } finally {
                recycle()
            }
        }
    }

    private fun setTitle(date: String) {
        binding.title.text = date.uppercase()
    }

    public fun setValue(time: String) {
        binding.value.text = time
    }

    private fun setIcon(icon: Drawable) {
        binding.icon.setImageDrawable(icon)
    }
}
