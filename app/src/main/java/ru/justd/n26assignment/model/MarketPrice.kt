package ru.justd.n26assignment.model

import com.google.gson.annotations.SerializedName

/**
 * Created by defuera on 27/03/2017.
 */
data class MarketPrice (

        @SerializedName("x")
        val timeSpan: Long,

        @SerializedName("y")
        val value: Float

)