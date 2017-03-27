package ru.justd.n26assignment.model

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

/**
 * Created by defuera on 27/03/2017.
 */
interface RestClient {

    @GET("/charts/market-price?format=json")
    fun getMarketPrices(
            @Query("period") period: String,
            @Query("start") startTimestamp : Long
    ): Single<ChartsResponse<MarketPrice>>

}