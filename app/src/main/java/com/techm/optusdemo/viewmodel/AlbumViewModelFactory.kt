package com.techm.optusdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techm.optusdemo.repository.UserRepository

/**
 * The ViewModelFactory class to create instance for ViewModel
 */
@Suppress("UNCHECKED_CAST")
class AlbumViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AlbumViewModel(userRepository) as T
}