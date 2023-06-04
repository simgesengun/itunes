package com.simgesengun.itunes.domain.usecase

interface UseCase <in Params, out T> {
    operator fun invoke(params: Params): T
}