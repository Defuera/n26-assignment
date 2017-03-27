package ru.justd.n26assignment

import android.app.Application
import ru.justd.n26assignment.di.DaggerMainComponent
import ru.justd.n26assignment.di.MainComponent

/**
 * Created by defuera on 27/03/2017.
 */
class App : Application() {

    lateinit var graph: MainComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerMainComponent.builder().build()
    }
}