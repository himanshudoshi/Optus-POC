package com.techm.optusdemo.repository

import androidx.lifecycle.MutableLiveData
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.network.UserApi.Factory.create
import retrofit2.Call
import retrofit2.Callback

/**
 * Retrofit Service for Network Operations
 */
class UserRepository {

    val liveUserInfoResponse: MutableLiveData<List<UserInfo>> = MutableLiveData()
    val liveUserAlbumResponse: MutableLiveData<List<UserAlbum>> = MutableLiveData()

    /** Fetch User Info Data from Network */
    fun loadUserInfoData(): MutableLiveData<List<UserInfo>>? {
        val retrofitCall = create().getUserInfo().apply {

            enqueue(object : Callback<List<UserInfo>> {
                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable?) {
                }

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: retrofit2.Response<List<UserInfo>>
                ) {

                    val list = response.body()
                    liveUserInfoResponse.value = list
                }
            })
        }
        return liveUserInfoResponse
    }

    /** Fetch User Profile Data from Network */
    fun loadUserAlbumData(): MutableLiveData<List<UserAlbum>>? {

        val retrofitCall = create().getUserAlbum()

        retrofitCall.enqueue(object : Callback<List<UserAlbum>> {
            override fun onFailure(call: Call<List<UserAlbum>>, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<List<UserAlbum>>,
                response: retrofit2.Response<List<UserAlbum>>
            ) {

                val lists = response.body()
                liveUserAlbumResponse.value = lists
            }
        })
        return liveUserAlbumResponse
    }
}