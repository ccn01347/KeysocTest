package com.example.keysoctest.ui.albums

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keysoctest.R
import com.example.keysoctest.`class`.KSAlbums
import com.example.keysoctest.`class`.KSAlumsBookmarkViewModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class AlbumsRecylerAdapter(
    private var albums: MutableList<KSAlumsBookmarkViewModel>?,
    public var onBookmarkClickListener: (bookmark: Boolean, viewModel: KSAlumsBookmarkViewModel, viewHolder: RecyclerView.ViewHolder) -> Unit)
    : RecyclerView.Adapter<AlbumsRecylerAdapter.ViewHodler>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsRecylerAdapter.ViewHodler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_albums, parent, false)
        return ViewHodler(view)
    }

    override fun getItemCount(): Int = albums?.size ?: 0

    override fun onBindViewHolder(holder: AlbumsRecylerAdapter.ViewHodler, position: Int) {
        holder.bindViewModel(albums!!.get(position), onBookmarkClickListener)
    }

    fun setAlbums(data: List<KSAlumsBookmarkViewModel>){
        albums = data.toMutableList()
    }

    fun updateAlbum(data: KSAlumsBookmarkViewModel){
        val index = albums?.indexOfFirst { it.album.collectionId == data.album.collectionId }
        Log.d("STEVE-DEBUG", index.toString())
        if (index != null) {
            albums?.set(index!!, data)
            notifyItemChanged(index)
        }

    }


    class ViewHodler(private val view: View): RecyclerView.ViewHolder(view){
        fun bindViewModel(
            viewModel: KSAlumsBookmarkViewModel,
            listener: (bookmark: Boolean, viewModel: KSAlumsBookmarkViewModel, viewHoler: RecyclerView.ViewHolder) -> Unit){
            val lbTitle = view.findViewById<TextView>(R.id.label_title)
            lbTitle.text = viewModel.title()

            val ivArtwork = view.findViewById<ImageView>(R.id.artwork)
            Glide.with(itemView).load(viewModel.artwork()).into(ivArtwork)

            val lbCaption = view.findViewById<TextView>(R.id.label_caption)
            lbCaption.text = viewModel.caption()

            val lbPrice = view.findViewById<TextView>(R.id.label_price)
            lbPrice.text = viewModel.price()

            val btnBookmark = view.findViewById<ImageButton>(R.id.btn_bookmark)
            btnBookmark.setImageResource(viewModel.bookmarkResource())
            btnBookmark.setOnClickListener(View.OnClickListener {
                viewModel.bookmarked = !viewModel.bookmarked
                listener.invoke(viewModel.bookmarked, viewModel, this)
            })
        }
    }
}