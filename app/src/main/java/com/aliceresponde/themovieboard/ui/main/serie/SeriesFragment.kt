package com.aliceresponde.themovieboard.ui.main.serie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.FragmentPopularSerieBinding
import com.aliceresponde.themovieboard.ui.main.serie.SeriesViewModel
import com.mancj.materialsearchbar.MaterialSearchBar

class SeriesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {
    private lateinit var binding: FragmentPopularSerieBinding
    private lateinit var viewModel: SeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_popular_serie, container, false)


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.type_popular) {

            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onButtonClicked(buttonCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        TODO("Not yet implemented")
    }
}