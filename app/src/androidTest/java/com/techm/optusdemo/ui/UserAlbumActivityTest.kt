package com.techm.optusdemo.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.techm.optusdemo.R
import com.techm.optusdemo.R.id.image_recyclerView
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation Test for UserAlbumActivityTest
 */
@RunWith(AndroidJUnit4::class)
class UserAlbumActivityTest {

    /** Define ActivityTestRule */
    @get:Rule
    var userAlbumActivityTest: ActivityTestRule<UserAlbumActivity> =
        ActivityTestRule(UserAlbumActivity::class.java)
    private var mActivity: UserAlbumActivity? = null

    /** Function to test App successfully launch */
    @Test
    fun appLaunchSuccessfully() {
        Thread.sleep(5000)
        ActivityScenario.launch(UserAlbumActivity::class.java)
       // intended(hasComponent(UserAlbumActivity::class.java.name))
    }

    /** Function to test Back Press button*/
    @Test
    fun click_backPress() {
        onView(isRoot()).perform(ViewActions.pressBackUnconditionally())
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
        onView(withId(R.id.image_recyclerView)).perform(ViewActions.swipeUp())
    }

    /** Function to test RecyclerView SwipeDown */
    @Test
    fun testSwipeDown_swipeDownRecyclerView() {
        onView(withId(R.id.image_recyclerView)).perform(ViewActions.swipeDown())
    }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testScrolling_scrollToPositionWithThumbNailUrl() {
        Thread.sleep(3000)
        onView(withId(R.id.image_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    itemWithIdClickTest(R.id.user_image)
                )
            )
    }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testIntent_toUserImageActivity() {
        Thread.sleep(3000)
        onView(withId(R.id.image_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(1, itemWithIdClickTest(R.id.name))
            )
        intended(hasComponent(UserImageActivity::class.java.name))
    }

    /** Function to test RecyclerView Scrolling Position */
    @Test
    fun testScroll_scrollToPositionWithImageText() {
        Thread.sleep(3000)
        onView(withId(R.id.image_recyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    itemWithIdClickTest(R.id.user_image_text)
                )
            )
    }

    /** Function to test Album Details Displaying in RecyclerView or not */
    @Test
    fun testAlbumItemsList_AlbumItemsListIsDisplayed() {
        Thread.sleep(3000)
        onView(withId(image_recyclerView))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    /** Function to test RecyclerView Item Click Functionality */
    @Test
    fun testRecyclerview_item_click() {
        Thread.sleep(3000)
        onView(withId(R.id.image_recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
    }

    /** Function to test RecyclerView Text Negative Scenario*/
    @Test
    fun testRecyclerview_ImageText_hasNoText() {
        Thread.sleep(3000)
        onView(withId(image_recyclerView))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(
                        hasDescendant(
                            withText(
                                "ottomanoer nausaate"
                            )
                        )
                    )
                )
            )
    }

    /** Function to test RecyclerView Displayed or not */
    @Test
    fun onLaunchCheckRecyclerViewIsDisplayed() {
        Thread.sleep(3000)
        val recyclerView: RecyclerView =
            userAlbumActivityTest.activity.findViewById(image_recyclerView)
        onView(withId(image_recyclerView)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
        Assert.assertNotSame(0, recyclerView.adapter?.itemCount)
    }

    /** Function to test RecyclerView Image Text */
    @Test
    fun testRecyclerview_ImageText_hasText() {
        Thread.sleep(3000)
        onView(withId(image_recyclerView))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.allOf(withText("officia porro iure quia iusto qui ipsa ut modi"))
                )
            )
    }

    @After
    fun testDone() {
        mActivity = null
    }
}

