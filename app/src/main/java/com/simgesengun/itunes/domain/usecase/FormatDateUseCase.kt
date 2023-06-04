package com.simgesengun.itunes.domain.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FormatDateUseCase @Inject constructor(): UseCase<Date?, String?> {
    override operator fun invoke(params: Date?): String? {
        return params?.run {
            SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.getDefault()).format(params)
        }
    }

    companion object {
        const val DISPLAY_DATE_FORMAT = "dd/MM/yyyy"
    }
}