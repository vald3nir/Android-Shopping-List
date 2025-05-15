package com.vald3nir.toolkit.auth.presentation

import android.app.Activity
import androidx.navigation.NavController
import com.vald3nir.toolkit.auth.presentation.navigaton.AuthScreenRoute
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

internal data class AuthScope(
    val activity: Activity,
    val viewModel: AuthViewModel,
    val navController: NavController
) : BaseScreenScope(viewModel, navController) {

    val onClickLoginWithGoogle: () -> Unit = {
        viewModel.loginWithGoogle(activity = activity)
    }

    val onClickUseFakeData: () -> Unit = {
        viewModel.useFakeData()
    }

    val onClickLogin: (email: String, password: String) -> Unit = { email, password ->
        viewModel.loginWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
        )
    }

    val onClickSignUp: (email: String, password: String) -> Unit = { email, password ->
        viewModel.createUserWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
        )
    }

    val onClickRedirectToLogin: () -> Unit = {
        navController.navigate(AuthScreenRoute.Login)
    }

    val onClickRedirectToSignUp: () -> Unit = {
        navController.navigate(AuthScreenRoute.SignUp)
    }

    fun finishAuthentication() {
        viewModel.finishAuthentication(activity)
    }
}

internal fun enableBtnContinue(email: String, password: String, errorPasswordMessage: String?, errorEmailMessage: String?): Boolean {
//    println("enableBtnContinue-> email: $email password: $password errorPasswordMessage: $errorPasswordMessage errorEmailMessage: $errorEmailMessage")
    return email.isNotBlank() && password.isNotBlank() && errorPasswordMessage.isNullOrBlank() && errorEmailMessage.isNullOrBlank()
}