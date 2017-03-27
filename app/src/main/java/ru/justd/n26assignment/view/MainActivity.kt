package ru.justd.n26assignment.view

import android.os.Bundle
import android.text.format.DateUtils
import android.widget.RadioGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import ru.justd.arkitec.view.ArkitecActivity
import ru.justd.n26assignment.App
import ru.justd.n26assignment.R
import ru.justd.n26assignment.model.ChartsResponse.Period
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

    @BindView(R.id.radio_group)
    lateinit var radioGroup: RadioGroup

    @BindView(R.id.graph)
    lateinit var graph: SparkView

    @BindView(R.id.min_rate)
    lateinit var minRate: TextView

    @BindView(R.id.max_rate)
    lateinit var maxRate: TextView

    @BindView(R.id.start_date)
    lateinit var startDate: TextView

    @BindView(R.id.end_date)
    lateinit var endDate: TextView

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

    override fun showData(data: List<MarketPrice>, period: Period) {
        graphAdapter.data = data

        val firstItem = data[0]
        val lastItem = data[data.lastIndex]

        minRate.text = firstItem.value.toString()
        maxRate.text = lastItem.value.toString()

        val flags: Int = when (period) {
            Period.week -> DateUtils.FORMAT_SHOW_TIME and DateUtils.FORMAT_SHOW_DATE
            Period.month -> DateUtils.FORMAT_SHOW_WEEKDAY and DateUtils.FORMAT_SHOW_DATE
            Period.year -> DateUtils.FORMAT_SHOW_DATE and DateUtils.FORMAT_SHOW_YEAR
            else -> throw IllegalArgumentException("unknown period $period")
        }

        startDate.text = DateUtils.formatDateTime(this, firstItem.timeSpan * 1000, flags)
        endDate.text = DateUtils.formatDateTime(this, lastItem.timeSpan * 1000, flags)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.period_week -> presenter.loadData(Period.week)
                R.id.period_month -> presenter.loadData(Period.month)
                R.id.period_year -> presenter.loadData(Period.year)
            }
        }

    }

    class GraphAdapter : SparkAdapter() {

        var data: List<MarketPrice>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getY(index: Int): Float {
            return data?.get(index)?.value ?: 0f
        }

        override fun getItem(index: Int): Any? {
            return data?.get(index)
        }

        override fun getCount() = data?.size ?: 0

    }
}