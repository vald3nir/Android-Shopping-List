package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.repository.db.dao.ProductsDao
import com.vald3nir.shoppinglist.repository.mock.MockProductModel

suspend fun ProductsDao.useFakeData() {
    deleteAll()
    insert(MockProductModel.items)
}