package com.techm.optusdemo.model.userinfo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.techm.optusdemo.R
import com.techm.optusdemo.model.useralbum.UserAlbum

/**
 * data class for User Info
 */
data class UserInfo(

    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

@BindingAdapter("loadAlbumImage")
fun loadAlbumImage(view: ImageView, userAlbum: UserAlbum) { // This methods should not have any return type, = declaration would make it return that object declaration.
    Picasso.get().load(userAlbum.thumbnailUrl).placeholder(R.drawable.image_not_available).fit()
        .error(R.drawable.ic_broken_image).into(view)
}