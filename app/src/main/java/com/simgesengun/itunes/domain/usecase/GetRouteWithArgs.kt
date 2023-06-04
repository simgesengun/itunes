package com.simgesengun.itunes.domain.usecase

import com.simgesengun.itunes.app.navigation.model.Screen
import javax.inject.Inject

class GetRouteWithArgs @Inject constructor() : UseCase<GetRouteWithArgs.Params, String> {

    override fun invoke(params: Params): String {
        return params.run {
            val argNameBlock = "{$argName}"
            if(screen.route.contains(argNameBlock)) {
                screen.route.replace(
                    argNameBlock, arg.toString()
                )
            } else throw NoSuchFieldException()
        }
    }

    data class Params(
        val screen: Screen,
        val argName: String,
        val arg: Any
    )
}