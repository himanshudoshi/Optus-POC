package com.techm.optusdemo.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.techm.optusdemo.R
import com.techm.optusdemo.R.id.recyclerView
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation Test for UserDetailsActivity
 */
@RunWith(AndroidJUnit4::class)
class UserInfoActivityTest {

    /** Define ActivityTestRule */
    @get:Rule
    var userDetailActivityRule: ActivityTestRule<UserInfoActivity> =
        ActivityTestRule(UserInfoActivity::class.java)
    var mActivity: UserInfoActivity? = null
    @get:Rule
    val intentsTestRule = IntentsTestRule(UserInfoActivity::class.java)
    /** Function to test App successfully launch */
    @Test
    fun appLaunchSuccessfully() {
        Thread.sleep(5000)
       ActivityScenario.launch(UserInfoActivity::class.java)
    }

    /** Function to test Back Press button*/
    @Test
    fun click_backPress() {
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBackUnconditionally());
    }
    /** Function to test ProgressBar Displaying or not */
    @Test
    fun onLaunchCheckProgressBarIsDisplayed() {
        IdlingResource.ResourceCallback {
            Espresso.onView(withId(R.id.progressBar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    /** Function to test RecyclerView SwipeUp */
    @Test
    fun testSwipeUp_swipeUpRecyclerView() {
        Espresso.onView(withId(recyclerView)).perform(ViewActions.swipeUp())
    }

    /** Function to test RecyclerView SwipeUp */
    @Test
    fun testSwipeDown_swipeDownRecyclerView() {
        Espresso.onView(withId(recyclerView)).perform(ViewActions.swipeDown())
    }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testScrolling_scrollToPositionWithName() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, clickItemWithId(R.id.name))
            )  }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testIntent_toAnotherActivity() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, clickItemWithId(R.id.name))
            )
        intended(hasComponent(UserAlbumActivity::class.java.name))

    }
    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testScroll_scrollToPositionWithEmail() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, clickItemWithId(R.id.email))
            )  }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testScroll_scrollToPositionWithphone() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, clickItemWithId(R.id.phone))
            )  }
    /** Function to test EmployeeDetails Displaying in RecyclerView or not */
    @Test
    fun testUserItemsList_UsersItemsListIsDisplayed() {
        Thread.sleep(3000)

        Espresso.onView(withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /** Function to test RecyclerView Item Click Functionality */
    @Test
    fun testRecyclerview_item_click() {
        Thread.sleep(3000)

        Espresso.onView(withId(recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(2,
                click()
            )
        )
    }

    /** Function to test RecyclerView Title Negative Scenario*/
    @Test
    fun testRecyclerview_UserName_hasNoText() {
        Thread.sleep(3000)
        Espresso.onView(withId(recyclerView))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(
                        hasDescendant(
                            withText(
                                "Himanshu"
                            )
                        )
                    )
                )
            );
    }

    @Test
    fun onLaunchCheckRecyclerViewIsDisplayed() {
        Thread.sleep(3000)
        val recyclerView: RecyclerView =
            userDetailActivityRule.activity.findViewById(recyclerView)
        Espresso.onView(withId(R.id.recyclerView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Assert.assertNotSame(0, recyclerView.adapter?.itemCount)
    }

    /** Function to test RecyclerView Title */
    @Test
    fun testRecyclerview_UserName_hasText() {
        Thread.sleep(3000)
        Espresso.onView(withId(recyclerView))
            .check(ViewAssertions.matches(
                CoreMatchers.allOf(withText("Tiger Nixon"))
            ));
    }
    @After
    fun testDone() {
        mActivity = null
    }
}