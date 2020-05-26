package com.techm.optusdemo.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.model.useralbum.UserAlbum
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 *  Created Interface for Network Operations
 */
interface UserApi {
    @GET("users")
    fun getUserInfo(): Call<List<UserInfo>>

    @GET("photos")
    fun getUserAlbum(): Call<List<UserAlbum>>

    /*  @GET("users")
      fun getUserInfo(): Response<MutableLiveData<UserInfo>>

      @GET("photos")
      fun getUserAlbum(): Response<MutableLiveData<UserAlbum>>*/

    companion object Factory {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()
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