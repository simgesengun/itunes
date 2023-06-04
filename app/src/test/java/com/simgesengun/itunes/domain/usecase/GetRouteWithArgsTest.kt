package com.simgesengun.itunes.domain.usecase

import com.simgesengun.itunes.app.navigation.model.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class GetRouteWithArgsTest {

    private lateinit var useCase: GetRouteWithArgs

    @Before
    fun setup() {
        useCase = GetRouteWithArgs()
    }

    @Test
    fun `when invoking with valid params, it should replace the arg in the route`() {
        val params = GetRouteWithArgs.Params(Screen.TrackDetail, "id", 123)
        val expectedOutput = "TrackDetail/123"

        val result = useCase(params)

        assertEquals(expectedOutput, result)
    }

    @Test
    fun `when invoking with a wrong arg, it should return an empty string`() {
        val params = GetRouteWithArgs.Params(Screen.TrackDetail, "argName", 123)

        assertThrows(NoSuchFieldException::class.java) {
            useCase(params)
        }
    }
}