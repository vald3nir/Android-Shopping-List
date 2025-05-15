package com.vald3nir.shoppinglist.repository.api

import com.vald3nir.shoppinglist.domain.dto.ProductFromBarcodeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicServicesAPI {

    @GET("api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String): ProductFromBarcodeDTO?
}