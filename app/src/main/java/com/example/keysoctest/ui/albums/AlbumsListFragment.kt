package com.example.keysoctest.ui.albums

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.keysoctest.R
import com.example.keysoctest.`class`.APIManager
import com.example.keysoctest.`class`.ApiService
import com.example.keysoctest.`class`.KSAlbums
import com.example.keysoctest.`class`.KSAlbumsResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class AlbumsListFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumsListViewModel
    private lateinit var adapter: AlbumsRecylerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View
        var root = inflater.inflate(R.layout.fragment_albums, container, false)
        recyclerView = root.findViewById(R.id.id_albums_recylerview)


        // 2. ViewModel Binding
        viewModel = ViewModelProviders.of(this).get(AlbumsListViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer {
            Log.d("STEVE-DEBUG", it.size.toString())
            adapter.setAlbums(it)
            recyclerView.adapter = adapter
        })

        // 2.a. Adapter
        adapter = AlbumsRecylerAdapter(null) { bookmark, albumViewModel, viewHolder ->

            viewModel.bookmark(albumViewModel.album, bookmark)
            adapter.updateAlbum(albumViewModel)
        }
        recyclerView.adapter

        viewModel.fetchData()

        return root;
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()

    }
}