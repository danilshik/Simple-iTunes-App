package ru.ddstudio.simpleitunesapp.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.view.*
import ru.ddstudio.simpleitunesapp.R
import ru.ddstudio.simpleitunesapp.data.database.Song
import ru.ddstudio.simpleitunesapp.extensions.toStringTime


class SongAdapter(
    private val listener : (Song) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>(){
    var items : List<Song> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_song, parent, false)
        return SongViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

        fun updateData(data: List<Song>){
        val diffCallback = object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].collectionId == data[newItemPosition].collectionId


            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].hashCode() == data[newItemPosition].hashCode()

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = data.size
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        diffResult.dispatchUpdatesTo(this)
    }


    inner class SongViewHolder(convertView: View): RecyclerView.ViewHolder(convertView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: Song, listener: (Song) -> Unit){
            if(item.artworkUrl60 != null){
                Glide.with(itemView)
                    .load(item.artworkUrl60)
                    .into(itemView.iv_song)
            }
            else{
                Glide.with(itemView)
                    .clear(itemView.iv_song)
            }

            with(itemView.tv_track_name){
                text = item.trackName
            }

            with(itemView.tv_disc_number){
                text = "Порядковый номер диска: ${item.discNumber}"
            }

            with(itemView.tv_track_price){
                text = if(item.trackPrice == null) "-" else "${item.trackPrice} ${item.currency}"
            }

            with(itemView.tv_track_number){
                text = "Порядковый номер трека: ${item.trackNumber}"
            }
            with(itemView.tv_track_time_millies){
                text = item.trackTimeMillis?.toStringTime()?: "-"
            }

            itemView.setOnClickListener{listener.invoke(item)}
        }

    }
}