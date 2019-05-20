package com.setiawanpaiman.spvnts.ui.thumbnail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setiawanpaiman.spvnts.R
import com.setiawanpaiman.spvnts.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_thumbnail.view.*

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ThumbnailAdapter(private val listener: Listener) : RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    val data = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail, parent, false))
        vh.itemView.setOnClickListener {
            if (vh.adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            listener.onClick(vh.adapterPosition)
        }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(holder.itemView.context)
            .load(data[position].thumbnailUrl)
            .into(holder.itemView.imageView)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Photo>?) {
        this.data.clear()
        this.data.addAll(data ?: listOf())
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface Listener {
        fun onClick(pos: Int)
    }
}