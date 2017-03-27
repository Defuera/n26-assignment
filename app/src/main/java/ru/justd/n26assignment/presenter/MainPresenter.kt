package ru.justd.n26assignment.presenter

import ru.justd.arkitec.presenter.ArkitecPresenter
import ru.justd.n26assignment.view.MainView
import javax.inject.Inject

/**
 * Created by defuera on 26/03/2017.
 */
class MainPresenter @Inject constructor() : ArkitecPresenter<MainView>() {

    override fun onViewAttached() {
        view().showToast()
    }
}