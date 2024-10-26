package uz.android.jetmovieapp.common.models

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieResponse(
    val page: Long,
    val results: List<Result>,
    val total_pages: Long,
    val total_results: Long,
)

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Long>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long,
)
