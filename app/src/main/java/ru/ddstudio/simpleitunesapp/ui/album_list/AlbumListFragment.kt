package ru.ddstudio.simpleitunesapp.ui.album_list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fr_list_album.*
import kotlinx.android.synthetic.main.fr_list_album.view.*
import ru.ddstudio.simpleitunesapp.AppDelegate
import ru.ddstudio.simpleitunesapp.MainViewModelFactory
import ru.ddstudio.simpleitunesapp.R
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Album
import javax.inject.Inject

class AlbumListFragment : Fragment(){
    private lateinit var viewModel: AlbumListViewModel
    @Inject
    lateinit var viewModelFactory : MainViewModelFactory
    private lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fr_list_album, container, false)
        initViews(view)
        initViewModel()
        return view
    }

    private fun initViews(view: View){
        albumListAdapter = AlbumListAdapter{transmit(it)}
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        with(view.rv_list_albums){
            adapter = albumListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(divider)
        }


    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(AlbumListViewModel::class.java)
        viewModel.getAlbums().observe(viewLifecycleOwner, Observer { result ->

            Log.d("AlbumsFragment", result.toString())
            when(result.status){
                Result.Status.SUCCESS -> {
                    showProgressBar(false)
                    if(result.data.isNullOrEmpty()) {
                        showRecyclerView(false)
                        showTextView(true)
                    } else {
                        showTextView(false)
                        showRecyclerView(true)
                        albumListAdapter.updateData(result.data)
                    }

                }
                Result.Status.ERROR -> {
                    showProgressBar(false)
                    showRecyclerView(false)
                    showTextView(true, result.error.toString())
                }
                Result.Status.LOADING ->{
                    showTextView(false)
                    showRecyclerView(false)
                    showProgressBar(true)

                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Введите название альбома"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.handleSearchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleSearchQuery(newText)
                return true
            }
        })
//
//        return super.onCreateOptionsMenu(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showProgressBar(isShow : Boolean = true){
        progress_bar.visibility = if(isShow) View.VISIBLE else View.INVISIBLE
    }

    private fun showTextView(isShow : Boolean = true, text : String = "Пусто"){
        if(isShow){
            tv_empty.visibility = View.VISIBLE
            tv_empty.text = text
        }
        else{
            tv_empty.visibility = View.INVISIBLE
        }
    }

    private fun showRecyclerView(isShow: Boolean = true){
        rv_list_albums.visibility = if(isShow) View.VISIBLE else View.INVISIBLE
    }

    private fun transmit(item: Album){
        Toast.makeText(context, "Click ${item.collectionName}", Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun getInstance() : AlbumListFragment{
            return AlbumListFragment()
        }
    }


}