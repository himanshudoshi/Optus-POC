package com.techm.optusdemo.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techm.optusdemo.model.useralbum.UserAlbum
import com.techm.optusdemo.network.UserApi
import com.techm.optusdemo.repository.UserRepository
import io.reactivex.Maybe
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
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

/** Album View Model Unit Test*/
@RunWith(JUnit4::class)
class AlbumViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var mUserApi: UserApi
    private lateinit var mAlbumViewModel: AlbumViewModel
    private lateinit var mUserRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mUserRepository = UserRepository()
        this.mAlbumViewModel = AlbumViewModel(mUserRepository)
    }

    /** Test Class for UserAlbum */
    @Test
    fun test_UserAlbumApiHasValue() {

        //Given
        val viewModel = AlbumViewModel(mUserRepository)
        // WHEN
        val result = viewModel.getUserAlbumData()
        // Then
        assertNotNull(result)
    }

    /** Test Class for UserAlbum Success Scenario */
    @Test
    fun test_getUserAlbumAPISuccess() {

        `when`(this.mUserApi.getUserAlbum()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserRepository>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbum>>
        val result = this.mUserRepository.liveUserAlbumResponse
        result.observeForever(observer)
        this.mAlbumViewModel.getUserAlbumData()
        Thread.sleep(7000)
        assertNotNull(result.value)
    }

    @Test
    fun test_getUserAlbumError() {

        `when`(this.mUserApi.getUserAlbum()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbum>>
        this.mUserRepository.liveUserAlbumResponse.observeForever(observer)
        Thread.sleep(7000)
        assertNotNull(this.mUserRepository.statusMessage.value)
    }
}

