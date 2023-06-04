package com.simgesengun.itunes.data.datasource.remote.model

data class ResponseSearch(
    val resultCount: Int?,
    val results: List<Track>?
)