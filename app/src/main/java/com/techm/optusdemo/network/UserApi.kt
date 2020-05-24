package com.techm.optusdemo.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.model.useralbum.UserAlbum
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 *  Created Interface for Network Operations
 */
interface UserApi {
    @GET("users")
    fun getUserInfo(): Call<List<UserInfo>>

    @GET("photos")
    fun getUserProfile(): Call<List<UserAlbum>>

    companion object Factory {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private var gson = GsonBuilder().setLenient().create()!!
        fun create(): UserApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(UserApi::class.java)
        }
    }
}