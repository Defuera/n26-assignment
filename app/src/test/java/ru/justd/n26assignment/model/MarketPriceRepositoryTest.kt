package ru.justd.n26assignment.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
class MarketPriceRepositoryTest {

    lateinit var instance: MarketPriceRepository

    @Before
    fun setup() {
        instance = MarketPriceRepository(
                TestRemoteMarketPriceDataSource(), //let's mock remote data source
                CacheMarketPriceDataSource()
        )
    }

    /**
     * Test cache
     */
    @Test
    fun loadPrices() {
        //1) data should be loaded from network and cached
        val remoteWeekPrices = instance.loadPrices(Period.week).toBlocking().value()
        Assert.assertEquals(0, remoteWeekPrices.values[0].timeSpan.toInt())

        //2) data should be loaded from cache
        val cachedWeekPrices = instance.loadPrices(Period.week).toBlocking().value()
        Assert.assertEquals(0, cachedWeekPrices.values[0].timeSpan.toInt())

        //3) data should be loaded from cache
        val remoteMonthPrices = instance.loadPrices(Period.month).toBlocking().value()
        Assert.assertEquals(1, remoteMonthPrices.values[0].timeSpan.toInt())
    }

    class TestRemoteMarketPriceDataSource : MarketPriceDataSource {

        var count = 0

        override fun loadPrices(period: Period): Single<ChartsResponse<MarketPrice>> {
            return Single.just(
                    ChartsResponse(
                            "", "", "", "", "",
                            listOf(
                                    MarketPrice(count.toLong(), count.toFloat()),
                                    MarketPrice(count.toLong(), count.toFloat())
                            )
                    )
            ).doOnSuccess { count++ }
        }

        override fun storePrices(period: Period, data: ChartsResponse<MarketPrice>) {
            throw UnsupportedOperationException()
        }

    }

}