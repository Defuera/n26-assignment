package ru.justd.n26assignment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import ru.justd.arkitec.view.BaseActivity

class MainActivity : BaseActivity<MainPresenter, MainView>(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun presenter() = presenter
    override fun view() = this


    override fun view(): MainView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun presenter(): MainPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
