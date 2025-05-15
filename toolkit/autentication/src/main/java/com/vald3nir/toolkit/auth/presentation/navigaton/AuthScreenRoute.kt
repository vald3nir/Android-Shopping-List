package com.vald3nir.toolkit.auth.presentation.navigaton

import com.vald3nir.toolkit.helpers.navigation.UiDestination
import kotlinx.serialization.Serializable

sealed interface AuthScreenRoute : UiDestination {

    @Serializable
    data object HomeAuth : AuthScreenRoute

    @Serializable
    data object Login : AuthScreenRoute

    @Serializable
    data object SignUp : AuthScreenRoute
}