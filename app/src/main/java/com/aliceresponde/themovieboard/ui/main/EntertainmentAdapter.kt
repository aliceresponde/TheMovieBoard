package com.aliceresponde.themovieboard.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.aliceresponde.themovieboard.BuildConfig
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.ui.ShowItem
import kotlinx.android.synthetic.main.entretaiment_item.view.*

class EntertainmentAdapter(var list: MutableList<ShowItem>, val listener: Listener) :
    RecyclerView.Adapter<EntertainmentAdapter.MovieSerieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSerieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.entretaiment_item, parent, false)
        return MovieSerieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieSerieHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateData(mList: List<ShowItem>) {
        list.clear()
        list.addAll(mList)
        notifyDataSetChanged()
    }

    inner class MovieSerieHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(item: ShowItem) {
            itemView.apply {
                image.load(BuildConfig.IMG_URL + item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_movie)
                }
                title.text = item.name
                releaseDate.text = item.date
                cardContainer.setOnClickListener { listener.onItemClickListener(item) }
            }
        }
    }

    interface Listener {
        fun onItemClickListener(item: ShowItem)
    }
}