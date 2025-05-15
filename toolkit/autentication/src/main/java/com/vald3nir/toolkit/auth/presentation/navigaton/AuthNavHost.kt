package com.vald3nir.toolkit.auth.presentation.navigaton

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vald3nir.toolkit.auth.presentation.AuthScope
import com.vald3nir.toolkit.auth.presentation.AuthViewModel
import com.vald3nir.toolkit.auth.presentation.screens.HomeAuthScreen
import com.vald3nir.toolkit.auth.presentation.screens.LoginScreen
import com.vald3nir.toolkit.auth.presentation.screens.SignUpScreen

@Composable
fun Activity.AuthNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AuthScreenRoute.HomeAuth) {

        composable<AuthScreenRoute.HomeAuth> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = hiltViewModel<AuthViewModel>(),
                navController = navController
            ).HomeAuthScreen()
        }

        composable<AuthScreenRoute.Login> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = hiltViewModel<AuthViewModel>(),
                navController = navController
            ).LoginScreen()
        }

        composable<AuthScreenRoute.SignUp> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = hiltViewModel<AuthViewModel>(),
                navController = navController
            ).SignUpScreen()
        }
    }
}