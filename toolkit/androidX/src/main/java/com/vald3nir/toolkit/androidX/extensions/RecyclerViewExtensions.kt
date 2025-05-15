package com.vald3nir.toolkit.androidX.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false),
    itemDecoration: RecyclerView.ItemDecoration? = null,
) {
    this.adapter = adapter
    setupLayoutManager(layoutManager, itemDecoration)
}

fun RecyclerView.setup(
    adapter: ListAdapter<*, *>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false),
    itemDecoration: RecyclerView.ItemDecoration? = null,
) {
    this.adapter = adapter
    setupLayoutManager(layoutManager, itemDecoration)
}

private fun RecyclerView.setupLayoutManager(layoutManager: RecyclerView.LayoutManager, itemDecoration: RecyclerView.ItemDecoration?) {
    this.layoutManager = layoutManager
    if (itemDecoration != null) {
        while (itemDecorationCount > 0) removeItemDecorationAt(0)
        this.addItemDecoration(itemDecoration)
    }
}