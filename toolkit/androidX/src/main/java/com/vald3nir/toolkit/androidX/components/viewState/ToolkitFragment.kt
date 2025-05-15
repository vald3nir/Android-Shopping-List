package com.vald3nir.toolkit.androidX.components.viewState

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class ToolkitFragment<VB : ViewBinding>(@LayoutRes contentLayoutID: Int) : Fragment(contentLayoutID) {

    protected lateinit var binding: VB
    protected abstract fun bindView(view: View): VB

    protected abstract fun setupView()

    protected abstract fun setupObservables()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bindView(view)
        setupView()
        setupObservables()
    }


    // =============================================================================================
    // Ui State Control
    // =============================================================================================

    fun showLoading(show: Boolean) {
        myActivity()?.showLoading(show)
    }

    fun showMessage(msg: String?) {
        myActivity()?.showMessage(msg)
    }

    fun myActivity() = activity as? ToolkitActivity

    fun getUiStateManager() = myActivity() as UiStateManager

    fun ToolkitViewModel.test() {

    }

    // =============================================================================================
    // Simple Delay Function
    // =============================================================================================

    fun postSimpleDelayed(callback: () -> Unit, delayMillis: Long = 200L) {
        myActivity()?.postSimpleDelayed({ callback.invoke() }, delayMillis)
    }

    // =============================================================================================
    // Yes/No Dialog
    // =============================================================================================

    fun showYesNoDialog(
        title: String? = null,
        message: String? = null,
        icon: Int? = null,
        textConfirmButton: String? = null,
        textCancelButton: String? = null,
        onClickConfirm: () -> Unit,
        onClickCancel: (() -> Unit)? = null,
    ) {
        myActivity()?.showYesNoDialog(
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
    // Android Navigator
    // =============================================================================================

    fun navigateTo(direction: NavDirections) {
        findNavController().safeNavigate(direction)
    }

    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }

    // =============================================================================================
    // Android Life Cycle
    // =============================================================================================

    fun onBackPressed() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

}