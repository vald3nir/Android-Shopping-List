package com.vald3nir.android.firebase.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.serialization.json.Json
import org.json.JSONObject

fun DatabaseReference.updateValue(
    dataJson: Any,
    onShowLoading: ((Boolean) -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onError: ((Exception?) -> Unit)? = null,
) {
    onShowLoading?.invoke(true)
    this.setValue(dataJson)
    this.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            finallySuccess(onShowLoading, onSuccess)
        }

        override fun onCancelled(error: DatabaseError) {
            finallyError(onShowLoading, onError, error.toException())
        }
    })
}

fun DatabaseReference.readList(
    onShowLoading: ((Boolean) -> Unit)? = null,
    onSuccess: (List<String?>) -> Unit,
    onError: ((Exception?) -> Unit)? = null,
) {
    onShowLoading?.invoke(true)
    get()
        .addOnFailureListener {
            finallyError(onShowLoading, onError, it)
        }
        .addOnSuccessListener {
            try {
                val response = arrayListOf<String?>()
                it.children.forEach { item ->
                    val data = item.value as Map<*, *>
                    response.add(JSONObject(data).toString())
                }
                finallySuccess(onShowLoading, onSuccess, response)
            } catch (e: Exception) {
                finallyError(onShowLoading, onError, e)
            }
        }
}

fun DatabaseReference.readObject(
    onShowLoading: ((Boolean) -> Unit)? = null,
    onSuccess: (String?) -> Unit,
    onError: ((Exception?) -> Unit)? = null,
) {
    onShowLoading?.invoke(true)
    get()
        .addOnFailureListener { finallyError(onShowLoading, onError, it) }
        .addOnSuccessListener {
            try {
                val data = it.value as Map<*, *>
                val response = JSONObject(data).toString()
                finallySuccess(onShowLoading, onSuccess, response)
            } catch (e: Exception) {
                finallyError(onShowLoading, onError, e)
            }
        }
}

private fun finallySuccess(
    onShowLoading: ((Boolean) -> Unit)? = null,
    onSuccess: (() -> Unit)? = null
) {
    onShowLoading?.invoke(false)
    onSuccess?.invoke()
}

private fun <T> finallySuccess(
    onShowLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    response: T
) {
    onShowLoading?.invoke(false)
    onSuccess?.invoke(response)
}

private fun finallyError(
    onShowLoading: ((Boolean) -> Unit)?,
    onError: ((Exception?) -> Unit)?,
    e: Exception
) {
    onShowLoading?.invoke(false)
    onError?.invoke(e)
}

inline fun <reified T> List<String?>.parseStringListToObjects(): List<T> {
    val items = arrayListOf<T>()
    this.map { data -> data?.let { items.add(Json.decodeFromString(it)) } }
    return items
}

inline fun <reified T> String.parseStringToObject(): T {
    return Json.decodeFromString(this)
}