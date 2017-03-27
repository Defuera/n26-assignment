package ru.justd.n26assignment.view

import android.os.Bundle
import android.widget.Toast
import ru.justd.arkitec.view.ArkitecActivity
import ru.justd.n26assignment.App
import ru.justd.n26assignment.R
import ru.justd.n26assignment.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : ArkitecActivity<MainPresenter, MainView>(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun presenter() = presenter
    override fun view() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).graph.inject(this)

        setContentView(R.layout.activity_main)
    }

    override fun showToast() {
        Toast.makeText(this, "lol", Toast.LENGTH_LONG).show()
    }

}