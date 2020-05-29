package com.techm.optusdemo.viewmodel


import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.network.UserApi
import com.techm.optusdemo.repository.UserRepository
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import java.net.SocketException

/** View Model Unit Test*/
@RunWith(JUnit4::class)
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var mUserApi: UserApi
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mUserRepository: UserRepository
    val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfo>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mUserRepository = UserRepository()
        this.mUserViewModel = UserViewModel(mUserRepository)
        mUserViewModel.getUserInfoData()?.observeForever(observer)

    }

    /** Test Class for UserInfo */
    @Test
    fun test_UserInfoApiHasValue() {

        //Given
        val viewModel = UserViewModel(mUserRepository)
        // WHEN
        val result = viewModel.getUserInfoData()
        // Then
        assertNotNull(result)
    }

    /** Test Class for UserInfo Success Scenario */
    @Test
    fun test_getUserInfoAPISuccess() {

        `when`(this.mUserApi.getUserInfo()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfo>>
        this.mUserRepository.liveUserInfoResponse.observeForever(observer)
        Thread.sleep(7000)
        assertNotNull(this.mUserRepository.liveUserInfoResponse.value)
    }

    /** Test Class for UserInfo Failure Scenario */
    @Test
    fun test_getUserInfoError() {

        `when`(this.mUserApi.getUserInfo()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfo>>
        this.mUserRepository.liveUserInfoResponse.observeForever(observer)
        Thread.sleep(7000)
        assertNotNull(this.mUserRepository.statusMessage.value)
    }

}

