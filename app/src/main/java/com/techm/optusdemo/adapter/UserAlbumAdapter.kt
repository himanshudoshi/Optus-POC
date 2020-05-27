package com.techm.optusdemo.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.jakewharton.rxbinding.view.RxView
import com.techm.optusdemo.R
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.network.ItemImageClickListener
import com.techm.optusdemo.ui.UserAlbumActivity
import kotlinx.android.synthetic.main.row_user_album.view.*

/** Class UserAlbum Adapter to populate Album items */
class UserAlbumAdapter(
    private var context: UserAlbumActivity,
    private var mUserList: ArrayList<UserAlbum>,
    private val itemClick: ItemImageClickListener
) : RecyclerView.Adapter<UserAlbumAdapter.ImageViewHolder>() {

    companion object {
        var mItemClickListener: ItemImageClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.row_user_album, parent, false)
        // var image = findViewById(R.id.image) as ImageView
        return ImageViewHolder(view)
    }

    /** Return item counts. */
    override fun getItemCount(): Int {
        return mUserList.size
    }

    /** Bind Data to ViewHolder */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        Log.e("list bind", "list bind")
        mItemClickListener = itemClick
        holder.userImageText.text = mUserList[position].title
        holder.userImage.load(mUserList[position].thumbnailUrl) {
            crossfade(true)
            placeholder(R.drawable.image_not_available)
            error(R.drawable.ic_broken_image)
        }
        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(
                mUserList[position].albumId,
                mUserList[position].id,
                mUserList[position].title,
                mUserList[position].url
            )
        }
    }

    /** This Class displays Album Images in RecyclerView */
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImageText = view.user_image_text!!
        val userImage: ImageView = view.user_image
        val mView = view
    }
}