package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.mock.MockProductModel

suspend fun ProductsDao.useFakeData() {
    deleteAll()
    insert(MockProductModel.items)
}