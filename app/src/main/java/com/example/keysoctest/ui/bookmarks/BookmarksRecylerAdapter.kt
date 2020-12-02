package com.example.keysoctest.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keysoctest.R
import com.example.keysoctest.`class`.KSAlumsBookmarkViewModel

class BookmarksRecylerAdapter(
    private var viewModels: List<KSAlumsBookmarkViewModel>?)
    : RecyclerView.Adapter<BookmarksRecylerAdapter.ViewHolder>() {




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarksRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_albums, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return viewModels?.size ?: 0
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModels!!.get(position))
    }

    fun setData(data: List<KSAlumsBookmarkViewModel>){
        viewModels = data
    }

    //

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(viewModel: KSAlumsBookmarkViewModel){
            val lbTitle = view.findViewById<TextView>(R.id.label_title)
            lbTitle.text = viewModel.title()

            val ivArtwork = view.findViewById<ImageView>(R.id.artwork)
            Glide.with(itemView).load(viewModel.artwork()).into(ivArtwork)

            val lbCaption = view.findViewById<TextView>(R.id.label_caption)
            lbCaption.text = viewModel.caption()

            val lbPrice = view.findViewById<TextView>(R.id.label_price)
            lbPrice.text = viewModel.price()

            val btnBookmark = view.findViewById<ImageButton>(R.id.btn_bookmark)
            btnBookmark.visibility = View.INVISIBLE
        }
    }

}