package com.aliceresponde.themovieboard.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import coil.api.load
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.databinding.ActivityDetailBinding
import com.aliceresponde.themovieboard.ui.model.ShowItem

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    val args: DetailActivityArgs by navArgs()
    private val item: ShowItem by lazy { args.showItem }
    private val itemType: String by lazy { args.itemType }

    private val TYPE_SERIE = "serie"
    private val TYPE_MOVIE = "movie"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        with(binding) {
            image.load(item.imageUrl){
                crossfade(true)
                error(R.drawable.ic_movie)
                placeholder(R.drawable.ic_movie)
            }
            title.text = item.name
            releaseDate.text = item.date
            overView.text = item.overview
        }
    }
}