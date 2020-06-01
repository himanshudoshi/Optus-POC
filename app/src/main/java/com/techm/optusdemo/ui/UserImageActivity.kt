package com.techm.optusdemo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.techm.optusdemo.R
import com.techm.optusdemo.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_user_image.*

/**
 * Activity class displays User Detail ,url Image in Recyclerview in the screen
 */
class UserImageActivity : AppCompatActivity() {

    private lateinit var mBindings: ActivityDetailsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindings = DataBindingUtil.setContentView(this, R.layout.activity_details)
        setUpView()
    }

    /** SetUp View */
    private fun setUpView() {

        linearLayoutManager = LinearLayoutManager(this)
        val bundle = intent
        val albumId: String? = bundle.getStringExtra("albumId")
        val photoId: String? = bundle.getStringExtra("photoId")
        val title: String? = bundle.getStringExtra("title")
        val url: String? = bundle.getStringExtra("url")
        showLoadingView()
        mBindings.albumId.text = (getString(R.string.album_id) + albumId)
        mBindings.photoId.text = (getString(R.string.photo_id) + photoId)
        mBindings.userImageText.text = title
        mBindings.userImage.load(url) {
            crossfade(true)
            placeholder(R.drawable.image_not_available)
            error(R.drawable.ic_broken_image)
        }
        hideLoadingView()
    }

    /** show ProgressBar while data fetching **/
    private fun showLoadingView() {
        details_progressBar?.visibility = View.VISIBLE
    }

    /** hide ProgressBar text while data fetching **/
    private fun hideLoadingView() {
        details_progressBar?.visibility = View.INVISIBLE
    }
}
