package com.example.testapplication.data

import androidx.test.platform.app.InstrumentationRegistry
import com.example.testapplication.data.data_source.NetworkDataSourceImpl
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.testdoubles.FakeErrorRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals

class CatFactsRepositoryTest {

    private lateinit var subject: CatFactsRepository

    @Before
    fun setup() {
        val dataSource = NetworkDataSourceImpl()
        val dispatcher = StandardTestDispatcher()
        val errorRepo = FakeErrorRepository()
        val networkMonitor = ConnectivityManagerNetworkMonitor(InstrumentationRegistry.getInstrumentation().targetContext)
        subject = NetwCatFactsRepository(
            dataSource = dataSource,
            imageGenerator = ImageGenerator(),
            ioDispatcher = dispatcher,
            errorRepository = errorRepo,
            networkMonitor = networkMonitor
        )
    }

    @Test
    fun CatFactsRepositoryTest_fetch_cat_facts() = runTest {
        val itemsList = mutableListOf<List<CatItem>>()
         backgroundScope.launch {
             subject.catFactsStream.toList(itemsList)
         }

        assertNotEquals(itemsList.size, 0)
    }
}