package com.vald3nir.android.firebase.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vald3nir.android.firebase.utils.readList
import com.vald3nir.android.firebase.utils.readObject
import com.vald3nir.android.firebase.utils.updateValue

object FirebaseDB {

    fun enableOfflineMode() {
        Firebase.database.setPersistenceEnabled(true)
    }

    fun insertOrUpdate(
        path: String,
        data: Any,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (() -> Unit)? = null,
        onError: ((Exception?) -> Unit)? = null,
    ) {
        try {
            Firebase.database.getReference(path).updateValue(data, onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }

    fun readList(
        path: String,
        keepSynced: Boolean = false,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (List<String?>) -> Unit,
        onError: ((Exception?) -> Unit)?,
    ) {
        try {
            val myRef = Firebase.database.getReference(path)
            myRef.keepSynced(keepSynced)
            myRef.readList(onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }

    fun readObject(
        path: String,
        keepSynced: Boolean = false,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (String?) -> Unit,
        onError: ((Exception?) -> Unit)?,
    ) {
        try {
            val myRef = Firebase.database.getReference(path)
            myRef.keepSynced(keepSynced)
            myRef.readObject(onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }
}