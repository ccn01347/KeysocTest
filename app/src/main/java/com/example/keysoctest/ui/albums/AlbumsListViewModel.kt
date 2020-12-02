package com.example.keysoctest.ui.albums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keysoctest.`class`.*
import retrofit2.Call
import retrofit2.Response

class AlbumsListViewModel: ViewModel() {
    var albums: MutableLiveData<List<KSAlumsBookmarkViewModel>> = MutableLiveData(ArrayList<KSAlumsBookmarkViewModel>())
        private set



    fun fetchData(){
        val apiService = APIManager.client.create(ApiService::class.java)

        apiService.itunesSearch().enqueue(object: retrofit2.Callback<KSAlbumsResponse> {
            override fun onResponse(
                call: Call<KSAlbumsResponse>,
                response: Response<KSAlbumsResponse>
            ) {
                val viewModels = ArrayList<KSAlumsBookmarkViewModel>()
                for (album in response.body()!!.results){
                    var data = KSAlumsBookmarkViewModel(album)
                    data.bookmarked = BookmarkManager.shared.containInBookmarkList(album)
                    viewModels.add(data)
                }

                // 2. Check bookmarked
                albums.postValue(viewModels)
            }

            override fun onFailure(call: Call<KSAlbumsResponse>, t: Throwable) {
                // Failed
                Log.d("STEVE-DEBUG", call.request().url().toString())
            }
        })
    }

    fun bookmark(album: KSAlbums, bookmark: Boolean){
        if (bookmark){
            BookmarkManager.shared.bookmarkAlbums(album)
        }else{
            BookmarkManager.shared.removeBookmark(album)
        }
//        var list = albums.value
//        list?.first { it.album.collectionId == album.collectionId }?.bookmarked = bookmark
//        albums.postValue(list)
    }


}