package com.vald3nir.shoppinglist.data.dtos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemShoppingListDTO(
    val nome: String? = null,
    var checked: Boolean? = false
) : Parcelable