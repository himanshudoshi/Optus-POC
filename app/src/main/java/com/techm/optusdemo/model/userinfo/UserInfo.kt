package com.techm.optusdemo.model.userinfo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
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
fun loadAlbumImage(
    imageView: ImageView,
    userAlbum: UserAlbum
) {
    imageView.load(userAlbum.thumbnailUrl) {
        crossfade(true)
        placeholder(R.drawable.image_not_available)
        error(R.drawable.ic_broken_image)
    }
}