package com.aliceresponde.themovieboard.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.FragmentMoviesBinding
import com.aliceresponde.themovieboard.ui.main.ShowItemsAdapter
import com.aliceresponde.themovieboard.ui.model.ShowItem
import com.mancj.materialsearchbar.MaterialSearchBar
import javax.inject.Inject

class MovieFragment : Fragment(), MaterialSearchBar.OnSearchActionListener,
    ShowItemsAdapter.Listener {

    @Inject
    lateinit var factory: MoviesViewModelFactory
    lateinit var binding: FragmentMoviesBinding

    val adapter: ShowItemsAdapter by lazy {
        ShowItemsAdapter(listOf(), this)
    }
    val viewModel: MoviesViewModel by lazy {
        ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recycler.adapter = adapter
        binding.searchBar.setOnSearchActionListener(this)
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.type_popular -> viewModel.getPopularMovies()
                else -> viewModel.getRatedMovies()
            }
        }

        viewModel.movies.observe(viewLifecycleOwner, Observer { adapter.updateData(it) })

        return binding.root
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

    override fun onItemClickListener(item: ShowItem) {
        TODO("Not yet implemented")
    }

}