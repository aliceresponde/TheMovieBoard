package com.aliceresponde.themovieboard.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.aliceresponde.themovieboard.BuildConfig
import com.aliceresponde.themovieboard.R
import com.aliceresponde.themovieboard.ui.model.ShowItem
import kotlinx.android.synthetic.main.entretaiment_item.view.*

class ShowItemsAdapter(var list: List<ShowItem>, val listener: Listener) :
    RecyclerView.Adapter<ShowItemsAdapter.MovieSerieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSerieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.entretaiment_item, parent, false)
        return MovieSerieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieSerieHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateData(newItemList: List<ShowItem>) {
        val oldList = list
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            ShowItemDiffCallback(oldList, newItemList)
        )
        list = newItemList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieSerieHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(item: ShowItem) {
            itemView.apply {
                image.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_movie)
                }
                title.text = item.name
                releaseDate.text = item.date
                cardContainer.setOnClickListener { listener.onItemClickListener(item) }
            }
        }
    }

    class ShowItemDiffCallback(
        var oldItemList: List<ShowItem>,
        var newItemList: List<ShowItem>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemList[oldItemPosition].id == newItemList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldItemList.size

        override fun getNewListSize(): Int = newItemList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemList[oldItemPosition] == newItemList[newItemPosition]
        }
    }

    interface Listener {
        fun onItemClickListener(item: ShowItem)
    }
}