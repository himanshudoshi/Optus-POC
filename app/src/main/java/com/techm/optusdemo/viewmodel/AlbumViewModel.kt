package com.techm.optusdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.repository.UserRepository

/**
 *  The ViewModel for fetching a list of User Album Details.
 */
class AlbumViewModel(private val userRepository: UserRepository) : ViewModel() {

    // User Album
    fun getUserAlbumData(): MutableLiveData<List<UserAlbum>>? {
        return userRepository.loadUserAlbumData()
    }
}