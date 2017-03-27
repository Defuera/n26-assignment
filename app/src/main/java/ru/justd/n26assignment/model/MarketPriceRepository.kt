package ru.justd.n26assignment.model

import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
class MarketPriceRepository constructor(
        val remote: MarketPriceDataSource,
        val local: MarketPriceDataSource
) {


    fun loadPrices(period: ChartsResponse.Period): Single<ChartsResponse<MarketPrice>> {
        return remote.loadPrices(period)
    }

}
