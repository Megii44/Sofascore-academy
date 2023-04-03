package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.CityInfoTwoLabelsViewBinding


class CityInfoTwoLabelsView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: CityInfoTwoLabelsViewBinding

    private val label1: String
    private val label2: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.city_info_two_labels_view, this, true)
        binding = CityInfoTwoLabelsViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.CityInfoTwoLabelsView, 0, 0
        ).apply {
            try {
                label1 = getString(R.styleable.CityInfoTwoLabelsView_label1)!!
                label2 = getString(R.styleable.CityInfoTwoLabelsView_label2)!!
            } finally {
                recycle()
            }
        }
    }
}
