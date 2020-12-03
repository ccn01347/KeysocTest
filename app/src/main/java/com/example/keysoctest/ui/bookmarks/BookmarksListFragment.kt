package com.example.keysoctest.ui.bookmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.keysoctest.R
import com.example.keysoctest.`class`.BookmarkManager


class BookmarksListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BookmarksListViewModel
    private lateinit var adapter: BookmarksRecylerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View
        var root = inflater.inflate(R.layout.fragment_bookmark, container, false)
        recyclerView = root.findViewById(R.id.id_albums_recylerview)

        // 1.b.
        adapter = BookmarksRecylerAdapter(emptyList())
        recyclerView.adapter = adapter
        // 2. Binding
        viewModel = BookmarksListViewModel(viewLifecycleOwner)
        viewModel.adapter.observe(viewLifecycleOwner, Observer {

            Log.d("STEVE-DEBUG", it.size.toString())
            Log.d("STEVE-DEBUG", (recyclerView.adapter as BookmarksRecylerAdapter).viewModels?.size.toString())

            (recyclerView.adapter as BookmarksRecylerAdapter).setData(it)
            Log.d("STEVE-DEBUG", adapter.viewModels?.size.toString())
            Log.d("STEVE-DEBUG", (recyclerView.adapter as BookmarksRecylerAdapter).viewModels?.size.toString())

        })

        return root
    }
}