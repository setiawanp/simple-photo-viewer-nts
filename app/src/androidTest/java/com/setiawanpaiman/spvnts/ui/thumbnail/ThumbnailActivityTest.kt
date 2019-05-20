package com.setiawanpaiman.spvnts.ui.thumbnail

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.setiawanpaiman.spvnts.MainApplication
import com.setiawanpaiman.spvnts.R
import com.setiawanpaiman.spvnts.di.components.DaggerTestApplicationComponent
import com.setiawanpaiman.spvnts.di.modules.TestApplicationModule
import com.setiawanpaiman.spvnts.fakes.FakeResponse
import com.setiawanpaiman.spvnts.matchers.RecyclerViewItemCountAssertion
import com.setiawanpaiman.spvnts.matchers.ViewMatchersExt.nthChildOf
import com.setiawanpaiman.spvnts.ui.detail.PhotoDetailActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@RunWith(AndroidJUnit4::class)
class ThumbnailActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(ThumbnailActivity::class.java, false, false)

    private lateinit var app: MainApplication
    private val server = MockWebServer()

    @Before
    fun setUp() {
        val baseUrl = server.url("/").toString()
        app = ApplicationProvider.getApplicationContext()
        DaggerTestApplicationComponent.builder()
            .testApplicationModule(TestApplicationModule(baseUrl))
            .build()
            .inject(app)
    }

    @Test
    fun successShowAllData() {
        server.enqueue(MockResponse().setBody(FakeResponse.PHOTO_RESPONSE_JSON))

        testRule.launchActivity(null)
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(RecyclerViewItemCountAssertion(5))
    }

    @Test
    fun failedShowError() {
        server.enqueue(MockResponse().setResponseCode(500))

        testRule.launchActivity(null)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.error_message)))
    }

    @Test
    fun clickImage() {
        server.enqueue(MockResponse().setBody(FakeResponse.PHOTO_RESPONSE_JSON))

        testRule.launchActivity(null)
        onView(allOf(nthChildOf(withId(R.id.recyclerView), 0))).perform(click())

        intended(
            allOf(
                hasComponent(PhotoDetailActivity::class.java.name),
                hasExtra("EXTRA_PHOTO_URL", "http://url.com/photo1")
            )
        )
    }

    @Test
    fun swipeRefresh() {
        server.enqueue(MockResponse().setBody(FakeResponse.PHOTO_RESPONSE_JSON))

        testRule.launchActivity(null)
        onView(withId(R.id.swipeRefresh)).perform(swipeDown())

        server.enqueue(MockResponse().setBody(FakeResponse.EMPTY_PHOTO_RESPONSE_JSON))
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(RecyclerViewItemCountAssertion(0))
    }
}