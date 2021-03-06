package ru.justd.n26assignment.view

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.format.DateUtils
import android.widget.RadioGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.robinhood.spark.SparkView
import ru.justd.arkitec.view.ArkitecActivity
import ru.justd.lilwidgets.LilLoaderDialog
import ru.justd.n26assignment.App
import ru.justd.n26assignment.R
import ru.justd.n26assignment.model.MarketPrice
import ru.justd.n26assignment.model.Period
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

    override fun attachPresenter() {
        super.attachPresenter()

        radioGroup.setOnCheckedChangeListener { _, _ -> loadData() }
        loadData()
    }

    private fun loadData() {
        when (radioGroup.checkedRadioButtonId) {
            R.id.period_week -> presenter.loadData(Period.week)
            R.id.period_month -> presenter.loadData(Period.month)
            R.id.period_year -> presenter.loadData(Period.year)
        }
    }

    override fun showData(data: List<MarketPrice>, period: Period) {
        showLoading(false)

        graphAdapter.data = data

        val firstItem = data[0]
        val lastItem = data[data.lastIndex]

        minRate.text = firstItem.value.toString()
        maxRate.text = lastItem.value.toString()

        val flags: Int = when (period) {
            Period.week -> DateUtils.FORMAT_SHOW_TIME and DateUtils.FORMAT_SHOW_DATE
            Period.month -> DateUtils.FORMAT_SHOW_WEEKDAY and DateUtils.FORMAT_SHOW_DATE
            Period.year -> DateUtils.FORMAT_SHOW_DATE and DateUtils.FORMAT_SHOW_YEAR
            else -> throw IllegalArgumentException()
        }

        startDate.text = DateUtils.formatDateTime(this, firstItem.timeSpan * 1000, flags)
        endDate.text = DateUtils.formatDateTime(this, lastItem.timeSpan * 1000, flags)
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            LilLoaderDialog.Builder(supportFragmentManager)
                    .setDelay(300) //so magic much number
                    .setCancelable(true)
                    .create()
        } else {
            Handler().post { LilLoaderDialog.dismiss(supportFragmentManager) }
        }
    }

    override fun showError(localizedMessage: String?) {
        showLoading(false)

        val snackbar = Snackbar
                .make(
                        graph,
                        localizedMessage ?: resources.getString(R.string.error_unexpected),
                        Snackbar.LENGTH_INDEFINITE
                )

        snackbar
                .setAction(
                        R.string.retry,
                        {
                            loadData()
                            snackbar.dismiss()
                        }
                ).show()
    }

}