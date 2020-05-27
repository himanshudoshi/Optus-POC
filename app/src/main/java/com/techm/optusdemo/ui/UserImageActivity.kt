package com.techm.optusdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.techm.optusdemo.R
import com.techm.optusdemo.databinding.ActivityDetailsBinding

/**
 * Activity class displays User Detail ,url Image in Recyclerview in the screen
 */
class UserImageActivity : AppCompatActivity() {

    private lateinit var mBindings: ActivityDetailsBinding //it generates based on xml
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindings = DataBindingUtil.setContentView(this, R.layout.activity_details)
        setUpView()
    }

    /**
     * SetUp View
     */
    private fun setUpView() {

        linearLayoutManager = LinearLayoutManager(this)
        val bundle = intent
        val albumId: String? = bundle.getStringExtra("albumId")
        val photoId: String? = bundle.getStringExtra("photoId")
        val title: String? = bundle.getStringExtra("title")
        val url: String? = bundle.getStringExtra("url")

        mBindings.albumId.text = getString(R.string.album_id) + albumId
        mBindings.photoId.text = getString(R.string.photo_id) + photoId
        mBindings.userImageText.text = title
        mBindings.userImage.load(url) {
            crossfade(true)
            placeholder(R.drawable.image_not_available)
            error(R.drawable.ic_broken_image)
        }
    }
}
