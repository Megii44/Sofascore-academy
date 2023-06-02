package com.example.minisofascore.ui.basketball

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.minisofascore.R
import java.time.LocalDate

class BasketballFragment : Fragment() {

    companion object {
        private const val ARG_DATE = "date"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(date: LocalDate): BasketballFragment {
            val fragment = BasketballFragment()
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: BasketballViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val date = arguments?.getSerializable(ARG_DATE) as LocalDate

        // use the 'date' variable in your ViewModel to get events for this date
        // You might need to add methods to your ViewModel to support this

        viewModel = ViewModelProvider(this)[BasketballViewModel::class.java]
        // TODO: Use the ViewModel

        return inflater.inflate(R.layout.fragment_basketball, container, false)
    }

    // This method is no longer required as ViewModel initialization is done in onCreateView
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    */
}
