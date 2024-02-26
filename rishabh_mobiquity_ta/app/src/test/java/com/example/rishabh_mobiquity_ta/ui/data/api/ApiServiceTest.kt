package com.example.rishabh_mobiquity_ta.ui.data.api

import com.example.rishabh_mobiquity_ta.ui.data.entity.Products
import com.example.rishabh_mobiquity_ta.ui.data.entity.SalePrice
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: ApiService
    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(ApiService::class.java)
    }

    @Test
    fun `api service call, returns empty response`() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = service.getShoppingData()
        mockWebServer.takeRequest()

        assertThat(response.body()).isEmpty()
    }

    @Test
    fun `api service call, returns response with two categories`() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = service.getShoppingData()
        mockWebServer.takeRequest()

        assertThat(response.body()?.size).isEqualTo(2)
    }

    @Test
    fun `api service call, returns Error`() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something Went Wrong!!")
        mockWebServer.enqueue(mockResponse)

        val response = service.getShoppingData()
        mockWebServer.takeRequest()

        assertThat(response.code()).isEqualTo(404)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
