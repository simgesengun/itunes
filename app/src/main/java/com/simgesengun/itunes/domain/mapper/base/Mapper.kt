package com.simgesengun.itunes.domain.mapper.base

interface Mapper<in From, out To> {
    fun map(from: From): To
}