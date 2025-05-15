package com.vald3nir.toolkit.androidX.extensions

import com.google.android.material.tabs.TabLayout

fun TabLayout.actionClickListener(actionClickListener: (position: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabReselected(tab: TabLayout.Tab) {}
        override fun onTabSelected(tab: TabLayout.Tab) {
            actionClickListener.invoke(tab.position)
        }
    })
}