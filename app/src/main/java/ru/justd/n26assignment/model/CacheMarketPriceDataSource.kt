package ru.justd.n26assignment.model

import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
class CacheMarketPriceDataSource : MarketPriceDataSource {

    private val cache = HashMap<Period, ChartsResponse<MarketPrice>>()

    override fun loadPrices(period: Period): Single<ChartsResponse<MarketPrice>> {
        if (!cache.containsKey(period)) {
            return Single.error(EmptyCacheException())
        } else {
            return Single.just(
                    cache[period]
            )
        }
    }

    override fun storePrices(period: Period, data: ChartsResponse<MarketPrice>) {
        cache.put(period, data)
    }

}