package com.vald3nir.shoppinglist.presentation.features.boot

import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BootViewModel @Inject constructor() : BaseViewModel() {

    fun isUserLogged() = FirebaseAuthenticator.isUserLogged()

}