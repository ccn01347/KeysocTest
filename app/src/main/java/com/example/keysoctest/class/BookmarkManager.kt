package com.example.keysoctest.`class`

import androidx.lifecycle.MutableLiveData

class BookmarkManager {
    var tempBookmarkAlums = MutableLiveData<ArrayList<KSAlbums>>(ArrayList<KSAlbums>())
        private set

    companion object {
        val shared = BookmarkManager()
    }

    fun bookmarkAlbums(album: KSAlbums){
        var current = tempBookmarkAlums.value

        // 1. check to avoid duplicate
        if (current!!.indexOfFirst { it.collectionId == album.collectionId } >= 0){
            return
        }
        // 2. Add into array and post value
        // 2.a. These part should write data into database for production purpose.
        current!!.add(album)
        tempBookmarkAlums.postValue(current)
    }

    fun removeBookmark(album: KSAlbums){
        var current = tempBookmarkAlums.value

        // 1. check to avoid duplicate
        if (current!!.indexOfFirst { it.collectionId == album.collectionId } < 0){
            return
        }
        current!!.remove(album)
        tempBookmarkAlums.postValue(current)
    }

    fun containInBookmarkList(album: KSAlbums) : Boolean{
        val index = tempBookmarkAlums.value!!.indexOfFirst { it.collectionId == album.collectionId }
        return index >= 0
    }
}