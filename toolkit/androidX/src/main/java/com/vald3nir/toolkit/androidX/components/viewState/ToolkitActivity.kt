package com.vald3nir.toolkit.androidX.components.viewState

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vald3nir.toolkit.androidX.components.base.CustomYesNoDialog
import com.vald3nir.toolkit.androidX.extensions.hideKeyboard

abstract class ToolkitActivity : AppCompatActivity(), UiStateManager {

    // =============================================================================================
    // Android Life Cycle
    // =============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun onDestroy() {
        simpleDelay.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }

    // =============================================================================================
    // Ui State Control
    // =============================================================================================

    override suspend fun updateUiState(state: UiState) {
        when (state) {
            is UiState.Loading -> showLoading(state.show)
            is UiState.Error -> showError(state.e)
            is UiState.Message -> showMessage(state.msg)
        }
    }

    abstract fun showError(e: Exception?)
    abstract fun showMessage(msg: String?)
    abstract fun showLoading(show: Boolean)

    // =============================================================================================
    // Simple Delay Function
    // =============================================================================================

    private val simpleDelay = Handler(Looper.getMainLooper())

    fun postSimpleDelayed(callback: () -> Unit, delayMillis: Long = 200L) {
        simpleDelay.postDelayed({ callback.invoke() }, delayMillis)
    }

    // =============================================================================================
    // Yes/No Dialog
    // =============================================================================================

    private val yesNoDialog by lazy {
        CustomYesNoDialog(context = this)
    }

    fun showYesNoDialog(
        title: String? = null,
        message: String? = null,
        icon: Int? = null,
        textConfirmButton: String? = null,
        textCancelButton: String? = null,
        onClickConfirm: () -> Unit,
        onClickCancel: (() -> Unit)? = null,
    ) {
        yesNoDialog.showDialog(
            title,
            message,
            icon,
            textConfirmButton,
            textCancelButton,
            onClickConfirm,
            onClickCancel
        )
    }

    // =============================================================================================
    // UI Style
    // =============================================================================================

    fun setStatusBarBackgroundColor(color: Int) = with(window) {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(this@ToolkitActivity, color)
    }
}