package ru.justd.n26assignment.presenter

import ru.justd.arkitec.presenter.ArkitecPresenter
import ru.justd.n26assignment.model.ChartsResponse.Period
import ru.justd.n26assignment.model.MarketPriceInteractor
import ru.justd.n26assignment.view.MainView
import rx.functions.Action1
import javax.inject.Inject

/**
 * Created by defuera on 26/03/2017.
 */
class MainPresenter @Inject constructor() : ArkitecPresenter<MainView>() {

    @Inject
    lateinit var marketPriceInteractor: MarketPriceInteractor

    override fun onViewAttached() {

        val period = Period.year
        loadData(period)

    }

    fun loadData(period: Period) {
        subscribe(
                marketPriceInteractor.loadPrices(period),
                Action1 { response -> view().showData(response.values, period) },
                Action1(Throwable::printStackTrace)
        )
    }

}