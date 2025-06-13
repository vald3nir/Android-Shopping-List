package com.vald3nir.android.firebase.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

object FirebaseDB {

    fun enableOfflineMode() {
        Firebase.database.setPersistenceEnabled(true)
    }

    fun insertOrUpdate(path: String, data: Any) {
        Firebase.database.getReference(path).setValue(data)
    }

    suspend fun readList(path: String, keepSynced: Boolean = true): List<String?> {
        val myRef = Firebase.database.getReference(path)
        myRef.keepSynced(keepSynced)
        val response = arrayListOf<String?>()
        myRef.get().await().children.forEach { item ->
            val data = item.value as Map<*, *>
            response.add(JSONObject(data).toString())
        }
        return response
    }

    suspend fun readObject(path: String, keepSynced: Boolean = true): String {
        val myRef = Firebase.database.getReference(path)
        myRef.keepSynced(keepSynced)
        val item = myRef.get().await().value
        val data = item as Map<*, *>
        return JSONObject(data).toString()
    }
}