package com.aliceresponde.themovieboard.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.aliceresponde.themovieboard.MovieApp
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.ActivityDetailBinding
import com.aliceresponde.themovieboard.ui.model.ShowItem

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var item: ShowItem
    lateinit var itemType: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val movieApp = application as MovieApp
        intent?.let {
            item = it.getParcelableExtra("showItem")
            itemType = it.getStringExtra("itemType")
        }

        with(binding) {
            image.load(item.imageUrl)
            title.text = item.name
            releaseDate.text = item.date
            overView.text = item.overview
        }
    }
}