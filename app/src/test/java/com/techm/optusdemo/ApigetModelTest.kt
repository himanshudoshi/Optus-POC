package com.techm.optusdemo

import com.techm.optusdemo.model.userinfo.UserInfo
import org.junit.Assert
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.junit.Before

/**
 *   Api Model Test Class
 */
class ApigetModelTest {

    lateinit var userInfoMock: UserInfo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userInfoMock = UserInfoAPISearchMock.userInfoMock
    }

    @Test
    fun testUserInfo() {

        Assert.assertEquals(userInfoMock.id, 1)
        Assert.assertEquals(userInfoMock.name, "Leanne Graham")
        Assert.assertEquals(userInfoMock.username, "Bret")
        Assert.assertEquals(userInfoMock.email, "Sincere@april.biz")
        Assert.assertEquals(userInfoMock.phone, "1-770-736-8031 x56442")
        Assert.assertEquals(userInfoMock.website, "hildegard.org")
    }

    @Test
    fun testAddressInfo() {

        Assert.assertEquals(userInfoMock.address.street, "Kulas Light")
        Assert.assertEquals(userInfoMock.address.suite, "Apt. 556")
        Assert.assertEquals(userInfoMock.address.city, "Gwenborough")
        Assert.assertEquals(userInfoMock.address.zipcode, "92998-3874")
        Assert.assertEquals(userInfoMock.address.geo.lat, "-37.3159")
        Assert.assertEquals(userInfoMock.address.geo.lng, "81.1496")
    }

    @Test
    fun testCompanyWrongInfo() {

        Assert.assertNotEquals(userInfoMock.company.name, "Romaguera")
        Assert.assertNotEquals(userInfoMock.company.catchPhrase, "Multi-layered client-server ")
        Assert.assertNotEquals(userInfoMock.company.bs, "harness real-time ")
    }

}

