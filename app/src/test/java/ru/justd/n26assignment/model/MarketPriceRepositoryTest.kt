package ru.justd.n26assignment.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
class MarketPriceRepositoryTest {

    lateinit var instance: MarketPriceRepository
    lateinit var remote: RetrofitMarketPriceDataSource

    val TEST_CASE_VALUE_1 = 0
    val TEST_CASE_VALUE_2 = 0

    @Before
    fun setup() {
        remote = mock(RetrofitMarketPriceDataSource::class.java)
        `when`(remote.loadPrices(Period.week)).then { createTestResponse(TEST_CASE_VALUE_1) }
        `when`(remote.loadPrices(Period.month)).then { createTestResponse(TEST_CASE_VALUE_2) }

        instance = MarketPriceRepository(
                remote,
                CacheMarketPriceDataSource()
        )
    }

    private fun createTestResponse(value: Int) =
            Single.just(
                    ChartsResponse(
                            "", "", "", "", "",
                            listOf(
                                    MarketPrice(value.toLong(), value.toFloat()),
                                    MarketPrice(value.toLong(), value.toFloat())
                            )
                    )
            )

    /**
     * Assure data is loaded from cache if available
     */
    @Test
    fun loadPrices() {
        //1) data should be loaded from network and cached
        val remoteWeekPrices = instance.loadPrices(Period.week).toBlocking().value()
        Assert.assertEquals(TEST_CASE_VALUE_1, remoteWeekPrices.values[0].timeSpan.toInt())

        //2) data should be loaded from cache
        val cachedWeekPrices = instance.loadPrices(Period.week).toBlocking().value()
        Assert.assertEquals(TEST_CASE_VALUE_1, cachedWeekPrices.values[0].timeSpan.toInt())

        //3) data should be loaded from cache
        val remoteMonthPrices = instance.loadPrices(Period.month).toBlocking().value()
        Assert.assertEquals(TEST_CASE_VALUE_2, remoteMonthPrices.values[0].timeSpan.toInt())

        //lets show we are aware of mockito
        verify(remote, times(1)).loadPrices(Period.week)
        verify(remote, times(1)).loadPrices(Period.month)
    }

}