package ru.justd.n26assignment.view

import ru.justd.n26assignment.model.ChartsResponse
import ru.justd.n26assignment.model.MarketPrice

/**
 * Created by defuera on 26/03/2017.
 */
interface MainView {
    fun showData(data : List<MarketPrice>, period: ChartsResponse.Period)
    fun showError(localizedMessage: String?)
    fun showLoading(show: Boolean)
}