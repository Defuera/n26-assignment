package ru.justd.n26assignment.view

import com.robinhood.spark.SparkAdapter
import ru.justd.n26assignment.model.MarketPrice

/**
 * Created by defuera on 27/03/2017.
 */
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