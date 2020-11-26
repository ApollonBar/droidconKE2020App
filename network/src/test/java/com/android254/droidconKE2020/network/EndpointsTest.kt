package com.android254.droidconKE2020.network

import com.android254.droidconKE2020.network.di.networkModule
import com.android254.droidconKE2020.network.payloads.GoogleToken
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.declare

class EndpointsTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(networkModule, fakeModule)
    }

    val service: ApiService by inject()

    lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun testGetSponsors() = runBlocking {
        server.enqueue(MockResponse().setBody("""{"data": [{"name": "test"}]}"""))
        server.start()
        declare {
            server.url("/")
        }
        val sponsors = service.events.getSponsors()
        assertThat(sponsors.data.size, `is`(1))
    }

    @Test
    fun testGetAccessToken() = runBlocking {
        server.enqueue(MockResponse().setBody("""{"token": "abc", "user": {"name": "abc"}}"""))
        server.start()
        declare {
            server.url("/")
        }
        val token = service.auth.googleLogin(GoogleToken("some token"))
        assertThat(token.token, `is`("abc"))
    }

    @Test
    fun testLogout() = runBlocking {
        server.enqueue(MockResponse().setBody("""{"message": "Success"}"""))
        server.start()
        declare {
            server.url("/")
        }
        val message = service.auth.logout()
        assertThat(message.message, `is`("Success"))
    }

    @Test
    fun testGetFeed() = runBlocking {
        server.enqueue(
            MockResponse().setBody(
                """{
  "data": [
    {
      "title": "Test",
      "body": "Good one",
      "topic": "droidconweb",
      "url": "https://droidcon.co.ke",
      "image": "http://localhost:8000/upload/event/feeds/dangyntvmaet8jgjpg.jpg",
      "created_at": "2020-03-19 18:45:49"
    }
  ],
  "meta": {
    "paginator": {
      "count": 2,
      "per_page": "10",
      "current_page": 1,
      "next_page": null,
      "has_more_pages": false,
      "next_page_url": null,
      "previous_page_url": null
    }
  }
}"""
            )
        )
        server.start()
        declare {
            server.url("/")
        }
        val feed = service.feed.fetchFeeds()
        assertThat(feed.feedItems, `is`(sampleFeed))
    }

    @Test
    fun testSendEventFeedback() = runBlocking {
        server.enqueue(MockResponse().setBody("""{"message": "Feedback sent successfully, Thank you"}"""))
        server.start()
        declare {
            server.url("/")
        }
        val message = service.eventFeedback.sendEventFeedback("Awesome", 4)
        assertThat(message.body()!!.message, `is`("Feedback sent successfully, Thank you"))
    }
}