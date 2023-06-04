package com.simgesengun.itunes.domain.mapper.track

import com.simgesengun.itunes.domain.mapper.base.Mapper
import javax.inject.Inject

class TrackPriceMapper @Inject constructor(): Mapper<Pair<String?, String?>, String?> {

    override fun map(from: Pair<String?, String?>): String? {
        val (trackPrice, currency) = from
        return trackPrice?.let {
            listOf(it, currency).joinToString(" ")
        }
    }
}