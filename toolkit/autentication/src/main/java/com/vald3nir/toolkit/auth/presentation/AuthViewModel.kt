package com.vald3nir.toolkit.auth.presentation

import android.app.Activity
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.toolkit.auth.di.AuthApplicationCall
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(private val applicationCall: AuthApplicationCall) :
    BaseViewModel() {

    fun finishAuthentication(activity: BaseActivity) {
        applicationCall.finishAuth(activity)
    }

    fun loginWithGoogle(activity: Activity?) {
        launchWithScope {
            FirebaseAuthenticator.loginWithGoogle(
                activity = activity,
                serverClientId = applicationCall.getUserClientID(),
                onLoginSuccess = {
                    clearFakeUser()
                },
                onLoginError = {
                    updateViewState(BaseScreenState.Loading(false))
                    updateViewState(BaseScreenState.ShowMessage(it?.message.toString()))
                }
            )
        }
    }

    fun createUserWithEmailAndPassword(activity: Activity?, email: String, password: String) {
        updateViewState(BaseScreenState.Loading(true))
        FirebaseAuthenticator.createUserWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
            onCreateUserSuccess = {
                loginWithEmailAndPassword(activity = activity, email = email, password = password)
            },
            onLoginError = {
                updateViewState(BaseScreenState.Loading(false))
                updateViewState(BaseScreenState.ShowMessage(it?.message))
            },
        )
    }

    fun loginWithEmailAndPassword(activity: Activity?, email: String, password: String) {
        launchWithScope {
            FirebaseAuthenticator.loginWithEmailAndPassword(
                activity = activity,
                email = email,
                password = password,
                onLoginSuccess = {
                    updateViewState(BaseScreenState.Loading(false))
                    updateViewState(BaseScreenState.Success(Unit))
                },
                onLoginError = {
                    updateViewState(BaseScreenState.Loading(false))
                    updateViewState(BaseScreenState.ShowMessage(it?.message))
                }
            )
        }
    }

    private fun clearFakeUser() {
        launchWithScope {
            applicationCall.clearFakeData()
            updateViewState(BaseScreenState.Success(Unit))
        }
    }

    fun useFakeData() {
        launchWithScope {
            applicationCall.useFakeData()
            updateViewState(BaseScreenState.Success(Unit))
        }
    }
}