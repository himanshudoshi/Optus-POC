package com.techm.optusdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.optusdemo.R
import com.techm.optusdemo.adapter.UserDataAdapter
import com.techm.optusdemo.databinding.ActivityMainBinding
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.network.ItemClickListener
import com.techm.optusdemo.repository.UserRepository
import com.techm.optusdemo.viewmodel.UserViewModel
import com.techm.optusdemo.utils.Utils
import com.techm.optusdemo.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity class displays all User Details in Recyclerview in the screen
 */
class UserInfoActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var factory: UserViewModelFactory
    private var mBindings: ActivityMainBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //data binding
        mBindings = DataBindingUtil.setContentView(this, R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        mBindings!!.recyclerView.layoutManager = linearLayoutManager
        if (Utils.hasNetwork(this) == true) {
            getUserProfileData()
        } else makeText(
            this,
            getString(R.string.internet_connection_not_available),
            LENGTH_SHORT
        ).show()
    }

    /** Initialize ViewModel and fetch data from viewModel to Activity. */
    private fun getUserProfileData() {

        //live data
        userRepository = UserRepository()
        factory = UserViewModelFactory(userRepository)
        val mUserViewModel =
            ViewModelProviders.of(this@UserInfoActivity, factory).get(UserViewModel::class.java)
        mUserViewModel.getUserInfoData()?.observe(this, Observer { userList ->
            recyclerView.adapter =
                UserDataAdapter(this@UserInfoActivity, userList as ArrayList<UserInfo>, object :
                    ItemClickListener {
                    override fun onItemClick(pos: Int, name: String) {
                        intent = Intent(this@UserInfoActivity, UserAlbumActivity::class.java)
                        intent.putExtra("id", name)
                        startActivity(intent)
                    }
                })
        })
    }
}

