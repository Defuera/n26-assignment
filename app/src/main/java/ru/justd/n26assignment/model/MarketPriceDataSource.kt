package ru.justd.n26assignment.model

import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
interface MarketPriceDataSource {

    fun loadPrices(period: Period): Single<ChartsResponse<MarketPrice>>
    fun storePrices(period: Period, data: ChartsResponse<MarketPrice>)

}