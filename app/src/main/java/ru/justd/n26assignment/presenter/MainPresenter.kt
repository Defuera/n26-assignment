package ru.justd.n26assignment.presenter

import android.util.Log
import ru.justd.arkitec.presenter.ArkitecPresenter
import ru.justd.n26assignment.model.ChartsResponse.Period
import ru.justd.n26assignment.model.MarketPriceRepository
import ru.justd.n26assignment.view.MainView
import rx.functions.Action1
import javax.inject.Inject

/**
 * Created by defuera on 26/03/2017.
 */
class MainPresenter @Inject constructor() : ArkitecPresenter<MainView>() {

    @Inject
    lateinit var marketPriceRepository: MarketPriceRepository

    override fun onViewAttached() {}

    fun loadData(period: Period) {
        view().showLoading(true)
        subscribe(
                marketPriceRepository.loadPrices(period),
                Action1 { response -> view().showData(response.values, period) },
                Action1 {
                    t ->
                    t.printStackTrace()
                    view().showError(t.localizedMessage)
                }
        )
    }

}