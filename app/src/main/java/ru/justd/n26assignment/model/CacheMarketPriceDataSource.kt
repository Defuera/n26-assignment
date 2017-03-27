package ru.justd.n26assignment.model

import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
class CacheMarketPriceDataSource : MarketPriceDataSource {

    private val cache = HashMap<ChartsResponse.Period, ChartsResponse<MarketPrice>>()

    override fun loadPrices(period: ChartsResponse.Period): Single<ChartsResponse<MarketPrice>> {
        if (!cache.containsKey(period)) {
            return Single.error(EmptyCacheException())
        } else {
            return Single.just(
                    cache[period]
            )
        }
    }

    override fun storePrices(period: ChartsResponse.Period, data: ChartsResponse<MarketPrice>) {
        cache.put(period, data)
    }

}