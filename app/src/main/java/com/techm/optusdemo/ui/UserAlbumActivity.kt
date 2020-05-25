package com.techm.optusdemo.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.optusdemo.R
import com.techm.optusdemo.viewmodel.UserViewModel
import com.techm.optusdemo.adapter.UserAlbumAdapter
import com.techm.optusdemo.databinding.ActivityUserImageBinding
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.utils.Utils
import com.techm.optusdemo.network.ItemImageClickListener
import com.techm.optusdemo.repository.UserRepository
import com.techm.optusdemo.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_user_image.*
import kotlinx.android.synthetic.main.activity_user_image.album_id
import java.util.stream.Collectors

/**
 * Activity class displays User ID , Album ID and Image in Recyclerview in the screen
 */
@Suppress("NAME_SHADOWING")
class UserAlbumActivity : AppCompatActivity() {

    private lateinit var id: String
    private lateinit var mBindings: ActivityUserImageBinding //it generates based on xml
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userRepository: UserRepository
    private lateinit var factory: UserViewModelFactory

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //data binding
        mBindings = DataBindingUtil.setContentView(this, R.layout.activity_user_image)
        setUpView()
    }

    /**
     * SetUp View
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpView() {
        linearLayoutManager = LinearLayoutManager(this)
        mBindings.imageRecyclerView.layoutManager = linearLayoutManager
        val bundle = intent
        id = bundle.getStringExtra("id")

        image_recyclerView.layoutManager = LinearLayoutManager(this)
        album_id.text = getString(R.string.album_id) + id
        if (Utils.hasNetwork(this) == true) {
            getUserProfileImages()
        } else {
            Toast.makeText(
                this,
                getString(R.string.internet_connection_not_available),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    /** Initialize ViewModel and fetch data from viewModel to Activity. */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getUserProfileImages() {
        userRepository = UserRepository()
        factory = UserViewModelFactory(userRepository)
        val mUserAlbumViewModel =
            ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
        mUserAlbumViewModel.getUserAlbumData()
            ?.observe(this@UserAlbumActivity, Observer { userAlbumList ->
                val articleList = userAlbumList as ArrayList<UserAlbum>
                val filteredArticleList =
                    articleList.stream()
                        .filter { androidList -> androidList.albumId.toString() == id }
                        .collect(Collectors.toList())
                image_recyclerView.adapter =
                    UserAlbumAdapter(
                        this@UserAlbumActivity,
                        filteredArticleList as ArrayList<UserAlbum>,
                        object :
                            ItemImageClickListener {
                            override fun onItemClick(
                                albumId: Int,
                                photoId: Int,
                                title: String,
                                url: String
                            ) {
                                intent =
                                    Intent(this@UserAlbumActivity, UserImageActivity::class.java)
                                intent.putExtra("albumId", albumId.toString())
                                intent.putExtra("photoId", photoId.toString())
                                intent.putExtra("title", title)
                                intent.putExtra("url", url)
                                startActivity(intent)
                            }
                        })
            })
    }
}