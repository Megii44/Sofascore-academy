package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.CityInfoOneLabelViewBinding


class CityInfoOneLabelView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: CityInfoOneLabelViewBinding

    private val label: String
    private val temperature: String
    private val icon: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.city_info_one_label_view, this, true)
        binding = CityInfoOneLabelViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.CityInfoOneLabelView, 0, 0
        ).apply {
            try {
                label = getString(R.styleable.CityInfoOneLabelView_city_info_one_label_label)!!
                temperature = getString(R.styleable.CityInfoOneLabelView_city_info_one_label_temperature)!!
                icon = getString(R.styleable.CityInfoOneLabelView_city_info_one_label_icon)!!
            } finally {
                recycle()
            }
        }
    }
}
