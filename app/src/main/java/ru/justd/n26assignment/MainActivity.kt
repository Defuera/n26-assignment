package ru.justd.n26assignment

import android.os.Bundle
import ru.justd.arkitec.view.ArkitecActivity
import javax.inject.Inject

class MainActivity : ArkitecActivity<MainPresenter, MainView>(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun presenter() = presenter
    override fun view() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
