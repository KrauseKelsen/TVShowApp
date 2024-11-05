package com.example.tvshowapp3.models

import java.util.Date

data class TvShowItem(
    val id: Int,
    val image: Image,
    val name: String,
    val rating: Rating,
    val premiered: Date
)

data class Rating(
    val average: Double
)
