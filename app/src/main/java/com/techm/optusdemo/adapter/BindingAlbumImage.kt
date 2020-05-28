package com.techm.optusdemo.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * @Bind{binds the album image for data-binding}
 */
@BindingAdapter("thumbnail" , "error")
fun loadAlbumImage(imageView: ImageView , url: String , error: Drawable) {
    Picasso.get().load(url).error(error).into(imageView)
}