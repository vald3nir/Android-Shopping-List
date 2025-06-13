package com.vald3nir.shoppinglist.db.mock

import com.vald3nir.shoppinglist.db.model.entities.ProductModel
import com.vald3nir.shoppinglist.domain.enums.ProductCategoryEnum

object MockProductModel {

    val items = listOf(
        ProductModel(name = "Pasta de dente", category = ProductCategoryEnum.HYGIENE.alias),
        ProductModel(name = "Acém", category = ProductCategoryEnum.MEAT.alias),
        ProductModel(name = "Pão", category = ProductCategoryEnum.BAKERY.alias),
        ProductModel(name = "Uva", category = ProductCategoryEnum.PRODUCE.alias),
        ProductModel(name = "Sabão", category = ProductCategoryEnum.CLEANING.alias),
        ProductModel(name = "Cerveja", category = ProductCategoryEnum.DRINKS.alias),
        ProductModel(name = "Smartphone", category = ProductCategoryEnum.ELECTRONICS.alias),
        ProductModel(name = "Grampeador", category = ProductCategoryEnum.OFFICE.alias),
        ProductModel(name = "Dipirona", category = ProductCategoryEnum.PHARMACY.alias),
        ProductModel(name = "Ração", category = ProductCategoryEnum.PET_SHOP.alias),
        ProductModel(name = "Arroz", category = ProductCategoryEnum.GROCERIES.alias),
    )
}