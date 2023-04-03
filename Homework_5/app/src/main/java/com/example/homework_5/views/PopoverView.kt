package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.CityInfoOneLabelViewBinding
import com.example.homework_5.databinding.PopoverViewBinding


class PopoverView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: PopoverViewBinding

    private val title: String
    private val description: String
    private val buttonOneText: String
    private val buttonTwoText: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.city_info_one_label_view, this, true)
        binding = PopoverViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.PopoverView, 0, 0
        ).apply {
            try {
                title = getString(R.styleable.PopoverView_popover_title)!!
                description = getString(R.styleable.PopoverView_popover_description)!!
                buttonOneText = getString(R.styleable.PopoverView_popover_button_one_text)!!
                buttonTwoText = getString(R.styleable.PopoverView_popover_button_two_text)!!
            } finally {
                recycle()
            }
        }
    }
}
