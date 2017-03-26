package ru.justd.arkitec.view

import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import ru.justd.arkitec.presenter.ArkitecPresenter

abstract class ArkitecActivity<out P : ArkitecPresenter<V>, V> : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        attachPresenter()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        attachPresenter()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        detachPresenter()
    }

    @CallSuper
    open fun attachPresenter() = presenter().attachView(view())

    @CallSuper
    open fun detachPresenter() = presenter().detachView()

    abstract fun view(): V

    abstract fun presenter(): P

}