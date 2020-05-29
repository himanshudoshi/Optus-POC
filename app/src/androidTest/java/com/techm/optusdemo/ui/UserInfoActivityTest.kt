package com.techm.optusdemo.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.techm.optusdemo.R
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation Test for UserInfoActivity
 */
@RunWith(AndroidJUnit4::class)
class UserInfoActivityTest {

    /** Define ActivityTestRule */
    @get:Rule
    var userDetailActivityRule: ActivityTestRule<UserInfoActivity> =
        ActivityTestRule(UserInfoActivity::class.java)
    private var mActivity: UserInfoActivity? = null

    private val listItemPosition = 7
    private val id = "ID : 7"
    private val testname = "Name : Kurtis Weissnat"
    private val notname = "Name : Optus"
    private val testemail = "Email : Telly.Hoeger@billy.biz"
    private val testphone = "Phone : 210.067.6132"

    /** Function to test App successfully launch */
    @Test
    fun appLaunchSuccessfully() {
        Thread.sleep(5000)
        ActivityScenario.launch(UserInfoActivity::class.java)
    }

    /** Function to test Back Press button*/
    @Test
    fun click_backPress() {
        onView(isRoot()).perform(pressBackUnconditionally())
    }

    /** Function to test ProgressBar Displaying or not */
    @Test
    fun onLaunchCheckProgressBarIsDisplayed() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.progressBar))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }

    /** Function to test RecyclerView SwipeUp */
    @Test
    fun testSwipeUp_swipeUpRecyclerView() {
        Thread.sleep(5000)
        onView(withId(R.id.userInfo_recyclerView)).perform(
            withCustomConstraints(
                swipeUp(),
                isDisplayingAtLeast(listItemPosition)
            )
        )
    }

    /** Function to test RecyclerView Scrolling Position to specific Name */
    @Test
    fun testScrolling_scrollToPositionWithName() {
        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        listItemPosition,
                        itemWithIdClickTest(R.id.name)
                    )
            )
    }

    /** Function to test RecyclerView Scrolling Position to specific Email */
    @Test
    fun testScroll_scrollToPositionWithEmail() {
        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        listItemPosition,
                        itemWithIdClickTest(R.id.email)
                    )
            )
    }

    /** Function to test RecyclerView Scrolling Position to specific Phone Number */
    @Test
    fun testScroll_scrollToPositionWithphone() {
        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        listItemPosition,
                        itemWithIdClickTest(R.id.phone)
                    )
            )
    }

    /** Function to test UserInfo Displaying in RecyclerView or not */
    @Test
    fun testUserItemsList_UsersItemsListIsDisplayed() {

        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    /** Function to test RecyclerView Item Click Functionality */
    @Test
    fun testRecyclerview_item_click() {
        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                listItemPosition,
                click()
            )
        )
    }

    /** Function to test RecyclerView Name Item Negative Scenario*/
    @Test
    fun testRecyclerview_UserName_hasNoText() {
        Thread.sleep(3000)
        onView(withId(R.id.userInfo_recyclerView))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(hasDescendant(withText(notname)))
                )
            )
    }

    /** Function to test RecyclerView is Displayed */
    @Test
    fun onLaunchCheckRecyclerViewIsDisplayed() {
        Thread.sleep(3000)
        val recyclerView: RecyclerView =
            userDetailActivityRule.activity.findViewById(R.id.userInfo_recyclerView)
        onView(withId(R.id.userInfo_recyclerView)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
        Assert.assertNotSame(0, recyclerView.adapter?.itemCount)
    }

    /** Function to test RecyclerView Name TextView has User Name */
    @Test
    fun testRecyclerViewUserHasTextViewUserName() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.userInfo_recyclerView))
                .check(ViewAssertions.matches(hasDescendant(withId(R.id.name))))
                .check(ViewAssertions.matches(withText(testname)))
        }
    }

    /** Function to test RecyclerView Email TextView has User Email */
    @Test
    fun testRecyclerViewUserHasTextViewUserEmail() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.userInfo_recyclerView))
                .check(ViewAssertions.matches(hasDescendant(withId(R.id.email))))
                .check(ViewAssertions.matches(withText(testemail)))
        }
    }

    /** Function to test RecyclerView Phone TextView has User Phone */
    @Test
    fun testRecyclerViewHasTextViewUserPhone() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.userInfo_recyclerView))
                .check(ViewAssertions.matches(hasDescendant(withId(R.id.phone))))
                .check(ViewAssertions.matches(withText(testphone)))
        }
    }

    @After
    fun testDone() {
        mActivity = null
    }
}