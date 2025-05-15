package com.vald3nir.shoppinglist.domain.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductFromBarcodeDTO(
    val code: String?,
    val product: ProductFromBarcodeInfoDTO?,
)

@Serializable
data class ProductFromBarcodeInfoDTO(
    @SerializedName("product_name")
    val productName: String?
)