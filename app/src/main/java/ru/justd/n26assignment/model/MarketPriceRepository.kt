package ru.justd.n26assignment.model

import rx.Single
import rx.exceptions.Exceptions

/**
 * Created by defuera on 27/03/2017.
 */
class MarketPriceRepository constructor(
        private val remote: MarketPriceDataSource,
        private val local: MarketPriceDataSource
) {

    fun loadPrices(period: Period): Single<ChartsResponse<MarketPrice>> {
        return local
                .loadPrices(period)
                .onErrorResumeNext { throwable ->
                    if (throwable is EmptyCacheException) {
                        remote
                                .loadPrices(period)
                                .doOnSuccess { local.storePrices(period, it) }
                    } else {
                        throw Exceptions.propagate(throwable)
                    }

                }
    }

}
