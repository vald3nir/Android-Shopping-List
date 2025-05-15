package com.vald3nir.shoppinglist.repository.api

import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/shoppingList")
    suspend fun getShoppingLists(): Response<List<ShoppingListDTO>>
}