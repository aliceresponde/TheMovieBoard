package com.aliceresponde.themovieboard.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.FragmentPopularMovieBinding
import com.aliceresponde.themovieboard.ui.main.movie.MoviesViewModel
import com.mancj.materialsearchbar.MaterialSearchBar

class MovieFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {
    lateinit var binding: FragmentPopularMovieBinding
    lateinit var viewModel: MoviesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_popular_movie, container, false)
        binding.searchBar.setOnSearchActionListener(this)
        return binding.root
    }

    override fun onButtonClicked(buttonCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        if (!enabled){
            //todo get moview by
        }
            // getMovies(searchMovieType = searchMovieType)
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        TODO("Not yet implemented")
    }
}