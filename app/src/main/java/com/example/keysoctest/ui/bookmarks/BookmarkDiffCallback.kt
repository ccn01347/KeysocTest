package com.example.keysoctest.ui.bookmarks

import androidx.recyclerview.widget.DiffUtil
import com.example.keysoctest.`class`.KSAlbums
import com.example.keysoctest.`class`.KSAlumsBookmarkViewModel

class BookmarkDiffCallback(
    private val currentAlbums:List<KSAlumsBookmarkViewModel>,
    private val newAlbums:List<KSAlumsBookmarkViewModel>)
    : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentAlbums.get(oldItemPosition).album.collectionId == newAlbums.get(newItemPosition).album.collectionId
    }

    override fun getOldListSize(): Int {
        return currentAlbums.size
    }

    override fun getNewListSize(): Int {
        return newAlbums.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentAlbums.get(oldItemPosition).equals(newAlbums.get(newItemPosition))
    }
}