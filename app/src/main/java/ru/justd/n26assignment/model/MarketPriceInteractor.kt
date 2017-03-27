package ru.justd.n26assignment.model

import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by defuera on 27/03/2017.
 */
@Singleton
class MarketPriceInteractor @Inject constructor() {

    @Inject
    lateinit var api: RestClient

    fun loadPrices(period: ChartsResponse.Period): Single<ChartsResponse<MarketPrice>> {
        return api.getMarketPrices(period)
    }
}
