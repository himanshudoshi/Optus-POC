package com.techm.optusdemo.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.network.UserApi
import com.techm.optusdemo.repository.UserRepository
import org.junit.Before
import org.junit.Test
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Rule
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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mUserRepository = UserRepository()
        this.mUserViewModel = UserViewModel(mUserRepository)
    }

    /** Test Class for UserInfo */
    @Test
    fun `GIVEN viewmodel WHEN UserInfo api request THEN user Info field should have values`() {

        //Given
        val viewmodel = UserViewModel(mUserRepository)
        // WHEN
        val result = viewmodel.getUserInfoData()
        // Then
        assertNotNull(result)
    }

    /** Test Class for UserAlbum */
    @Test
    fun `GIVEN viewmodel WHEN UserAlbum api request THEN user Album Details should have values`() {

        //Given
        val viewmodel = UserViewModel(mUserRepository)
        // WHEN
        val result = viewmodel.getUserAlbumData()
        // Then
        assertNotNull(result)
    }

    @Test
    fun test_getUserInfoAPISuccess() {

        `when`(this.mUserApi.getUserInfo()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfo>>
        this.mUserRepository.liveUserInfoResponse.observeForever(observer)
        this.mUserViewModel.getUserInfoData()

        assertNotNull(this.mUserViewModel.getUserInfoData())
    }

    @Test
    fun test_getUserInfoError() {

        `when`(this.mUserApi.getUserInfo()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

          val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfo>>
          this.mUserRepository.liveUserInfoResponse.observeForever(observer)

        this.mUserViewModel.getUserInfoData()

        assertNotNull(this.mUserViewModel.getUserInfoData())
    }


    @Test
    fun test_getUserAlbumAPISuccess() {

        `when`(this.mUserApi.getUserAlbum()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserRepository>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbum>>
        this.mUserRepository.liveUserAlbumResponse.observeForever(observer)
        this.mUserViewModel.getUserAlbumData()
        assertNotNull(this.mUserViewModel.getUserAlbumData())
    }

    @Test
    fun test_getUserAlbumError() {

        `when`(this.mUserApi.getUserAlbum()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbum>>
        this.mUserRepository.liveUserAlbumResponse.observeForever(observer)
        this.mUserViewModel.getUserAlbumData()
        assertNotNull(this.mUserViewModel.getUserAlbumData())

    }
}

