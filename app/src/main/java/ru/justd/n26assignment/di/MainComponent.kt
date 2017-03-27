package ru.justd.n26assignment.di

import dagger.Component
import ru.justd.n26assignment.view.MainActivity
import javax.inject.Singleton

/**
 * Created by defuera on 27/03/2017.
 */
@Singleton
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(view: MainActivity)
}