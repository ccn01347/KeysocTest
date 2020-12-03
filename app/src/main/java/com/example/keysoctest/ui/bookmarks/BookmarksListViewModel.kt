package com.example.keysoctest.ui.bookmarks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.keysoctest.`class`.BookmarkManager
import com.example.keysoctest.`class`.KSAlbums
import com.example.keysoctest.`class`.KSAlumsBookmarkViewModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class BookmarksListViewModel(owner: LifecycleOwner) {
    var adapter: MutableLiveData<List<KSAlumsBookmarkViewModel>> = MutableLiveData()

    init {
        BookmarkManager.shared.tempBookmarkAlums.observe(owner, androidx.lifecycle.Observer {
            var list = ArrayList<KSAlumsBookmarkViewModel>()
            for (album in it){
                list.add(KSAlumsBookmarkViewModel(album, true))
            }

            adapter.postValue(list)
        })
    }

    private var currencyFomatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))


}