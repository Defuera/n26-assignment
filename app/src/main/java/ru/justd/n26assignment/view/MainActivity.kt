package ru.justd.n26assignment.view

import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import ru.justd.arkitec.view.ArkitecActivity
import ru.justd.n26assignment.App
import ru.justd.n26assignment.R
import ru.justd.n26assignment.model.MarketPrice
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

    @BindView(R.id.graph)
    lateinit var graph: SparkView

    override fun presenter() = presenter
    override fun view() = this

    val graphAdapter = GraphAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).graph.inject(this)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        graph.adapter = graphAdapter
    }

    override fun showData(data: List<MarketPrice>) {
            graphAdapter.data = data
    }

    class GraphAdapter : SparkAdapter() {

        var data : List<MarketPrice>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getY(index: Int): Float {
            return data?.get(index)?.value ?: 0f
        }

        override fun getItem(index: Int): Any {
            return "magy"
        }

        override fun getX(index: Int): Float {
            return super.getX(index) //todo
        }

        override fun getCount() = data?.size ?: 0

    }
}