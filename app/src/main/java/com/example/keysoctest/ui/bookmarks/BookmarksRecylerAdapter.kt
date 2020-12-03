package com.example.keysoctest.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.keysoctest.R
import com.example.keysoctest.`class`.BookmarkManager
import com.example.keysoctest.`class`.KSAlbums
import com.example.keysoctest.`class`.KSAlumsBookmarkViewModel

class BookmarksRecylerAdapter(
    var viewModels: List<KSAlumsBookmarkViewModel>)
    : RecyclerView.Adapter<BookmarksRecylerAdapter.ViewHolder>() {

    val viewBinderHelper: ViewBinderHelper = ViewBinderHelper()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarksRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_bookmark, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return viewModels.size ?: 0
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(holder.swipeLayout, position.toString())
        viewBinderHelper.closeLayout(position.toString())
        holder.bind(viewModels.get(position))
    }


    // function
    fun setData(data: List<KSAlumsBookmarkViewModel>){
//        viewModels = data
        var result = DiffUtil.calculateDiff(BookmarkDiffCallback(this.viewModels, data))
        viewModels = data
        result.dispatchUpdatesTo(this)
    }

    // View Holder Class
    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val swipeLayout: SwipeRevealLayout
        val lbTitle: TextView
        val ivArtwork: ImageView
        val lbCaption: TextView
        val lbPrice: TextView
        val btnBookmark: ImageButton
        val lbDelete: TextView

        init {
            swipeLayout = view.findViewById(R.id.swipelayout)
            lbTitle     = view.findViewById(R.id.label_title)
            ivArtwork   = view.findViewById(R.id.artwork)
            lbCaption   = view.findViewById(R.id.label_caption)
            lbPrice     = view.findViewById(R.id.label_price)
            btnBookmark = view.findViewById(R.id.btn_bookmark)
            lbDelete    = view.findViewById(R.id.lb_delete)
        }
        fun bind(viewModel: KSAlumsBookmarkViewModel){
            Glide.with(itemView).load(viewModel.artwork()).into(ivArtwork)
            lbTitle.text    = viewModel.title()
            lbCaption.text  = viewModel.caption()
            lbPrice.text    = viewModel.price()
            btnBookmark.visibility = View.INVISIBLE
            lbDelete.setOnClickListener(View.OnClickListener {
                BookmarkManager.shared.removeBookmark(viewModel.album)
            })

        }
    }

}