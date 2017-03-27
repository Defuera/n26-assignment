package ru.justd.n26assignment.model

/**
 * Created by defuera on 27/03/2017.
 */
data class ChartsResponse<T>(

        val status: String,
        val name: String,
        val unit: String,
        val period: String,
        val description: String,
        val values: List<T>

)