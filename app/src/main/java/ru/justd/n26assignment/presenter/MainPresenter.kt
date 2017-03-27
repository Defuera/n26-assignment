package ru.justd.n26assignment.presenter

import android.util.Log
import ru.justd.arkitec.presenter.ArkitecPresenter
import ru.justd.n26assignment.model.ChartsResponse
import ru.justd.n26assignment.model.MarketPriceInteractor
import ru.justd.n26assignment.view.MainView
import rx.functions.Action1
import javax.inject.Inject

/**
 * Created by defuera on 26/03/2017.
 */
class MainPresenter @Inject constructor() : ArkitecPresenter<MainView>() {

    @Inject
    lateinit var marketPriceInteractor : MarketPriceInteractor

    override fun onViewAttached() {

        subscribe(
                marketPriceInteractor.loadPrices(ChartsResponse.Period.day),
                Action1 { response -> view().showData(response.values) },
                Action1(Throwable::printStackTrace)
        )

    }

}