package ru.justd.n26assignment.view

import android.os.Bundle
import android.widget.Toast
import ru.justd.arkitec.view.ArkitecActivity
import ru.justd.n26assignment.App
import ru.justd.n26assignment.R
import ru.justd.n26assignment.presenter.MainPresenter
import javax.inject.Inject

/**
 * Create an Android application that will fetch information about the current Bitcoin market price
 * and display the data in a graph of your choosing.
 * The application must:
 * 1. Make a network call to a remote api resource to fetch the current exchange rate data, it is not necessary to demonstrate authentication or oauth processes,
 * 2. Effectively make use of threading and asynchronous behaviour,
 * 3. Show understanding of the Android SDK and application / activity lifecycles,
 * 4. Be performant and make appropriate use of background tasks,
 * 5. Show market price data over time using an appropriate graph.
 */
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