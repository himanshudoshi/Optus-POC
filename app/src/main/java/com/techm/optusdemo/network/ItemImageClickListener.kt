package com.techm.optusdemo.network

/**
 *  Created Interface for Image Item Click Listener
 */
interface ItemImageClickListener {
    fun onItemClick(albumId: Int, photoId: Int, title: String, url: String)
}