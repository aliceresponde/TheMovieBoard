package com.aliceresponde.themovieboard.ui.main.serie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aliceresponde.themovieboard.MovieApp
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.FragmentSerieBinding
import com.aliceresponde.themovieboard.hideKeyboard
import com.aliceresponde.themovieboard.ui.main.ShowItemsAdapter
import com.aliceresponde.themovieboard.ui.model.ShowItem
import com.google.android.material.snackbar.Snackbar
import com.mancj.materialsearchbar.MaterialSearchBar

class SeriesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener,
    ShowItemsAdapter.Listener {
    private lateinit var binding: FragmentSerieBinding
    private lateinit var factory: SeriesViewModelFactory
    private lateinit var viewModel: SeriesViewModel

    private val adapter: ShowItemsAdapter by lazy { ShowItemsAdapter(listOf(), this) }
    private val ITEM_TYPE = "serie"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val app = (activity?.application) as MovieApp
        initViewModel(app)
        initObservers()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_serie, container, false)

        binding.apply {
            lifecycleOwner = this@SeriesFragment
            vm = viewModel
            recycler.adapter = adapter
            searchBar.setOnSearchActionListener(this@SeriesFragment)

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.type_popular -> viewModel.getPopularSeries()
                    R.id.tape_rated -> viewModel.getRatedSeries()
                    else -> {}
                }
            }
        }

        viewModel.getPopularSeries()

        return binding.root
    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { adapter.updateData(it) })
        viewModel.isInternetOn.observe(viewLifecycleOwner, Observer {
            if (!it) {
                view?.let {
                    Snackbar.make(it, R.string.no_internet_message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }


    private fun initViewModel(app: MovieApp) {
        factory = SeriesViewModelFactory(app.provideSeriesRepository())
        viewModel = ViewModelProvider(this, factory).get(SeriesViewModel::class.java)
    }

    override fun onButtonClicked(buttonCode: Int) {
    }

    override fun onSearchStateChanged(enabled: Boolean) {
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        binding.radioGroup.clearCheck()
        hideKeyboard()
        viewModel.fetchSeriesByName(text.toString())
        binding.searchBar.text = ""
    }

    override fun onItemClickListener(item: ShowItem) {
        view?.let {
            val action = SeriesFragmentDirections.actionPopularSeriesFragmentToDetailActivity(
                item,
                ITEM_TYPE
            )
            findNavController().navigate(action)
        }
    }
}