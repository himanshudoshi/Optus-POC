package com.techm.optusdemo

import com.techm.optusdemo.model.userinfo.UserInfo
import org.junit.Assert
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.junit.Before

/**
 *   Api Model Test Class
 */
class ApiModelTest {

    private lateinit var userInfoMock: UserInfo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userInfoMock = UserInfoAPISearchMock.userInfoMock
    }

    /** Test for UserInfo Data Class */
    @Test
    fun testUserInfo() {

        Assert.assertEquals(userInfoMock.id, 1)
        Assert.assertEquals(userInfoMock.name, "Leanne Graham")
        Assert.assertEquals(userInfoMock.username, "Bret")
        Assert.assertEquals(userInfoMock.email, "Sincere@april.biz")
        Assert.assertEquals(userInfoMock.phone, "1-770-736-8031 x56442")
        Assert.assertEquals(userInfoMock.website, "hildegard.org")
    }

    /** Test for Company Data Class */
    @Test
    fun testCompanyWrongInfo() {

        Assert.assertNotEquals(userInfoMock.company.name, "Romaguera")
        Assert.assertNotEquals(userInfoMock.company.catchPhrase, "Multi-layered client-server ")
        Assert.assertNotEquals(userInfoMock.company.bs, "harness real-time ")
    }
}

