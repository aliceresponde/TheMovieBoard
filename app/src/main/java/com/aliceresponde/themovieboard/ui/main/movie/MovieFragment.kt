package com.aliceresponde.themovieboard.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.MovieApp
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.FragmentMoviesBinding
import com.aliceresponde.themovieboard.ui.main.ShowItemsAdapter
import com.aliceresponde.themovieboard.ui.model.ShowItem
import com.mancj.materialsearchbar.MaterialSearchBar

class MovieFragment : Fragment(), MaterialSearchBar.OnSearchActionListener,
    ShowItemsAdapter.Listener {

    // FIXME: 10/06/20 use Inject
    lateinit var factory: MoviesViewModelFactory
    lateinit var viewModel: MoviesViewModel
    lateinit var binding: FragmentMoviesBinding

    val adapter: ShowItemsAdapter by lazy {
        ShowItemsAdapter(listOf(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.lifecycleOwner = this

        val app = (activity?.application) as MovieApp
        factory = MoviesViewModelFactory(app.provideMoviesRepository())
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        binding.viewModel = viewModel

        binding.recycler.adapter = adapter
        binding.searchBar.setOnSearchActionListener(this)
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.type_popular -> viewModel.fetchPopularMovies()
                else -> viewModel.fetchRatedMovies()
            }
        }

        factory

        viewModel.fetchPopularMovies()
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { adapter.updateData(it) })
        viewModel.ratedMovies.observe(viewLifecycleOwner, Observer { adapter.updateData(it) })

        return binding.root
    }


    override fun onButtonClicked(buttonCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        val search = viewModel.findMoviesByName(text.toString())
        search.value?.let {
            adapter.updateData(it)
        }
    }

    override fun onItemClickListener(item: ShowItem) {
        TODO("Not yet implemented")
    }

}