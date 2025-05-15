package com.vald3nir.shoppinglist.domain.enums

import androidx.annotation.DrawableRes
import com.vald3nir.shoppinglist.R

enum class ProductCategoryEnum(val alias: String, @DrawableRes val icon: Int) {
    HYGIENE(alias = "Higiene e Beleza", icon = R.drawable.ic_hygiene),
    MEAT(alias = "Açougue e Peixaria", icon = R.drawable.ic_meat),
    BAKERY(alias = "Padaria", icon = R.drawable.ic_cake),
    PRODUCE(alias = "Hortifruti", icon = R.drawable.ic_apple),
    CLEANING(alias = "Limpeza", icon = R.drawable.ic_cleaning),
    DRINKS(alias = "Bebidas", icon = R.drawable.ic_drink),
    ELECTRONICS(alias = "Eletrônicos", icon = R.drawable.ic_electronics),
    OFFICE(alias = "Escritório", icon = R.drawable.ic_office),
    PHARMACY(alias = "Farmácia", icon = R.drawable.ic_pharmacy),
    PET_SHOP(alias = "PetShops", icon = R.drawable.ic_pet),
    GROCERIES(alias = "Mercearia", icon = R.drawable.ic_groceries),
}

fun String?.toProductCategoryEnum(): ProductCategoryEnum? {
    return ProductCategoryEnum.entries.find { it.alias.equals(this, ignoreCase = true) }
}