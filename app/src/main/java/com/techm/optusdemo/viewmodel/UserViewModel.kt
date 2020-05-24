package com.techm.optusdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.repository.UserRepository

/**
 *  The ViewModel for fetching a list of User Details.
 */
 class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

   // private val mService = User()

    fun getUserInfoData(): MutableLiveData<List<UserInfo>>? {
        return userRepository.loadUserInfoData()
    }

    fun getUserProfileData(): MutableLiveData<List<UserAlbum>>? {
        return userRepository.loadUserProfileData()
    }
}