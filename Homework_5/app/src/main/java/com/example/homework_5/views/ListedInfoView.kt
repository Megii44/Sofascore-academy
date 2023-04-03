package com.example.homework_5.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.homework_5.R
import com.example.homework_5.databinding.ListedInfoViewBinding


class ListedInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ListedInfoViewBinding

    private val title: String
    private val subtitle: String

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item_view, this, true)
        binding = ListedInfoViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ListedInfoView, 0, 0
        ).apply {
            try {
                title = getString(R.styleable.ListedInfoView_title)!!
                subtitle = getString(R.styleable.ListedInfoView_subtitle)!!
            } finally {
                recycle()
            }
        }
    }
}
