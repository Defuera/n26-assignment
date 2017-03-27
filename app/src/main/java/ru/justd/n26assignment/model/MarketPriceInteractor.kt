package ru.justd.n26assignment.model

import android.text.format.DateUtils
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

        return when(period){
            ChartsResponse.Period.week -> api.getMarketPrices("day", (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS) / 1000)
            ChartsResponse.Period.month -> api.getMarketPrices("day", (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS * 4) / 1000)
            ChartsResponse.Period.year -> api.getMarketPrices("month", (System.currentTimeMillis() - DateUtils.YEAR_IN_MILLIS) / 1000)
            else -> throw IllegalArgumentException("unknown period $period")
        }
    }

}
