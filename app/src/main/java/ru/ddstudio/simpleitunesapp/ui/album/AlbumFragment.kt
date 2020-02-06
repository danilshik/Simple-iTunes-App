package ru.ddstudio.simpleitunesapp.ui.album

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fr_album.*
import kotlinx.android.synthetic.main.fr_album.view.*
import kotlinx.android.synthetic.main.fr_album.view.tv_artist_name
import kotlinx.android.synthetic.main.fr_album.view.tv_count_tracks
import ru.ddstudio.simpleitunesapp.AppDelegate
import ru.ddstudio.simpleitunesapp.MainViewModelFactory
import ru.ddstudio.simpleitunesapp.R
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.extensions.format
import ru.ddstudio.simpleitunesapp.utils.ConnectUtils
import javax.inject.Inject

class AlbumFragment : Fragment(){
    private lateinit var album : Album
    private lateinit var songAdapter: SongAdapter

    private lateinit var viewModel: AlbumViewModel
    @Inject
    lateinit var viewModelFactory : MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fr_album, container, false)

        initViews(view)
        initViewModel(view)
        return view
    }

    private fun initViews(view: View){
        album = arguments?.getSerializable("album") as Album
        Log.d("AlbumFragment", album.toString())

        val toolbar : Toolbar = activity!!.toolbar
        toolbar.title = album.collectionName


        view.tv_artist_name.text = album.artistName.toString()
        view.tv_country.text = album.country.toString()
        view.tv_release_date.text = "${album.releaseDate.format()}"
        view.tv_collection_price.text = if(album.collectionPrice != null) "${album.collectionPrice} ${album.currency}" else "-"
        view.tv_count_tracks.text = album.trackCount.toString()

        Glide.with(view.iv_album)
            .load(album.artworkUrl100)
            .into(view.iv_album)

        songAdapter = SongAdapter{}
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        with(view.rv_songs){
            adapter = songAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(divider)
        }
    }

    private fun initViewModel(view: View) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)
        viewModel.setAlbum(album)
        viewModel.getSongData().observe(viewLifecycleOwner, Observer { result ->

            Log.d("AlbumFragment", result.toString())
            when(result.status){
                Result.Status.SUCCESS -> {
                    showProgressBar(false)
                    if(result.data.isNullOrEmpty()) {
                        showRecyclerView(false)
                        showTextView(true)
                    } else {
                        showTextView(false)
                        showRecyclerView(true)
                        songAdapter.updateData(result.data)
                    }

                }
                Result.Status.ERROR -> {
                    showProgressBar(false)
                    showRecyclerView(false)
                    if(ConnectUtils.isConnected(context!!))
                        Snackbar.make(
                            view,
                            "${result.error.toString()}\n",
                            Snackbar.LENGTH_LONG
                        ).show()
                    else
                        Snackbar.make(
                            view,
                            getString(R.string.internet_not_available),
                            Snackbar.LENGTH_LONG
                        ).show()
                }
                Result.Status.LOADING ->{
                    showTextView(false)
                    showRecyclerView(false)
                    showProgressBar(true)

                }
            }
        })
    }

    private fun showProgressBar(isShow : Boolean = true){
        progress_bar.visibility = if(isShow) View.VISIBLE else View.INVISIBLE
    }

    private fun showTextView(isShow : Boolean = true, text : String = getString(R.string.result_empty)){
        if(isShow){
            tv_empty.visibility = View.VISIBLE
            tv_empty.text = text
        }
        else{
            tv_empty.visibility = View.INVISIBLE
        }
    }

    private fun showRecyclerView(isShow: Boolean = true){
        rv_songs.visibility = if(isShow) View.VISIBLE else View.INVISIBLE
    }

}