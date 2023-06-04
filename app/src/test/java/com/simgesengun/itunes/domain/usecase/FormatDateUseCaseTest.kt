package com.simgesengun.itunes.domain.usecase

import java.util.Calendar
import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FormatDateUseCaseTest {

    private lateinit var useCase: FormatDateUseCase

    @Before
    fun setup() {
        useCase = FormatDateUseCase()
    }

    @Test
    fun `when invoking with a valid date, it should return formatted date string`() {
        val calendar = Calendar.getInstance().also {
            it.set(2023, 5, 3)
        }

        val expectedOutput = "03/06/2023"

        val result = useCase(calendar.time)

        assertEquals(expectedOutput, result)
    }

    @Test
    fun `when invoking with null date, it should return null`() {
        val inputDate: Date? = null

        val result = useCase(inputDate)

        assertEquals(null, result)
    }

    private fun createDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return calendar.time
    }
}