package com.techm.optusdemo.model.userinfo

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