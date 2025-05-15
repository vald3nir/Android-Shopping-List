package com.vald3nir.toolkit.androidX.components.templates

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vald3nir.toolkit.androidx.databinding.CustomItemListBinding
import com.vald3nir.toolkit.androidx.databinding.CustomSheetDialogBinding

class CustomSheetDialog(
    context: Context,
    private val title: String? = null,
    private val titleColor: Int? = null,
    private val items: List<CustomItemSheet>
) : BottomSheetDialog(context) {

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = CustomSheetDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.initViews()
    }

    private fun CustomSheetDialogBinding.initViews() {
        rvItems.apply {
            adapter = SheetDialogAdapter()
            layoutManager = LinearLayoutManager(context)

        }
        txvTitle.apply {
            text = title
            setTextColor(titleColor)
        }
    }

    inner class SheetDialogAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(CustomItemListBinding.inflate(layoutInflater))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindView(items[position])
        }

        override fun getItemCount(): Int = items.size
    }

    inner class ViewHolder(private val customItemSheet: CustomItemListBinding) :
        RecyclerView.ViewHolder(customItemSheet.root) {

        fun bindView(item: CustomItemSheet) {
            customItemSheet.apply {
                root.setOnClickListener {
                    dismiss()
                    item.action.invoke()
                }
                txvLabel.apply {
                    text = item.title
                    setTextColor(item.titleCor)
                }
                item.icon?.let { imvIcon.setImageResource(it) }
            }
        }
    }

    private fun TextView.setTextColor(color: Int?) {
        color?.let {
            setTextColor(ContextCompat.getColor(context, it))
        }
    }

    data class CustomItemSheet(
        val icon: Int? = null,
        val title: String?,
        val titleCor: Int?,
        val action: () -> Unit
    )
}