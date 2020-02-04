package ru.ddstudio.simpleitunesapp.ui.album_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_album.view.*
import ru.ddstudio.simpleitunesapp.R
import ru.ddstudio.simpleitunesapp.data.database.Album

class AlbumListAdapter(
    private val listener : (Album) -> Unit
) : RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>(){
    var items : List<Album> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_list_album, parent, false)
        return AlbumListViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun updateData(data: List<Album>){
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


    inner class AlbumListViewHolder(convertView: View): RecyclerView.ViewHolder(convertView), LayoutContainer{

        override val containerView: View?
            get() = itemView

        fun bind(item: Album, listener: (Album) -> Unit){
            if(item.artworkUrl60 != null){
                Glide.with(itemView)
                    .load(item.artworkUrl60)
                    .into(itemView.iv_album)
            }
            else{
                Glide.with(itemView)
                    .clear(itemView.iv_album)
            }

            with(itemView.tv_collection_name){
                text = item.collectionName
            }

            with(itemView.tv_artist_name){
                text = item.artistName
            }

            with(itemView.tv_price){
                text = if(item.collectionPrice == null) "-" else "${item.collectionPrice} ${item.currency}"
            }

            with(itemView.tv_count_tracks){
                text = "${item.trackCount} Tracks"
            }

            itemView.setOnClickListener{listener.invoke(item)}
        }

    }
}