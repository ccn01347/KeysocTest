package com.example.keysoctest.`class`

import com.example.keysoctest.R
import java.text.NumberFormat
import java.util.*

class KSAlumsBookmarkViewModel constructor(val data: KSAlbums, val bookmark: Boolean = false) {
    lateinit var album: KSAlbums
    var bookmarked = bookmark
    private var currencyFomatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))

    init {
        album = data
    }

    fun title(): String{
        return album.collectionName
    }

    fun caption(): String{
        return album.artistName
    }

    fun artwork(): String{
        return album.artworkUrl100
    }

    fun price(): String{
        return currencyFomatter.format(album.collectionPrice)
    }

    fun bookmarkResource(bookmark: Boolean = bookmarked): Int{
        if (bookmark){
            return R.drawable.ic_baseline_bookmark_24
        }else{
            return R.drawable.ic_baseline_bookmark_border_24
        }
    }

}