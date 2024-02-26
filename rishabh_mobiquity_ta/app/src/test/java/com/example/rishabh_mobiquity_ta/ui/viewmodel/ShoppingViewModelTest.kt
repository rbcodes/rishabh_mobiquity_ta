package com.example.rishabh_mobiquity_ta.ui.viewmodel

import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.repository.ShoppingRepositoryTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class ShoppingViewModelTest {

    private lateinit var viewModel: ShoppingViewModel
    private lateinit var shoppingRepositoryTest : ShoppingRepositoryTest

    @Before
    fun setUp() {
        shoppingRepositoryTest = ShoppingRepositoryTest()
        viewModel = ShoppingViewModel(shoppingRepositoryTest)
    }

    @Test
    fun `call for data when successful network connection, returns list`() {
        shoppingRepositoryTest.SetShouldReturnNetworkError(false)
        runTest {
            val response = (shoppingRepositoryTest.getShoppingData().first() as ApiState.Success).data
            assertThat(response.size).isEqualTo(1)
            assertThat(response.get(0).name).isEqualTo("Food")
        }
    }

    @Test
    fun `call for data when network error, returns error`() {
        shoppingRepositoryTest.SetShouldReturnNetworkError(true)
        runTest {
            val response = (shoppingRepositoryTest.getShoppingData().first() as ApiState.Error).error
//            System.out.println(response)
            assertThat(response).isEqualTo("Error Fetching Api Data")
        }
    }


}